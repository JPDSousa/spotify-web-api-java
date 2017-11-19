package com.wrapper.spotify.methods.tracks;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.DefaultBuilder;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

@SuppressWarnings("javadoc")
public class TrackRequest extends AbstractRequest<Track> {

	public static DefaultBuilder<Track> builder(String trackId) {
		return new DefaultBuilder<>(joinPath(TRACKS, trackId), TrackRequest::new);
	}
	
	public TrackRequest(DefaultBuilder<Track> builder) {
		super(new TrackJsonFactory(), builder);
	}

}
