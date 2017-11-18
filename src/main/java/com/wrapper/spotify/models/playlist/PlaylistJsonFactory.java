package com.wrapper.spotify.models.playlist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;
import com.wrapper.spotify.models.user.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class PlaylistJsonFactory extends AbstractPlaylistJsonFactory<Playlist> {
	
	private static final String ADDED_AT = "added_at";
	private static final String ADDED_BY = "added_by";
	private static final String TRACK = "track";
	
	private final TrackJsonFactory trackFactory;
	private final String dateFormat;
	private final TimeZone timeZone;
	
	private final PageJsonFactory<PlaylistTrack> pageFactory;
	
	public PlaylistJsonFactory() {
		trackFactory = new TrackJsonFactory();
		dateFormat = "yyyy-MM-dd'T'hh:mm:ss";
		timeZone = TimeZone.getTimeZone("GMT");
		pageFactory = new PageJsonFactory<>();
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
			baseObject.setTracks(createPlaylistTrackPage(jsonObject.getJSONObject("tracks")));
		}
	}
	
	private Page<PlaylistTrack> createPlaylistTrackPage(JSONObject playlistTrackPageJson) {
		final Page<PlaylistTrack> returnedPage = pageFactory.fromJson(playlistTrackPageJson);
		returnedPage.setItems(createPlaylistTracks(playlistTrackPageJson.getJSONArray("items")));
		return returnedPage;
	}
	
	private List<PlaylistTrack> createPlaylistTracks(JSONArray playlistTrackPageJson) {
		final List<PlaylistTrack> returnedPlaylistTracks = new ArrayList<PlaylistTrack>();
		for (int i = 0; i < playlistTrackPageJson.size(); i++) {
			returnedPlaylistTracks.add(createPlaylistTrack(playlistTrackPageJson.getJSONObject(i)));
		}
		return returnedPlaylistTracks;
	}
	
	private PlaylistTrack createPlaylistTrack(JSONObject playlistTrackJson) {
		final PlaylistTrack returnedPlaylistTrack = new PlaylistTrack();
		try {
			returnedPlaylistTrack.setAddedAt(createDate(playlistTrackJson.getString(ADDED_AT)));
		} catch (ParseException e) {
			returnedPlaylistTrack.setAddedAt(null);
		}
		try {
			final User user = userFactory.fromJson(playlistTrackJson.getJSONObject(ADDED_BY));
			returnedPlaylistTrack.setAddedBy(user);
		} catch (JSONException e) {
			returnedPlaylistTrack.setAddedBy(null);
		}
		final Track track = trackFactory.fromJson(playlistTrackJson.getJSONObject(TRACK));
		returnedPlaylistTrack.setTrack(track);
		return returnedPlaylistTrack;
	}
	
	private Date createDate(String dateString) throws ParseException {
		final SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		formatter.setTimeZone(timeZone);
		return formatter.parse(dateString);
	}
	
}
