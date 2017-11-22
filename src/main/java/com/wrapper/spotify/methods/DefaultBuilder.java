package com.wrapper.spotify.methods;

import java.util.function.Function;

@SuppressWarnings("javadoc")
public final class DefaultBuilder<T> extends AbstractBuilder<DefaultBuilder<T>, T> {

	public DefaultBuilder(String path, Function<DefaultBuilder<T>, Request<T>> builder) {
		super(path, builder);
	}

}
