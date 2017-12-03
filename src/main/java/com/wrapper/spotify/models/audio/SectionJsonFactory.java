package com.wrapper.spotify.models.audio;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class SectionJsonFactory extends AbstractJsonFactory<Section> {

	private final IntervalJsonFactory intervalFactory;
	
	public SectionJsonFactory() {
		intervalFactory = new IntervalJsonFactory();
	}
	
	@Override
	public Section fromJson(JSONObject jsonObject) {
		final Section section = new Section();
		intervalFactory.fillObject(section, jsonObject);
		fill(section, jsonObject);
		return section;
	}
	
	protected void fill(Section section, JSONObject jsonObject) {
		section.setKey(jsonObject.getInt("key"));
		section.setKeyConfidence(jsonObject.getDouble("key_confidence"));
		section.setLoudness(jsonObject.getDouble("loudness"));
		section.setMode(jsonObject.getInt("mode"));
		section.setModeConfidence(jsonObject.getDouble("mode_confidence"));
		section.setTempo(jsonObject.getDouble("tempo"));
		section.setTempoConfidence(jsonObject.getDouble("tempo_confidence"));
		section.setTimeSignature(jsonObject.getInt("time_signature"));
		section.setTimeSignatureConfidence(jsonObject.getDouble("time_signature_confidence"));
	}
	
	

}
