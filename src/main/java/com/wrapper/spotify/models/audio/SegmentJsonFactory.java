package com.wrapper.spotify.models.audio;

import java.util.List;

import com.google.common.collect.Lists;
import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class SegmentJsonFactory extends AbstractJsonFactory<Segment> {

	private final IntervalJsonFactory intervalFactory;
	
	public SegmentJsonFactory() {
		intervalFactory = new IntervalJsonFactory();
	}
	
	@Override
	public Segment fromJson(JSONObject jsonObject) {
		final Segment segment = new Segment();
		intervalFactory.fillObject(segment, jsonObject);
		segment.setLoudnessEnd(jsonObject.getDouble("loudness_end"));
		segment.setLoudnessMax(jsonObject.getDouble("loudness_max"));
		segment.setLoudnessMaxTime(jsonObject.getDouble("loudness_max_time"));
		segment.setLoudnessStart(jsonObject.getDouble("loudness_start"));
		segment.setPitches(createDoubleArray(jsonObject.getJSONArray("pitches")));
		segment.setTimbre(createDoubleArray(jsonObject.getJSONArray("timbre")));
		return segment;
	}

	private List<Double> createDoubleArray(JSONArray jsonArray) {
		final List<Double> doubles = Lists.newArrayListWithExpectedSize(jsonArray.size());
		for(int i=0; i < jsonArray.size(); i++) {
			doubles.add(jsonArray.getDouble(i));
		}
		return doubles;
	}

}
