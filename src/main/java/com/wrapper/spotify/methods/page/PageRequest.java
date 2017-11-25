package com.wrapper.spotify.methods.page;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;

@SuppressWarnings("javadoc")
public class PageRequest<T> extends AbstractRequest<Page<T>> {

	public static <T> Builder<T> builder(String pageHref, String propName, JsonFactory<T> jsonFactory) throws MalformedURLException {
		return new Builder<>(new URL(pageHref), propName, jsonFactory);
	}
	
	public static final class Builder<T> extends AbstractBuilder<Builder<T>, Page<T>> {

		public Builder(URL url, String propName, JsonFactory<T> jsonFactory) {
			super(url.getPath(), builder -> new PageRequest<>(propName, jsonFactory, builder));
			addQuery(url.getQuery());
		}

		private void addQuery(String query) {
			final StringTokenizer tokenizer = new StringTokenizer(query, "&");
			while(tokenizer.hasMoreTokens()) {
				final String[] param = tokenizer.nextToken().split("=", 2);
				parameter(param[0], param[1]);
			}
		}
		
	}
	
	public PageRequest(String propName, JsonFactory<T> jsonFactory, Builder<T> builder) {
		super(new PageJsonFactory<>(propName, jsonFactory), builder);
	}

}
