package com.wrapper.spotify.models.playlist;

import com.wrapper.spotify.models.page.PageJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class PlaylistJsonFactory extends AbstractPlaylistJsonFactory<Playlist> {
	
	private final PageJsonFactory<PlaylistTrack> pageFactory;
	
	public PlaylistJsonFactory() {
		pageFactory = new PageJsonFactory<>("tracks", new PlaylistTrackJsonFactory());
	}
	
	@Override
	public Playlist fromJson(JSONObject jsonObject) {
		final Playlist playlist = new Playlist(getId(jsonObject));
		fillObject(playlist, jsonObject);
		
		return playlist;
	}

	@Override
	protected void fillObject(Playlist baseObject, JSONObject jsonObject) {
		super.fillObject(baseObject, jsonObject);
		if (existsAndNotNull("tracks", jsonObject)) {
			baseObject.setTracks(pageFactory.fromJson(jsonObject));
		}
	}
	
}
