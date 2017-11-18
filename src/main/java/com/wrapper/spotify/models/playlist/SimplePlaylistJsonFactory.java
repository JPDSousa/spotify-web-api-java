package com.wrapper.spotify.models.playlist;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class SimplePlaylistJsonFactory extends AbstractPlaylistJsonFactory<SimplePlaylist> {

	private static final String TRACKS = "tracks";
	
	private final PlaylistTracksInformationJsonFactory tracksFactory;
	
	public SimplePlaylistJsonFactory() {
		tracksFactory = new PlaylistTracksInformationJsonFactory();
	}

	@Override
	public SimplePlaylist fromJson(JSONObject jsonObject) {
		final SimplePlaylist playlist = new SimplePlaylist(getId(jsonObject));
		fillObject(playlist, jsonObject);
		return playlist;
	}

	@Override
	protected void fillObject(SimplePlaylist baseObject, JSONObject jsonObject) {
		super.fillObject(baseObject, jsonObject);
		baseObject.setTracks(tracksFactory.fromJson(jsonObject.getJSONObject(TRACKS)));
	}
	
}
