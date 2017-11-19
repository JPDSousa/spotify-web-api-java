package com.wrapper.spotify.methods.users;

import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.playlist.Playlist;
import com.wrapper.spotify.models.playlist.PlaylistJsonFactory;

@SuppressWarnings("javadoc")
public class PlaylistRequest extends AbstractRequest<Playlist> {

	public static Builder builder(String userId, String playlistId) {
		return new Builder(userId, playlistId);
	}

	public static final class Builder extends AbstractBuilder<Builder, Playlist> {

		protected Builder(String userId, String playlistId) {
			super(joinPath(USERS, userId, "playlists", playlistId), PlaylistRequest::new);
		}

		public Builder fields(String fields) {
			assert (fields != null);
			return parameter("fields", fields);
		}

	}
	
	public PlaylistRequest(Builder builder) {
		super(new PlaylistJsonFactory(), builder);
	}

}