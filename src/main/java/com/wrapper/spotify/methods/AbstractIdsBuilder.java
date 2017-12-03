package com.wrapper.spotify.methods;

import java.util.List;
import java.util.function.Function;

@SuppressWarnings("javadoc")
public abstract class AbstractIdsBuilder<B extends AbstractIdsBuilder<B, T>, T> extends AbstractBuilder<B, T> {

	private final int maxItems;
	
	protected AbstractIdsBuilder(int maxItems, String path, Function<B, Request<T>> builder) {
		super(path, builder);
		this.maxItems = maxItems;
	}
	
	public B ids(List<String> ids) {
		assert (ids != null);
		assert (ids.size() <= maxItems);
		String idsParameter = String.join(",", ids);
		return query("ids", idsParameter);
	}

}
