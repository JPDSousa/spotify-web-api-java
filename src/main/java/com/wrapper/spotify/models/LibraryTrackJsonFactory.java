package com.wrapper.spotify.models;

import static com.wrapper.spotify.JsonUtil.createDate;

import java.text.ParseException;

import com.wrapper.spotify.json.AbstractJsonFactory;
import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class LibraryTrackJsonFactory extends AbstractJsonFactory<LibraryTrack> {

	private static final String ADDED_AT = "added_at";
	private static final String TRACK = "track";
	
	private final JsonFactory<Track> trackFactory;
	
	public LibraryTrackJsonFactory() {
		trackFactory = new TrackJsonFactory();
	}
	
	@Override
	public LibraryTrack fromJson(JSONObject jsonObject) {
		final LibraryTrack returnedLibraryTrack = new LibraryTrack();
		try {
			returnedLibraryTrack.setAddedAt(createDate(jsonObject.getString(ADDED_AT)));
		} catch (ParseException e) {
			returnedLibraryTrack.setAddedAt(null);
		}
		returnedLibraryTrack.setTrack(trackFactory.fromJson(jsonObject.getJSONObject(TRACK)));
		return returnedLibraryTrack;
	}

}
