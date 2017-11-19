package com.wrapper.spotify.methods.search;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.SearchBuilder;
import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

@SuppressWarnings("javadoc")
public class TrackSearchRequest extends AbstractRequest<Page<Track>> {

	public static SearchBuilder<Page<Track>> builder() {
		return new SearchBuilder<>(SpotifyEntityType.TRACK, TrackSearchRequest::new);
	}
	
	public TrackSearchRequest(SearchBuilder<Page<Track>> builder) {
		super(new PageJsonFactory<>(new TrackJsonFactory()), builder);
	}

}
