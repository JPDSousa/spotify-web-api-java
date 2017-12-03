package com.wrapper.spotify.models.album;

@SuppressWarnings("javadoc")
public enum ReleaseDatePrecision {
	
	YEAR,
	MONTH,
	DAY;
	
	public static ReleaseDatePrecision forName(String name) {
		return valueOf(name.toUpperCase());
	}

}
