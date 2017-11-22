package com.wrapper.spotify.methods;

import java.util.function.Function;

@SuppressWarnings("javadoc")
public abstract class AbstractPageBuilder<B extends AbstractPageBuilder<B, T>, T> extends AbstractBuilder<B, T> implements PageBuilder<B> {

	protected AbstractPageBuilder(String path, Function<B, Request<T>> builder) {
		super(path, builder);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public B limit(int limit) {
		return BuilderUtils.limit((B) this, limit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public B offset(int offset) {
		return BuilderUtils.offset((B) this, offset);
	}

}
