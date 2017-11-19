package com.wrapper.spotify.methods;

import java.util.function.Function;

@SuppressWarnings("javadoc")
public class IdBuilder<T> extends AbstractBuilder<IdBuilder<T>, T> {

	public IdBuilder(String path, Function<IdBuilder<T>, Request<T>> builder) {
		super(path, builder);
	}

	public IdBuilder<T> id(String id) {
		assert (id != null);
		final String path = String.format(this.path, id);
		return path(path);
	}

}
