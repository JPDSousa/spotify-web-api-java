package com.wrapper.spotify.methods.artists;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

import java.util.List;

@SuppressWarnings("javadoc")
public class TopTracksRequest extends AbstractRequest<List<Track>> {

	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder extends IdBuilder<List<Track>> {

		public Builder() {
			super(ARTISTS + "/%s/toptracks", TopTracksRequest::new);
		}

		public Builder countryCode(String countryCode) {
			assert (countryCode != null);
			parameter("country", countryCode);
			return this;
		}

	}
	
	public TopTracksRequest(IdBuilder<List<Track>> builder) {
		super(new ListJsonFactory<>("tracks", new TrackJsonFactory()), builder);
	}

}
