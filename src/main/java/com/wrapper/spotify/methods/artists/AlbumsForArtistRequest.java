package com.wrapper.spotify.methods.artists;

import static com.wrapper.spotify.methods.Paths.ARTISTS;

import com.google.common.base.Joiner;
import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.album.AlbumType;
import com.wrapper.spotify.models.album.SimpleAlbum;
import com.wrapper.spotify.models.album.SimpleAlbumJsonFactory;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class AlbumsForArtistRequest extends AbstractRequest<Page<SimpleAlbum>> {

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends IdBuilder<Page<SimpleAlbum>> {

		public Builder() {
			super(ARTISTS + "/%s/albums", AlbumsForArtistRequest::new);
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
	
	private final JsonFactory<Page<SimpleAlbum>> jsonFactory;
	
	public AlbumsForArtistRequest(IdBuilder<Page<SimpleAlbum>> builder) {
		super(builder);
		jsonFactory = new PageJsonFactory<>(new SimpleAlbumJsonFactory());
	}

	@Override
	protected Page<SimpleAlbum> fromJson(JSONObject jsonObject) {
		return jsonFactory.fromJson(jsonObject);
	}

}
