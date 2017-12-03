package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.RateLimiter;
import com.wrapper.spotify.Method;
import com.wrapper.spotify.json.JsonFactory;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("javadoc")
public abstract class AbstractRequest<T> implements Request<T> {

	private static final Logger LOGGER = Logger.getLogger(AbstractRequest.class.getName());

	protected static String joinPath(String... elements) {
		return String.join("/", elements);
	}

	private final URI url;
	private final List<Header> headers;
	private final JSON body;
	private final HttpClient httpClient;
	private final Method method;
	private final JsonFactory<T> jsonFactory;
	private final RateLimiter rateLimiter;
	private final ConcurrentMap<String, String> cache;

	public AbstractRequest(JsonFactory<T> jsonFactory, AbstractBuilder<?, T> builder) {
		this(jsonFactory, Method.GET, builder);
	}

	public AbstractRequest(JsonFactory<T> jsonFactory, Method method, AbstractBuilder<?, T> builder) {
		assert (builder.path != null);
		assert (builder.host != null);
		assert (builder.port > 0);
		assert (builder.scheme != null);
		assert (builder.query != null);
		assert (builder.headerParameters != null);

		this.headers = builder.headerParameters;
		this.body = builder.jsonBody;
		this.cache = builder.cache;
		this.method = method;
		this.jsonFactory = jsonFactory;
		this.rateLimiter = builder.rateLimiter;
		this.httpClient = builder.httpManager;

		try {
			final URIBuilder uriBuilder = new URIBuilder()
					.setScheme(builder.scheme)
					.setHost(builder.host)
					.setPort(builder.port)
					.setPath(builder.path);
			if(!builder.query.isEmpty()) {
				uriBuilder.setParameters(builder.query);
			}
			url = uriBuilder.build();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public URL toUrl() {
		try {
			return url.toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return url.toString();
	}

	private String executeRequest(HttpRequestBase request) throws IOException, ClientProtocolException {
		final HttpResponse response = httpClient.execute(request);
		try {
			handleErrorStatusCode(response);
			final String responseString = EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
			handleErrorResponseBody(responseString);
			return responseString;
		} catch (RuntimeException e) {
			LOGGER.severe("Bad request: " + request.getURI() + " || " 
					+ Arrays.toString(request.getAllHeaders()));
			throw e;
		}
	}

	/*
	 * TODO: Error handling could be more granular and throw a different exception depending on status code.
	 * It could also look into the JSON object to find an error message.
	 */
	private void handleErrorStatusCode(HttpResponse httpResponse) {
		int statusCode = httpResponse.getStatusLine().getStatusCode();

		if (statusCode >= 400 && statusCode < 500) {
			throw new RuntimeException("Bad Request: " + String.valueOf(statusCode));
		}
		if (statusCode >= 500) {
			throw new RuntimeException("Server Error: " + String.valueOf(statusCode));
		}

	}

	private void handleErrorResponseBody(String responseBody) {
		if (responseBody == null) {
			throw new RuntimeException("No response body");
		}

		if (!responseBody.equals("") && responseBody.startsWith("{")) {
			final JSONObject jsonObject = JSONObject.fromObject(responseBody);

			if (jsonObject.has("error")) {
				throw new RuntimeException(jsonObject.getString("error"));
			}
		}
	}

	public String getJson() throws IOException {
		final HttpGet request = new HttpGet(url);
		headers.forEach(request::addHeader);
		return executeRequest(request);
	}

	@SuppressWarnings("unchecked")
	public String postJson() throws IOException {
		final HttpPost request = new HttpPost(url);
		headers.forEach(request::addHeader);
		if(body instanceof JSONObject) {
			final List<NameValuePair> pairs = new ArrayList<>();
			((JSONObject) body).names()
			.forEach(name -> pairs.add(new BasicNameValuePair((String) name, ((JSONObject) body).getString((String) name))));
			request.setEntity(new UrlEncodedFormEntity(pairs));
		}
		else {
			request.setEntity(new StringEntity(body.toString(), ContentType.APPLICATION_JSON));
		}
		return executeRequest(request);
	}

	@SuppressWarnings("unchecked")
	public String putJson() throws IOException {
		final HttpPut request = new HttpPut(url);
		headers.forEach(request::addHeader);
		if(body instanceof JSONObject) {
			final List<NameValuePair> pairs = new ArrayList<>();
			((JSONObject) body).names()
			.forEach(name -> pairs.add(new BasicNameValuePair((String) name, ((JSONObject) body).getString((String) name))));
			request.setEntity(new UrlEncodedFormEntity(pairs));
		}
		else {
			request.setEntity(new StringEntity(body.toString(), ContentType.APPLICATION_JSON));
		}
		return executeRequest(request);
	}

	public String deleteJson() throws IOException {
		final HttpDelete request = new HttpDelete(url);
		headers.forEach(request::addHeader);
		return executeRequest(request);
	}

	@Override
	public List<Header> getHeader() {
		return headers;
	}

	@Override
	public String getBody() {
		return body != null ? body.toString() : null;
	}

	private String execMethod() throws IOException {
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
	public T exec() throws IOException {
		final String cacheKey = method.name() + toString();
		String cached = null;
		if(cache != null) {
			cached = cache.get(cacheKey);
		}
		if(cached == null) {
			rateLimiter.acquire();
			cached = execMethod();
			if(cache != null) {
				cache.put(cacheKey, cached);
			}
		}
		return jsonFactory.fromJson(cached);
	}

}
