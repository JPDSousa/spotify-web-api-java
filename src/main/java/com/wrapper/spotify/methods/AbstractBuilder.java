package com.wrapper.spotify.methods;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.Request.Builder;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public abstract class AbstractBuilder<B extends Builder<B, T>, T> implements Request.Builder<B, T> {

	protected String scheme;
	protected String host;
	protected int port;
	protected final String path;
	protected HttpClient httpManager;
	protected JSON jsonBody;
	protected final List<NameValuePair> body;
	protected final List<NameValuePair> query;
	protected final List<Header> headerParameters;
	protected RateLimiter rateLimiter;
	protected ConcurrentMap<String, String> cache;

	private final Function<B, Request<T>> builder;

	protected AbstractBuilder(String path, Function<B, Request<T>> builder) {
		super();
		httpManager = Api.DEFAULT_HTTP_CLIENT;
		scheme = Api.DEFAULT_SCHEME;
		host = Api.DEFAULT_HOST;
		port = Api.DEFAULT_PORT;
		this.builder = builder;
		this.path = path;
		rateLimiter = RateLimiter.create(20);
		query = Lists.newArrayList();
		headerParameters = Lists.newArrayList();
		body = Lists.newArrayList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public B httpClient(HttpClient httpManager) {
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
	public B scheme(String scheme) {
		this.scheme = scheme;
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B rateLimiter(RateLimiter rateLimiter) {
		this.rateLimiter = rateLimiter;
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B cache(ConcurrentMap<String, String> cache) {
		this.cache = cache;
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B query(String name, String value) {
		assert (name != null);
		assert (!name.isEmpty());
		assert (value != null);

		query.add(new BasicNameValuePair(name, value));
		return (B) this;
	}

	@SuppressWarnings("unchecked")
	public B body(String name, String value) {
		assert name != null;
		assert value != null;
		body.add(new BasicNameValuePair(name, value));

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
		assert (!name.isEmpty());
		assert (value != null);

		headerParameters.add(new BasicHeader(name, value));

		return (B) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Request<T> build() {
		final JSONObject json;
		if(!body.isEmpty()) {
			if(jsonBody == null) {
				json = new JSONObject();
				jsonBody = json;
			}
			else if(jsonBody instanceof JSONObject) {
				json = (JSONObject) jsonBody;
			}
			else {
				throw new RuntimeException("Invalid body");
			}
			body.forEach(pair -> json.element(pair.getName(), pair.getValue()));
		}
		return builder.apply((B) this);
	}

}