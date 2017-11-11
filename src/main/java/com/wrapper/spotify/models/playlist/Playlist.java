package com.wrapper.spotify.models.playlist;

import com.wrapper.spotify.models.Followers;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.image.DefaultImageHolder;
import com.wrapper.spotify.models.user.User;

@SuppressWarnings("javadoc")
public class Playlist extends DefaultImageHolder {

	private boolean collaborative;
	private String description;
	private Followers followers;
	private String name;
	private User owner;
	private boolean publicAccess;
	private Page<PlaylistTrack> tracks;

	public Playlist(String id) {
		super(SpotifyEntityType.PLAYLIST, id);
	}

	public boolean isCollaborative() {
		return collaborative;
	}

	public void setCollaborative(boolean collaborative) {
		this.collaborative = collaborative;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPublicAccess() {
		return publicAccess;
	}

	public void setPublicAccess(boolean publicAccess) {
		this.publicAccess = publicAccess;
	}

	public Page<PlaylistTrack> getTracks() {
		return tracks;
	}

	public void setTracks(Page<PlaylistTrack> tracks) {
		this.tracks = tracks;
	}
}
