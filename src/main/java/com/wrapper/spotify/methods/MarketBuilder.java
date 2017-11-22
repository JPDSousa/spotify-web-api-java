package com.wrapper.spotify.methods;

import com.neovisionaries.i18n.CountryCode;

@SuppressWarnings("javadoc")
public interface MarketBuilder<B extends MarketBuilder<B>> {
	
	B market(CountryCode market);
	
	B marketFromToken();

}
