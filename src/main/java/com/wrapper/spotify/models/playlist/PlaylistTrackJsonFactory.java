package com.wrapper.spotify.models.playlist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.wrapper.spotify.json.AbstractJsonFactory;
import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;
import com.wrapper.spotify.models.user.User;
import com.wrapper.spotify.models.user.UserJsonFactory;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class PlaylistTrackJsonFactory extends AbstractJsonFactory<PlaylistTrack> {

	private static final String ADDED_AT = "added_at";
	private static final String ADDED_BY = "added_by";
	private static final String TRACK = "track";
	
	private final String dateFormat;
	private final TimeZone timeZone;
	
	private final JsonFactory<Track> trackFactory;
	private final JsonFactory<User> userFactory;
	
	public PlaylistTrackJsonFactory() {
		dateFormat = "yyyy-MM-dd'T'hh:mm:ss";
		timeZone = TimeZone.getTimeZone("GMT");
		trackFactory = new TrackJsonFactory();
		userFactory = new UserJsonFactory();
	}
	
	@Override
	public PlaylistTrack fromJson(JSONObject jsonObject) {
		final PlaylistTrack returnedPlaylistTrack = new PlaylistTrack();
		try {
			returnedPlaylistTrack.setAddedAt(createDate(jsonObject.getString(ADDED_AT)));
		} catch (ParseException e) {
			returnedPlaylistTrack.setAddedAt(null);
		}
		try {
			final User user = userFactory.fromJson(jsonObject.getJSONObject(ADDED_BY));
			returnedPlaylistTrack.setAddedBy(user);
		} catch (JSONException e) {
			returnedPlaylistTrack.setAddedBy(null);
		}
		final Track track = trackFactory.fromJson(jsonObject.getJSONObject(TRACK));
		returnedPlaylistTrack.setTrack(track);
		return returnedPlaylistTrack;
	}
	
	private Date createDate(String dateString) throws ParseException {
		final SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		formatter.setTimeZone(timeZone);
		return formatter.parse(dateString);
	}

}
