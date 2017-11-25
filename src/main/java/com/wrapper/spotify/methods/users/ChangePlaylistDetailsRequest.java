/**
 * Copyright (C) 2017 Spotify AB
 */
package com.wrapper.spotify.methods.users;

import com.google.common.collect.Maps;
import com.wrapper.spotify.HttpManager.Method;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.Request;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.Map;

@SuppressWarnings("javadoc")
public class ChangePlaylistDetailsRequest extends AbstractRequest<String> {

	public static Builder builder(String userId, String playlistId) {
		return new Builder(userId, playlistId);
	}

	public static final class Builder extends AbstractBuilder<Builder, String> {

		private final Map<String, Object> properties;
		
		protected Builder(String userId, String playlistId) {
			super(joinPath(USERS, userId, "playlists", playlistId), ChangePlaylistDetailsRequest::new);
			this.properties = Maps.newHashMap();
			header("Content-Type", "application/json");
		}

		public Builder name(String name) {
			assert (name != null);
			properties.put("name", name);
			return this;
		}

		public Builder publicAccess(boolean isPublic) {
			properties.put("public", isPublic);
			return this;
		}

		@Override
		public Request<String> build() {
			body(JSONObject.fromObject(properties));
			return super.build();
		}

	}
	
	public ChangePlaylistDetailsRequest(Builder builder) {
		super(null, Method.PUT, builder);
	}

	@Override
	public String exec() throws IOException, WebApiException {
		return putJson();
	}

}
