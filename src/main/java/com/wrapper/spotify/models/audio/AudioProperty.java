package com.wrapper.spotify.models.audio;

import com.google.common.collect.Range;

@SuppressWarnings("javadoc")
public enum AudioProperty {
	
	ACOUSTICNESS(Range.open(0f, 1f)),
	DANCEABILITY(Range.open(0f, 1f)),
	DURATION(Range.greaterThan(0f)),
	ENERGY(Range.open(0f, 1f)),
	INSTRUMENTALNESS(Range.open(0f, 1f)),
	KEY(Range.open(0f, 11f)),
	LIVENESS(Range.open(0f, 1f)),
	LOUDNESS(Range.open(-60f, 0f)),
	MODE(Range.open(0f, 1f)),
	POPULARITY(Range.open(0f, 100f)),
	SPEECHINESS(Range.open(0f, 1f)),
	TEMPO(Range.greaterThan(0f)),
	TIME_SIGNATURE(Range.greaterThan(0f)),
	VALENCE(Range.open(0f, 1f));
	
	private final Range<Float> range;

	private AudioProperty(Range<Float> range) {
		this.range = range;
	}

	public boolean isValid(Float value) {
		return range.contains(value);
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
