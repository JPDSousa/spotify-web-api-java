package com.wrapper.spotify.models.playlist;

import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.image.DefaultImageHolder;
import com.wrapper.spotify.models.user.User;

abstract class AbstractPlaylist extends DefaultImageHolder {

	private boolean collaborative;
	private User owner;
	private String name;
	private boolean publicAccess;
	
	protected AbstractPlaylist(String id) {
		super(SpotifyEntityType.PLAYLIST, id);
	}
	
	public boolean isCollaborative() {
		return collaborative;
	}

	public void setCollaborative(boolean collaborative) {
		this.collaborative = collaborative;
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

}
