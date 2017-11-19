package com.wrapper.spotify.methods.artists;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.DefaultBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

import java.util.List;

@SuppressWarnings("javadoc")
public class TopTracksRequest extends AbstractRequest<List<Track>> {

	public static Builder builder(String artistId) {
		return new Builder(artistId);
	}
	
	public static final class Builder extends DefaultBuilder<List<Track>> {

		public Builder(String artistId) {
			super(joinPath(ARTISTS, artistId, "toptracks"), TopTracksRequest::new);
		}

		public Builder countryCode(String countryCode) {
			assert (countryCode != null);
			parameter("country", countryCode);
			return this;
		}

	}
	
	public TopTracksRequest(DefaultBuilder<List<Track>> builder) {
		super(new ListJsonFactory<>("tracks", new TrackJsonFactory()), builder);
	}

}
