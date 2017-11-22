package com.wrapper.spotify.methods;

import com.neovisionaries.i18n.CountryCode;

@SuppressWarnings("javadoc")
public interface CountryBuilder<B extends CountryBuilder<B>> {

	B country(CountryCode code);
}
