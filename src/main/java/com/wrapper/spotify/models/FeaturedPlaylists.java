package com.wrapper.spotify.models;

import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.playlist.SimplePlaylist;

@SuppressWarnings("javadoc")
public class FeaturedPlaylists {

	private String message;
	private Page<SimplePlaylist> playlists;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Page<SimplePlaylist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Page<SimplePlaylist> playlists) {
		this.playlists = playlists;
	}
}
