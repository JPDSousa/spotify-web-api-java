package com.wrapper.spotify.models.track;

import java.util.List;

import com.wrapper.spotify.json.SpotifyModelJsonFactory;
import com.wrapper.spotify.models.artist.SimpleArtist;
import com.wrapper.spotify.models.artist.SimpleArtistJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class SimpleTrackJsonFactory extends SpotifyModelJsonFactory<SimpleTrack> {
	
	private static final String TRACK_NUMBER = "track_number";
	private static final String PREVIEW_URL = "preview_url";
	private static final String NAME = "name";
	private static final String EXPLICIT = "explicit";
	private static final String DURATION_MS = "duration_ms";
	private static final String DISC_NUMBER = "disc_number";
	
	private final SimpleArtistJsonFactory artistFactory;
	
	public SimpleTrackJsonFactory() {
		super();
		this.artistFactory = new SimpleArtistJsonFactory();
	}

	@Override
	public SimpleTrack fromJson(JSONObject jsonObject) {
		final SimpleTrack track = new SimpleTrack(getId(jsonObject));
		fillObject(track, jsonObject);
		
		return track;
	}

	@Override
	protected void fillObject(SimpleTrack baseObject, JSONObject jsonObject) {
		final List<SimpleArtist> artists = artistFactory.fromJson(jsonObject.getJSONArray("artists"));
		super.fillObject(baseObject, jsonObject);
		baseObject.setArtists(artists);
		baseObject.setAvailableMarkets(getAvailableMarkets(jsonObject));
		baseObject.setDiscNumber(jsonObject.getInt(DISC_NUMBER));
		baseObject.setDuration(jsonObject.getInt(DURATION_MS));
		baseObject.setExplicit(jsonObject.getBoolean(EXPLICIT));
		baseObject.setName(jsonObject.getString(NAME));
		baseObject.setPreviewUrl(jsonObject.getString(PREVIEW_URL));
		baseObject.setTrackNumber(jsonObject.getInt(TRACK_NUMBER));
	}
	
	

}
