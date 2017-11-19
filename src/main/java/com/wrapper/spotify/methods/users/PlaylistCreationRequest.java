package com.wrapper.spotify.methods.users;

import com.wrapper.spotify.HttpManager.Method;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.playlist.Playlist;
import com.wrapper.spotify.models.playlist.PlaylistJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class PlaylistCreationRequest extends AbstractRequest<Playlist> {

	public static Builder builder(String userId) {
		return new Builder(userId);
	}
	
	public static final class Builder extends AbstractBuilder<Builder, Playlist> {

		protected Builder(String userId) {
			super(joinPath(USERS, userId, "playlist"), PlaylistCreationRequest::new);
		}

		private JSONObject jsonBody;

		public Builder publicAccess(boolean publicAccess) {
			if (jsonBody == null) {
				jsonBody = new JSONObject();
			}
			jsonBody.put("public",String.valueOf(publicAccess));
			
			return body(jsonBody);
		}

		public Builder title(String title) {
			if (jsonBody == null) {
				jsonBody = new JSONObject();
			}
			jsonBody.put("name", String.valueOf(title));
			
			return body(jsonBody);
		}

	}
	
	public PlaylistCreationRequest(Builder builder) {
		super(new PlaylistJsonFactory(), Method.POST, builder);
	}

}
