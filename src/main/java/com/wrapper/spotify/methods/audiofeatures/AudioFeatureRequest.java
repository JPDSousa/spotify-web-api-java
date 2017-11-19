package com.wrapper.spotify.methods.audiofeatures;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.DefaultBuilder;
import com.wrapper.spotify.models.AudioFeature;
import com.wrapper.spotify.models.AudioFeatureJsonFactory;

@SuppressWarnings("javadoc")
public class AudioFeatureRequest extends AbstractRequest<AudioFeature> {

	public static DefaultBuilder<AudioFeature> builder(String trackId) {
		return new DefaultBuilder<>(joinPath(AUDIO_FEATURES, trackId), AudioFeatureRequest::new);
	}
	
	public AudioFeatureRequest(DefaultBuilder<AudioFeature> builder) {
		super(new AudioFeatureJsonFactory(), builder);
	}

}
