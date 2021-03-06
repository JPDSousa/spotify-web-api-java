package com.wrapper.spotify.methods.users;

import com.wrapper.spotify.methods.AbstractPageBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;
import com.wrapper.spotify.models.playlist.PlaylistTrack;
import com.wrapper.spotify.models.playlist.PlaylistTrackJsonFactory;

@SuppressWarnings("javadoc")
public class PlaylistTracksRequest extends AbstractRequest<Page<PlaylistTrack>> {

	public static final class Builder extends AbstractPageBuilder<Builder, Page<PlaylistTrack>> {

		protected Builder(String userId, String playlistId) {
			super(String.join("/", USERS, userId, "playlists", playlistId, "tracks"), PlaylistTracksRequest::new);
		}

		public Builder fields(String fields) {
			assert (fields != null);
			return query("fields", fields);
		}

	}
	
	public PlaylistTracksRequest(Builder builder) {
		super(new PageJsonFactory<>(null, new PlaylistTrackJsonFactory()), builder);
	}

	public static Builder builder(String userId, String playlistId) {
		return new Builder(userId, playlistId);
	}
	
}
