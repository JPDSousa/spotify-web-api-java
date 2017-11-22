package com.wrapper.spotify.methods;

import com.neovisionaries.i18n.CountryCode;

@SuppressWarnings("javadoc")
public class BuilderUtils {

	public static <B extends AbstractBuilder<B, ?>> B limit(B builder, int limit) {
		assert (limit > 0);
		return builder.parameter("limit", String.valueOf(limit));
	}
	
	public static <B extends AbstractBuilder<B, ?>> B offset(B builder, int offset) {
		assert (offset >= 0);
		return builder.parameter("offset", String.valueOf(offset));
	}
	
	public static <B extends AbstractBuilder<B, ?>> B market(B builder, CountryCode market) {
		assert (market != null);
		return market(builder, market.name());
	}
	
	public static <B extends AbstractBuilder<B, ?>> B marketFromToken(B builder) {
		return market(builder, "from_token");
	}
	
	private static <B extends AbstractBuilder<B, ?>> B market(B builder, String value) {
		return builder.parameter("marker", value);
	}
	
	public static <B extends AbstractBuilder<B, ?>> B country(B builder, CountryCode country) {
		assert (country != null);
		return builder.parameter("country", country.name());
	}
	
}
