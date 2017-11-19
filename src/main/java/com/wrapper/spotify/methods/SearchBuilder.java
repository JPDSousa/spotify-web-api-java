package com.wrapper.spotify.methods;

import java.util.function.Function;

import com.wrapper.spotify.models.SpotifyEntityType;

@SuppressWarnings("javadoc")
public class SearchBuilder<T> extends PageBuilder<SearchBuilder<T>, T> {
	
	public SearchBuilder(SpotifyEntityType type, Function<SearchBuilder<T>, Request<T>> builder) {
		super(Request.SEARCH, builder);
		parameter("type", type.getType());
	}
	
	public SearchBuilder<T> query(String query) {
		assert (query != null);
		parameter("q", encode(query));
		return this;
	}
	
	public SearchBuilder<T> market(String market) {
		assert (market != null);
		parameter("market", market);
		return this;
	}


	private String encode(String query) {
		return query.replace(" ", "+");
	}

}
