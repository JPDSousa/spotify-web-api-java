package com.wrapper.spotify.models;

import com.wrapper.spotify.models.album.SimpleAlbum;
import com.wrapper.spotify.models.page.Page;

@SuppressWarnings("javadoc")
public class NewReleases {
	
	private Page<SimpleAlbum> albums;

	public Page<SimpleAlbum> getAlbums() {
		return albums;
	}

	public void setAlbums(Page<SimpleAlbum> albums) {
		this.albums = albums;
	}
}
