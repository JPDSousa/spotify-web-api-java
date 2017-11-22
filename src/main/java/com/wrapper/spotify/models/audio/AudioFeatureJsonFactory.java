package com.wrapper.spotify.models;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class AudioFeatureJsonFactory extends AbstractJsonFactory<AudioFeature> {

	private static final String TIME_SIGNATURE = "time_signature";
	private static final String DURATION_MS = "duration_ms";
	private static final String ANALYSIS_URL = "analysis_url";
	private static final String TRACK_HREF = "track_href";
	private static final String URI = "uri";
	private static final String ID = "id";
	private static final String TYPE = "type";
	private static final String TEMPO = "tempo";
	private static final String VALENCE = "valence";
	private static final String LIVENESS = "liveness";
	private static final String INSTRUMENTALNESS = "instrumentalness";
	private static final String ACOUSTICNESS = "acousticness";
	private static final String SPEECHINESS = "speechiness";
	private static final String MODE = "mode";
	private static final String LOUDNESS = "loudness";
	private static final String KEY = "key";
	private static final String ENERGY = "energy";
	private static final String DANCEABILITY = "danceability";

	@Override
	public AudioFeature fromJson(JSONObject jsonObject) {
		final AudioFeature audioFeature = new AudioFeature();
		audioFeature.setDanceability(jsonObject.getDouble(DANCEABILITY));
		audioFeature.setEnergy(jsonObject.getDouble(ENERGY));
		audioFeature.setKey(jsonObject.getInt(KEY));
		audioFeature.setLoudness(jsonObject.getDouble(LOUDNESS));
		audioFeature.setMode(jsonObject.getInt(MODE));
		audioFeature.setSpeechiness(jsonObject.getDouble(SPEECHINESS));
		audioFeature.setAcousticness(jsonObject.getDouble(ACOUSTICNESS));
		audioFeature.setInstrumentalness(jsonObject.getDouble(INSTRUMENTALNESS));
		audioFeature.setLiveness(jsonObject.getDouble(LIVENESS));
		audioFeature.setValence(jsonObject.getDouble(VALENCE));
		audioFeature.setTempo(jsonObject.getDouble(TEMPO));
		audioFeature.setType(jsonObject.getString(TYPE));
		audioFeature.setId(jsonObject.getString(ID));
		audioFeature.setUri(jsonObject.getString(URI));
		audioFeature.setTrackHref(jsonObject.getString(TRACK_HREF));
		audioFeature.setAnalysisUrl(jsonObject.getString(ANALYSIS_URL));
		audioFeature.setDurationMs(jsonObject.getInt(DURATION_MS));
		audioFeature.setTimeSignature(jsonObject.getInt(TIME_SIGNATURE));

		return audioFeature;
	}

}
