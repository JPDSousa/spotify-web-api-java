package com.wrapper.spotify.methods.users;

import com.wrapper.spotify.methods.AbstractPageBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;
import com.wrapper.spotify.models.playlist.SimplePlaylist;
import com.wrapper.spotify.models.playlist.SimplePlaylistJsonFactory;

@SuppressWarnings("javadoc")
public class UserPlaylistsRequest extends AbstractRequest<Page<SimplePlaylist>> {

	public static Builder builder(String userId) {
		return new Builder(userId);
	}

	public static final class Builder extends AbstractPageBuilder<Builder, Page<SimplePlaylist>> {

		protected Builder(String userId) {
			super(joinPath(USERS, userId, "playlists"), UserPlaylistsRequest::new);
		}

		public Builder accessToken(String accessToken) {
			return header("Authorization", "Bearer " + accessToken);
		}

	}
	
	public UserPlaylistsRequest(Builder builder) {
		super(new PageJsonFactory<>(null, new SimplePlaylistJsonFactory()), builder);
	}
}
