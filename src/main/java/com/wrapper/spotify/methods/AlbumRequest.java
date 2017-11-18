package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.album.Album;

import net.sf.json.JSONObject;

import java.io.IOException;

@SuppressWarnings("javadoc")
public class AlbumRequest extends AbstractRequest<Album> {

	public AlbumRequest(Builder builder) {
		super(builder);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder extends AbstractRequest.Builder<Builder> {

		/**
		 * The album with the given id.
		 *
		 * @param id The id for the album.
		 * @return AlbumRequest
		 */
		public Builder id(String id) {
			assert (id != null);
			return path(String.format("/v1/albums/%s", id));
		}

		@Override
		public AlbumRequest build() {
			return new AlbumRequest(this);
		}

	}

}
