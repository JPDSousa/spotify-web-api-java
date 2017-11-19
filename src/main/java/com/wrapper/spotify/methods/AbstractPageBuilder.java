package com.wrapper.spotify.methods;

import java.util.function.Function;

@SuppressWarnings("javadoc")
public class PageBuilder<B extends PageBuilder<B, T>, T> extends AbstractBuilder<B, T> {

	protected PageBuilder(String path, Function<B, Request<T>> builder) {
		super(path, builder);
	}
	
	public B limit(int limit) {
		assert (limit > 0);
		return parameter("limit", String.valueOf(limit));
	}

	public B offset(int offset) {
		assert (offset >= 0);
		return parameter("offset", String.valueOf(offset));
	}

}
