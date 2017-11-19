package com.wrapper.spotify.methods.audiofeatures;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.AudioFeature;
import com.wrapper.spotify.models.AudioFeatureJsonFactory;

@SuppressWarnings("javadoc")
public class AudioFeatureRequest extends AbstractRequest<AudioFeature> {

	public static IdBuilder<AudioFeature> builder() {
		return new IdBuilder<>(AUDIO_FEATURES + "/%s", AudioFeatureRequest::new);
	}
	
	public AudioFeatureRequest(IdBuilder<AudioFeature> builder) {
		super(new AudioFeatureJsonFactory(), builder);
	}

}
