package com.wrapper.spotify.methods.tracks;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.methods.AbstractIdsBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.BuilderUtils;
import com.wrapper.spotify.methods.MarketBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

import java.util.List;

@SuppressWarnings("javadoc")
public class TracksRequest extends AbstractRequest<List<Track>> {

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder extends AbstractIdsBuilder<Builder, List<Track>> implements MarketBuilder<Builder> {

		protected Builder() {
			super(50, TRACKS, TracksRequest::new);
		}

		@Override
		public Builder market(CountryCode market) {
			return BuilderUtils.market(this, market);
		}

		@Override
		public Builder marketFromToken() {
			return BuilderUtils.marketFromToken(this);
		}
		
	}
	
	public TracksRequest(Builder builder) {
		super(new ListJsonFactory<>("tracks", new TrackJsonFactory()), builder);
	}

}
