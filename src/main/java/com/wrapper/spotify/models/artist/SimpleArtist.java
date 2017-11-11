package com.wrapper.spotify.models.artist;

import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.SpotifyModel;

@SuppressWarnings("javadoc")
public class SimpleArtist extends SpotifyModel {

	private String name;

	public SimpleArtist(String id) {
		super(SpotifyEntityType.ARTIST, id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
