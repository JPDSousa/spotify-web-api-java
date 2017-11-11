package com.wrapper.spotify.models.track;

import com.wrapper.spotify.models.ExternalIds;
import com.wrapper.spotify.models.album.SimpleAlbum;

@SuppressWarnings("javadoc")
public class Track extends SimpleTrack {

	private SimpleAlbum album;
	private ExternalIds externalIds;
	private int popularity;

	public Track(String id) {
		super(id);
	}

	public SimpleAlbum getAlbum() {
		return album;
	}

	public void setAlbum(SimpleAlbum album) {
		this.album = album;
	}

	public ExternalIds getExternalIds() {
		return externalIds;
	}

	public void setExternalIds(ExternalIds externalIds) {
		this.externalIds = externalIds;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

}
