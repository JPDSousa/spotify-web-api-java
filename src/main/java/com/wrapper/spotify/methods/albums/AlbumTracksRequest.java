package com.wrapper.spotify.methods.albums;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.methods.AbstractPageBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.BuilderUtils;
import com.wrapper.spotify.methods.MarketBuilder;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;
import com.wrapper.spotify.models.track.SimpleTrack;
import com.wrapper.spotify.models.track.SimpleTrackJsonFactory;

@SuppressWarnings("javadoc")
public class AlbumTracksRequest extends AbstractRequest<Page<SimpleTrack>> {

	public static final Builder builder(String albumId) {
		return new Builder(albumId);
	}

	public static final class Builder extends AbstractPageBuilder<Builder, Page<SimpleTrack>> implements MarketBuilder<Builder> {

		protected Builder(String albumId) {
			super(joinPath(ALBUMS, albumId, "tracks"), AlbumTracksRequest::new);
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
	
	public AlbumTracksRequest(Builder builder) {
		super(new PageJsonFactory<>(new SimpleTrackJsonFactory()), builder);
	}

}
