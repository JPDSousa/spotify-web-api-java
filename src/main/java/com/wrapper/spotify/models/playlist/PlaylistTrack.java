package com.wrapper.spotify.models.playlist;

import java.util.Date;

import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.user.SimpleUser;

@SuppressWarnings("javadoc")
public class PlaylistTrack {

	private Date addedAt;
	private SimpleUser addedBy;
	private Track track;

	public Date getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(Date addedAt) {
		this.addedAt = addedAt;
	}

	public SimpleUser getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(SimpleUser addedBy) {
		this.addedBy = addedBy;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}
}
