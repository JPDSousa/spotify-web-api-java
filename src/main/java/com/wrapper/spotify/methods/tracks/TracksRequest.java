package com.wrapper.spotify.methods.tracks;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdsBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

import java.util.List;

@SuppressWarnings("javadoc")
public class TracksRequest extends AbstractRequest<List<Track>> {

	public static IdsBuilder<List<Track>> builder() {
		return new IdsBuilder<>(TRACKS, TracksRequest::new);
	}
	
	public TracksRequest(IdsBuilder<List<Track>> builder) {
		super(new ListJsonFactory<>("tracks", new TrackJsonFactory()), builder);
	}

}
