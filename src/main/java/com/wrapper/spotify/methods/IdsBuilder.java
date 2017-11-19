package com.wrapper.spotify.methods;

import java.util.List;
import java.util.function.Function;

@SuppressWarnings("javadoc")
public class IdsBuilder<T> extends AbstractBuilder<IdsBuilder<T>, T> {

	
	public IdsBuilder(String path, Function<IdsBuilder<T>, Request<T>> builder) {
		super(path, builder);
	}
	
	public IdsBuilder<T> id(List<String> ids) {
		assert (ids != null);
		String idsParameter = String.join(",", ids);
		return parameter("ids", idsParameter);
	}

}
