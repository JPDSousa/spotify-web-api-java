package com.wrapper.spotify.methods.albums;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.methods.AbstractIdsBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.BuilderUtils;
import com.wrapper.spotify.methods.MarketBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.album.Album;
import com.wrapper.spotify.models.album.AlbumJsonFactory;

import java.util.List;

@SuppressWarnings("javadoc")
public class AlbumsRequest extends AbstractRequest<List<Album>> {

	public static Builder builder() {
		return new Builder();
	}
	
	public AlbumsRequest(Builder builder) {
		super(new ListJsonFactory<>("albums", new AlbumJsonFactory()), builder);
	}
	
	public static final class Builder extends AbstractIdsBuilder<Builder, List<Album>> implements MarketBuilder<Builder> {

		public Builder() {
			super(20, ALBUMS, AlbumsRequest::new);
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
	
}