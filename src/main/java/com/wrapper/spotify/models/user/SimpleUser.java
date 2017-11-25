package com.wrapper.spotify.models.user;

import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.SpotifyModel;

@SuppressWarnings("javadoc")
public class SimpleUser extends SpotifyModel {

	public SimpleUser(String id) {
		super(SpotifyEntityType.USER, id);
	}

}
