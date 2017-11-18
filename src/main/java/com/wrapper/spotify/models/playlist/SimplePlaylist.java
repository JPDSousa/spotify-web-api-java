package com.wrapper.spotify.models.playlist;

@SuppressWarnings("javadoc")
public class SimplePlaylist extends AbstractPlaylist {

	private PlaylistTracksInformation tracks;
	
	public SimplePlaylist(String id) {
		super(id);
	}

	public PlaylistTracksInformation getTracks() {
		return tracks;
	}

	public void setTracks(PlaylistTracksInformation tracks) {
		this.tracks = tracks;
	}

}
