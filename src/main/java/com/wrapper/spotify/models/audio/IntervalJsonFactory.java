package com.wrapper.spotify.models.audio;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class IntervalJsonFactory extends AbstractJsonFactory<Interval> {
	
	private static final String START = "start";
	private static final String DURATION = "duration";
	private static final String CONFIDENCE = "confidence";

	@Override
	public Interval fromJson(JSONObject jsonObject) {
		final Interval interval = new Interval();
		fillObject(interval, jsonObject);
		return interval;
	}
	
	protected void fillObject(Interval interval, JSONObject jsonObject) {
		interval.setConfidence(jsonObject.getDouble(CONFIDENCE));
		interval.setDuration(jsonObject.getDouble(DURATION));
		interval.setStart(jsonObject.getDouble(START));
	}

}
