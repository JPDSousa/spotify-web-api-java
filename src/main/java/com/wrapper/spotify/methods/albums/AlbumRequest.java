package com.wrapper.spotify.methods.albums;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.BuilderUtils;
import com.wrapper.spotify.methods.MarketBuilder;
import com.wrapper.spotify.models.album.Album;
import com.wrapper.spotify.models.album.AlbumJsonFactory;

@SuppressWarnings("javadoc")
public class AlbumRequest extends AbstractRequest<Album> {

	public static Builder builder(String albumId) {
		return new Builder(albumId);
	}
	
	public static class Builder extends AbstractBuilder<Builder, Album> implements MarketBuilder<Builder> {

		protected Builder(String albumId) {
			super(joinPath(ALBUMS, albumId), AlbumRequest::new);
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
	
	public AlbumRequest(Builder builder) {
		super(new AlbumJsonFactory(), builder);
	}

}
