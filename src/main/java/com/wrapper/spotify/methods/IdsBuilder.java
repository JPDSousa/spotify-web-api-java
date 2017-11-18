package com.wrapper.spotify.methods;

import java.util.List;
import java.util.function.Function;

@SuppressWarnings("javadoc")
public class IdsBuilder<T> extends AbstractBuilder<IdsBuilder<T>, List<T>> {

	private final String path;
	
	public IdsBuilder(String path, Function<IdsBuilder<T>, Request<List<T>>> builder) {
		super(builder);
		this.path = path;
	}
	
	public IdsBuilder<T> id(List<String> ids) {
		assert (ids != null);
		String idsParameter = String.join(",", ids);
		path(path);
		return parameter("ids", idsParameter);
	}

}
