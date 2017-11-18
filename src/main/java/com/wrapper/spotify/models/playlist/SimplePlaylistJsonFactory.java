package com.wrapper.spotify.models.playlist;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class SimplePlaylistJsonFactory extends AbstractPlaylistJsonFactory<SimplePlaylist> {

	private static final String TOTAL = "total";
	private static final String TRACKS = "tracks";

	@Override
	public SimplePlaylist fromJson(JSONObject jsonObject) {
		final SimplePlaylist playlist = new SimplePlaylist(getId(jsonObject));
		fillObject(playlist, jsonObject);
		return playlist;
	}

	@Override
	protected void fillObject(SimplePlaylist baseObject, JSONObject jsonObject) {
		super.fillObject(baseObject, jsonObject);
		baseObject.setTracks(createPlaylistTracksInformation(jsonObject.getJSONObject(TRACKS)));
	}
	
	private static PlaylistTracksInformation createPlaylistTracksInformation(JSONObject tracksInformationJson) {
		final PlaylistTracksInformation playlistTracksInformation = new PlaylistTracksInformation();
		playlistTracksInformation.setHref(tracksInformationJson.getString(HREF));
		playlistTracksInformation.setTotal(tracksInformationJson.getInt(TOTAL));
		
		return playlistTracksInformation;
	}

}
