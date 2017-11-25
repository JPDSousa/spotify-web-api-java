package com.wrapper.spotify.methods.artists;

import com.google.common.base.Joiner;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.BuilderUtils;
import com.wrapper.spotify.methods.MarketBuilder;
import com.wrapper.spotify.methods.PageBuilder;
import com.wrapper.spotify.models.album.AlbumType;
import com.wrapper.spotify.models.album.SimpleAlbum;
import com.wrapper.spotify.models.album.SimpleAlbumJsonFactory;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;

@SuppressWarnings("javadoc")
public class AlbumsForArtistRequest extends AbstractRequest<Page<SimpleAlbum>> {

	public static Builder builder(String artistId) {
		return new Builder(artistId);
	}

	public static class Builder extends AbstractBuilder<Builder, Page<SimpleAlbum>> implements MarketBuilder<Builder>, PageBuilder<Builder> {

		public Builder(String artistId) {
			super(joinPath(ARTISTS, artistId, "albums"), AlbumsForArtistRequest::new);
		}

		public Builder types(AlbumType... types) {
			assert (types != null);
			assert (types.length > 0);
			String albumsParameter = Joiner.on(",").join(types);
			parameter("album_type", albumsParameter);
			return this;
		}

		@Override
		public Builder market(CountryCode market) {
			return BuilderUtils.market(this, market);
		}

		@Override
		public Builder limit(int limit) {
			return BuilderUtils.limit(this, limit);
		}

		@Override
		public Builder offset(int offset) {
			return BuilderUtils.offset(this, offset);
		}

		@Override
		public Builder marketFromToken() {
			return BuilderUtils.marketFromToken(this);
		}

	}
	
	public AlbumsForArtistRequest(Builder builder) {
		super(new PageJsonFactory<>(null, new SimpleAlbumJsonFactory()), builder);
	}

}
