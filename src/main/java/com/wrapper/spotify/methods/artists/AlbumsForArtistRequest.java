package com.wrapper.spotify.methods.artists;

import com.google.common.base.Joiner;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.DefaultBuilder;
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

	public static class Builder extends DefaultBuilder<Page<SimpleAlbum>> {

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

		public Builder market(String market) {
			assert (market != null);
			parameter("market", market);
			return this;
		}

		public Builder limit(int limit) {
			assert (limit > 0);
			parameter("limit", String.valueOf(limit));
			return this;
		}

		public Builder offset(int offset) {
			assert (offset >= 0);
			parameter("offset", String.valueOf(offset));
			return this;
		}

	}
	
	public AlbumsForArtistRequest(DefaultBuilder<Page<SimpleAlbum>> builder) {
		super(new PageJsonFactory<>(new SimpleAlbumJsonFactory()), builder);
	}

}
