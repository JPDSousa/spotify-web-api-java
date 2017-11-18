package com.wrapper.spotify.models.playlist;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class PlaylistTracksInformationJsonFactory extends AbstractJsonFactory<PlaylistTracksInformation> {

	private static final String HREF = "href";
	private static final String TOTAL = "total";

	@Override
	public PlaylistTracksInformation fromJson(JSONObject jsonObject) {
		final PlaylistTracksInformation playlistTracksInformation = new PlaylistTracksInformation();
		playlistTracksInformation.setHref(jsonObject.getString(HREF));
		playlistTracksInformation.setTotal(jsonObject.getInt(TOTAL));
		
		return playlistTracksInformation;
	}

}
