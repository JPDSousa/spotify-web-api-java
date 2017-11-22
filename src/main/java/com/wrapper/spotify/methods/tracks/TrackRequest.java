package com.wrapper.spotify.methods.tracks;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.BuilderUtils;
import com.wrapper.spotify.methods.MarketBuilder;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

@SuppressWarnings("javadoc")
public class TrackRequest extends AbstractRequest<Track> {

	public static Builder builder(String trackId) {
		return new Builder(trackId);
	}
	
	public static class Builder extends AbstractBuilder<Builder, Track> implements MarketBuilder<Builder> {

		protected Builder(String trackId) {
			super(joinPath(TRACKS, trackId), TrackRequest::new);
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
	
	public TrackRequest(Builder builder) {
		super(new TrackJsonFactory(), builder);
	}

}
