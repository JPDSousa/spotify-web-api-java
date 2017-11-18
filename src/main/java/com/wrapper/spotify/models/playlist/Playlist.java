package com.wrapper.spotify.models.playlist;

import com.wrapper.spotify.models.followers.Followers;
import com.wrapper.spotify.models.page.Page;

@SuppressWarnings("javadoc")
public class Playlist extends AbstractPlaylist {

	private String description;
	private Followers followers;
	private Page<PlaylistTrack> tracks;

	public Playlist(String id) {
		super(id);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Followers getFollowers() {
		return followers;
	}

	public void setFollowers(Followers followers) {
		this.followers = followers;
	}

	public Page<PlaylistTrack> getTracks() {
		return tracks;
	}

	public void setTracks(Page<PlaylistTrack> tracks) {
		this.tracks = tracks;
	}
}
