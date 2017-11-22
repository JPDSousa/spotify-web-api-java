package com.wrapper.spotify.methods;

import java.util.function.Function;

@SuppressWarnings("javadoc")
public final class IdsBuilder<T> extends AbstractIdsBuilder<IdsBuilder<T>, T> {
	
	public IdsBuilder(int maxItems, String path, Function<IdsBuilder<T>, Request<T>> builder) {
		super(maxItems, path, builder);
	}

}
