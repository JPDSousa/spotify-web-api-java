package com.wrapper.spotify.methods.audiofeatures;

import java.util.List;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdsBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.audio.AudioFeature;
import com.wrapper.spotify.models.audio.AudioFeatureJsonFactory;

@SuppressWarnings("javadoc")
public class AudioFeaturesRequest extends AbstractRequest<List<AudioFeature>> {

	public static final int MAX_IDS = 100;

	public static IdsBuilder<List<AudioFeature>> builder() {
		return new IdsBuilder<>(MAX_IDS, AUDIO_FEATURES, AudioFeaturesRequest::new);
	}
	
	public AudioFeaturesRequest(IdsBuilder<List<AudioFeature>> builder) {
		super(new ListJsonFactory<>("audio_features", new AudioFeatureJsonFactory()), builder);
	}

}
