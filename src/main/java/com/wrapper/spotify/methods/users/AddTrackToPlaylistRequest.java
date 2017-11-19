package com.wrapper.spotify.methods.users;

import com.wrapper.spotify.HttpManager.Method;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.SnapshotResult;
import com.wrapper.spotify.models.SnapshotResultJsonFactory;

@SuppressWarnings("javadoc")
public class AddTrackToPlaylistRequest extends AbstractRequest<SnapshotResult> {

	public AddTrackToPlaylistRequest(Builder builder) {
		super(new SnapshotResultJsonFactory(), Method.POST, builder);
	}

	public static Builder builder(String userId, String playlistId) {
		return new Builder(userId, playlistId);
	}

	public static final class Builder extends AbstractBuilder<Builder, SnapshotResult> {

		protected Builder(String userId, String playlistId) {
			super(joinPath(USERS, userId, "playlists", playlistId, "tracks"), AddTrackToPlaylistRequest::new);
			header("Content-Type", "application/json");
		}

		public Builder position(int position) {
			assert (position >= 0);

			return parameter("position", String.valueOf(position));
		}

	}

}