package com.wrapper.spotify.models.playlist;

import static com.wrapper.spotify.JsonUtil.createDate;
import java.text.ParseException;

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
	
	private final JsonFactory<Track> trackFactory;
	private final JsonFactory<User> userFactory;
	
	public PlaylistTrackJsonFactory() {
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

}
