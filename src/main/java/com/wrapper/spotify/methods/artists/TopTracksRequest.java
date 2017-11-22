package com.wrapper.spotify.methods.artists;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.BuilderUtils;
import com.wrapper.spotify.methods.CountryBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

import java.util.List;

@SuppressWarnings("javadoc")
public class TopTracksRequest extends AbstractRequest<List<Track>> {

	public static Builder builder(String artistId) {
		return new Builder(artistId);
	}
	
	public static final class Builder extends AbstractBuilder<Builder, List<Track>> implements CountryBuilder<Builder> {

		public Builder(String artistId) {
			super(joinPath(ARTISTS, artistId, "toptracks"), TopTracksRequest::new);
		}

		@Override
		public Builder country(CountryCode countryCode) {
			return BuilderUtils.country(this, countryCode);
		}

	}
	
	public TopTracksRequest(Builder builder) {
		super(new ListJsonFactory<>("tracks", new TrackJsonFactory()), builder);
	}

}
