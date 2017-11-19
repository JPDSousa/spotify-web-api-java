package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.HttpManager.Method;
import com.wrapper.spotify.UrlUtil;
import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.json.JsonFactory;

import net.sf.json.JSONObject;

import java.io.IOException;

@SuppressWarnings("javadoc")
public abstract class AbstractRequest<T> implements Request<T> {

	protected static String joinPath(String... elements) {
		return String.join("/", elements);
	}
	
	private Url url;

	private final HttpManager httpManager;
	private final Method method;
	private final JsonFactory<T> jsonFactory;

	public AbstractRequest(JsonFactory<T> jsonFactory, AbstractBuilder<?, T> builder) {
		this(jsonFactory, Method.GET, builder);
	}
	
	public AbstractRequest(JsonFactory<T> jsonFactory, Method method, AbstractBuilder<?, T> builder) {
		assert (builder.path != null);
		assert (builder.host != null);
		assert (builder.port > 0);
		assert (builder.scheme != null);
		assert (builder.parameters != null);
		assert (builder.parts != null);
		assert (builder.bodyParameters != null);
		assert (builder.headerParameters != null);

		this.method = method;
		this.jsonFactory = jsonFactory;
		if (builder.httpManager == null) {
			httpManager = Api.DEFAULT_HTTP_MANAGER;
		} else {
			httpManager = builder.httpManager;
		}

		Url.Builder urlBuilder = Url.newBuilder()
				.setScheme(builder.scheme)
				.setHost(builder.host)
				.setPort(builder.port)
				.setPath(builder.path)
				.addAllParameters(builder.parameters)
				.addAllBodyParameters(builder.bodyParameters)
				.addAllHeaderParameters(builder.headerParameters)
				.addAllParts(builder.parts);

		if (builder.jsonBody != null) {
			urlBuilder.setJsonBody(builder.jsonBody.toString());
		}

		url = urlBuilder.build();
	}

	@Override
	public Url toUrl() {
		return url;
	}

	@Override
	public String toString() {
		return UrlUtil.assemble(url);
	}

	@Override
	public String toStringWithQueryParameters() {
		return UrlUtil.assembleWithQueryParameters(url);
	}

	public String getJson() throws IOException, WebApiException {
		return httpManager.get(url);
	}

	public String postJson() throws IOException, WebApiException {
		return httpManager.post(url);
	}

	public String putJson() throws IOException, WebApiException {
		return httpManager.put(url);
	}

	public String deleteJson() throws IOException, WebApiException {
		return httpManager.delete(url);
	}
	
	private String execMethod() throws IOException, WebApiException {
		switch(method) {
		case DELETE:
			return deleteJson();
		case GET:
			return getJson();
		case POST:
			return postJson();
		case PUT:
			return putJson();
		}
		return null;
	}
	
	@Override
	public T exec() throws IOException, WebApiException {
		return jsonFactory.fromJson(JSONObject.fromObject(execMethod()));
	}
	
	@Override
	public SettableFuture<T> execAsync() {
		SettableFuture<T> settableFuture = SettableFuture.create();
		try {
			settableFuture.set(exec());
		} catch (Exception e) {
			settableFuture.setException(e);
		}
		return settableFuture;
	}

}
