package com.wrapper.spotify.methods.recommendation;

import java.util.List;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.BuilderUtils;
import com.wrapper.spotify.methods.MarketBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.audio.AudioProperty;
import com.wrapper.spotify.models.track.SimpleTrack;
import com.wrapper.spotify.models.track.SimpleTrackJsonFactory;

@SuppressWarnings("javadoc")
public class RecommendationRequest extends AbstractRequest<List<SimpleTrack>> {

	public static final Builder builder() {
		return new Builder();
	}
	
	public static final class Builder extends AbstractBuilder<Builder, List<SimpleTrack>> implements MarketBuilder<Builder> {

		private static final int TOTAL_SEEDS = 5;
		private int trackSeeds;
		private int artistSeeds;
		private int genreSeeds;
		
		protected Builder() {
			super(RECOMMENDATIONS, RecommendationRequest::new);
			trackSeeds = 0;
			artistSeeds = 0;
			genreSeeds = 0;
		}
		
		private int nSeeds() {
			return trackSeeds + artistSeeds + genreSeeds;
		}

		@Override
		public Builder market(CountryCode market) {
			return BuilderUtils.market(this, market);
		}

		@Override
		public Builder marketFromToken() {
			return BuilderUtils.marketFromToken(this);
		}
		
		public Builder limit(int limit) {
			return BuilderUtils.limit(this, limit);
		}
		
		public Builder trackSeeds(List<String> ids) {
			assert nSeeds() + ids.size() < TOTAL_SEEDS;
			trackSeeds = ids.size();
			return parameter("seed_tracks", String.join(",", ids));
		}
		
		public Builder genreSeeds(List<String> ids) {
			assert nSeeds() + ids.size() < TOTAL_SEEDS;
			genreSeeds = ids.size();
			return parameter("seed_genres", String.join(",", ids));
		}
		
		public Builder artistSeeds(List<String> ids) {
			assert nSeeds() + ids.size() < TOTAL_SEEDS;
			artistSeeds = ids.size();
			return parameter("seed_artists", String.join(",", ids));
		}
		
		public Builder min(AudioProperty prop, Number value) {
			return trackValue(prop, "min_", value);
		}
		
		public Builder max(AudioProperty prop, Number value) {
			return trackValue(prop, "max_", value);
		}
		
		public Builder target(AudioProperty prop, Number value) {
			return trackValue(prop, "target_", value);
		}
		
		private Builder trackValue(AudioProperty prop, String preName, Number value) {
			assert prop != null;
			assert prop.isValid(value.floatValue());
			return parameter(preName + prop.getName(), value.toString());
		}
		
	}
	
	public RecommendationRequest(Builder builder) {
		super(new ListJsonFactory<>("tracks", new SimpleTrackJsonFactory()), builder);
	}

}
