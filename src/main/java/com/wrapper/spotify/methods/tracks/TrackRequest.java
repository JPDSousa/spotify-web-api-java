package com.wrapper.spotify.methods.tracks;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

@SuppressWarnings("javadoc")
public class TrackRequest extends AbstractRequest<Track> {

	public static IdBuilder<Track> builder() {
		return new IdBuilder<>(TRACKS + "/%s", TrackRequest::new);
	}
	
	public TrackRequest(IdBuilder<Track> builder) {
		super(new TrackJsonFactory(), builder);
	}

}
