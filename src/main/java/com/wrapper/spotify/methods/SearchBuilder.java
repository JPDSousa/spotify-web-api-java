package com.wrapper.spotify.methods;

import java.util.function.Function;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.models.SpotifyEntityType;

@SuppressWarnings("javadoc")
public class SearchBuilder<T> extends AbstractPageBuilder<SearchBuilder<T>, T> implements MarketBuilder<SearchBuilder<T>> {
	
	public SearchBuilder(SpotifyEntityType type, Function<SearchBuilder<T>, Request<T>> builder) {
		super(Request.SEARCH, builder);
		parameter("type", type.getType());
	}
	
	public SearchBuilder<T> query(String query) {
		assert (query != null);
		parameter("q", encode(query));
		return this;
	}
	
	@Override
	public SearchBuilder<T> market(CountryCode market) {
		return BuilderUtils.market(this, market);
	}


	private String encode(String query) {
		return query.replace(" ", "+");
	}

	@Override
	public SearchBuilder<T> marketFromToken() {
		return BuilderUtils.marketFromToken(this);
	}

}
