package com.wrapper.spotify.models.user;

import com.wrapper.spotify.json.SpotifyModelJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class SimpleUserJsonFactory extends SpotifyModelJsonFactory<SimpleUser> {

	@Override
	public SimpleUser fromJson(JSONObject jsonObject) {
		final SimpleUser user = new SimpleUser(getId(jsonObject));
		fillObject(user, jsonObject);
		return user;
	}

}
