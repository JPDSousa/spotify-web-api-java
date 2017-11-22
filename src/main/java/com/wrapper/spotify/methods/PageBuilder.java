package com.wrapper.spotify.methods;

@SuppressWarnings("javadoc")
public interface PageBuilder<B extends PageBuilder<B>> {

	B limit(int limit);
	
	B offset(int offset);
}
