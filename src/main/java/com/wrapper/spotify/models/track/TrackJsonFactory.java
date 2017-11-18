package com.wrapper.spotify.models.track;

import com.wrapper.spotify.json.SpotifyModelJsonFactory;
import com.wrapper.spotify.models.album.SimpleAlbumJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class TrackJsonFactory extends SpotifyModelJsonFactory<Track> {
	
	private static final String POPULARITY = "popularity";
	private static final String ALBUM = "album";
	
	private final SimpleTrackJsonFactory innerFactory;
	private final SimpleAlbumJsonFactory albumFactory;
	
	public TrackJsonFactory() {
		super();
		this.innerFactory = new SimpleTrackJsonFactory();
		albumFactory = new SimpleAlbumJsonFactory();
	}

	@Override
	public Track fromJson(JSONObject jsonObject) {
		final Track track = new Track(getId(jsonObject));
		fillObject(track, jsonObject);
		return track;
	}

	@Override
	protected void fillObject(Track baseObject, JSONObject jsonObject) {
		super.fillObject(baseObject, jsonObject);
		innerFactory.fillObject(baseObject, jsonObject);
		baseObject.setAlbum(albumFactory.fromJson(jsonObject.getJSONObject(ALBUM)));
		baseObject.setExternalIds(getExternalIds(jsonObject));
		baseObject.setPopularity(jsonObject.getInt(POPULARITY));
	}

}
