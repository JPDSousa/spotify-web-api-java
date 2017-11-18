package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UrlUtil;
import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.WebApiException;

import net.sf.json.JSONObject;

import java.io.IOException;

@SuppressWarnings("javadoc")
public abstract class AbstractRequest<T> implements Request<T> {

	private Url url;

	private HttpManager httpManager;

	public AbstractRequest(AbstractBuilder<?, T> builder) {
		assert (builder.path != null);
		assert (builder.host != null);
		assert (builder.port > 0);
		assert (builder.scheme != null);
		assert (builder.parameters != null);
		assert (builder.parts != null);
		assert (builder.bodyParameters != null);
		assert (builder.headerParameters != null);

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
	
	@Override
	public T get() throws IOException, WebApiException {
		return fromJson(JSONObject.fromObject(getJson()));
	}
	
	@Override
	public SettableFuture<T> getAsync() {
		SettableFuture<T> settableFuture = SettableFuture.create();
		try {
			settableFuture.set(get());
		} catch (Exception e) {
			settableFuture.setException(e);
		}
		return settableFuture;
	}
	
	protected abstract T fromJson(JSONObject json);

}
