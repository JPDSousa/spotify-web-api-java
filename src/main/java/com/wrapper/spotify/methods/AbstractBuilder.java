package com.wrapper.spotify.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.methods.Request.Builder;

import net.sf.json.JSON;

@SuppressWarnings("javadoc")
public abstract class AbstractBuilder<B extends Builder<B, T>, T> implements Request.Builder<B, T> {
	
	protected Url.Scheme scheme = Api.DEFAULT_SCHEME;
	protected String host = Api.DEFAULT_HOST;
	protected int port = Api.DEFAULT_PORT;
	protected final String path;
	protected HttpManager httpManager;
	protected JSON jsonBody;
	protected List<Url.Parameter> parameters = new ArrayList<Url.Parameter>();
	protected List<Url.Parameter> headerParameters = new ArrayList<Url.Parameter>();
	protected List<Url.Part> parts = new ArrayList<Url.Part>();
	protected List<Url.Parameter> bodyParameters = new ArrayList<Url.Parameter>();
	
	private final Function<B, Request<T>> builder;
	
	protected AbstractBuilder(String path, Function<B, Request<T>> builder) {
		super();
		this.builder = builder;
		this.path = path;
	}

	@SuppressWarnings("unchecked")
	@Override
	public B httpManager(HttpManager httpManager) {
		this.httpManager = httpManager;
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public B host(String host) {
		this.host = host;
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public B port(int port) {
		this.port = port;
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public B scheme(Url.Scheme scheme) {
		this.scheme = scheme;
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B parameter(String name, String value) {
		assert (name != null);
		assert (name.length() > 0);
		assert (value != null);

		Url.Parameter parameter = Url.Parameter.newBuilder()
				.setName(name)
				.setValue(value).build();
		parameters.add(parameter);

		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B body(String name, String value) {
		assert (name != null);
		assert (name.length() > 0);
		assert (value != null);

		Url.Parameter parameter = Url.Parameter.newBuilder()
				.setName(name)
				.setValue(value).build();
		bodyParameters.add(parameter);

		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B body(JSON jsonBody) {
		assert (jsonBody != null);
		this.jsonBody = jsonBody;

		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B header(String name, String value) {
		assert (name != null);
		assert (name.length() > 0);
		assert (value != null);

		Url.Parameter parameter= Url.Parameter.newBuilder()
				.setName(name)
				.setValue(value).build();
		headerParameters.add(parameter);

		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B part(Url.Part part) {
		assert (part != null);
		parts.add(part);
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Request<T> build() {
		return builder.apply((B) this);
	}

}