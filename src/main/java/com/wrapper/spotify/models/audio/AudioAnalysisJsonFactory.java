package com.wrapper.spotify.models.audio;

import com.wrapper.spotify.json.AbstractJsonFactory;
import com.wrapper.spotify.json.JsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class AudioAnalysisJsonFactory extends AbstractJsonFactory<AudioAnalysis> {

	private static final String TRACK = "track";
	private static final String TATUMS = "tatums";
	private static final String SECTIONS = "sections";
	private static final String META = "meta";
	private static final String BEATS = "beats";
	private static final String BARS = "bars";

	private JsonFactory<Interval> intervalFactory;
	private JsonFactory<Metadata> metadataFactory;
	private JsonFactory<Section> sectionFactory;
	private JsonFactory<TrackAnalysis> trackFactory;
	
	public AudioAnalysisJsonFactory() {
		intervalFactory = new IntervalJsonFactory();
		metadataFactory = new MetadataJsonFactory();
		sectionFactory = new SectionJsonFactory();
		trackFactory = new TrackAnalysisJsonFactory();
	}
	
	@Override
	public AudioAnalysis fromJson(JSONObject jsonObject) {
		final AudioAnalysis audio = new AudioAnalysis();
		audio.setBars(intervalFactory.fromJson(jsonObject.getJSONArray(BARS)));
		audio.setBeats(intervalFactory.fromJson(jsonObject.getJSONArray(BEATS)));
		audio.setMeta(metadataFactory.fromJson(jsonObject.getJSONObject(META)));
		audio.setSections(sectionFactory.fromJson(jsonObject.getJSONArray(SECTIONS)));
		audio.setTatums(intervalFactory.fromJson(jsonObject.getJSONArray(TATUMS)));
		audio.setTrack(trackFactory.fromJson(jsonObject.getJSONObject(TRACK)));
		return audio;
	}

}
