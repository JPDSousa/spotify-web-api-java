package com.wrapper.spotify.methods.audiofeatures;

import static com.wrapper.spotify.methods.Paths.AUDIO_FEATURES;

import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.AudioFeature;
import com.wrapper.spotify.models.AudioFeatureJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class AudioFeatureRequest extends AbstractRequest<AudioFeature> {

	public static IdBuilder<AudioFeature> builder() {
		return new IdBuilder<>(AUDIO_FEATURES + "/%s", AudioFeatureRequest::new);
	}
	
	private final JsonFactory<AudioFeature> jsonFactory;

	public AudioFeatureRequest(IdBuilder<AudioFeature> builder) {
		super(builder);
		jsonFactory = new AudioFeatureJsonFactory();
	}

	@Override
	protected AudioFeature fromJson(JSONObject json) {
		return jsonFactory.fromJson(json);
	}

}
