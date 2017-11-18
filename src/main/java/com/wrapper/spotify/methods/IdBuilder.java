package com.wrapper.spotify.methods;

import java.util.function.Function;

@SuppressWarnings("javadoc")
public class IdBuilder<T> extends AbstractBuilder<IdBuilder<T>, T> {

	private final String path;
	
	public IdBuilder(String path, Function<IdBuilder<T>, Request<T>> builder) {
		super(builder);
		this.path = path;
	}

	public IdBuilder<T> id(String id) {
		assert (id != null);
		final String path = String.format(this.path, id);
		return path(path);
	}

}
