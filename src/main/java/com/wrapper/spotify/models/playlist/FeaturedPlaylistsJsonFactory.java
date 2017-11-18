package com.wrapper.spotify.models.playlist;

import com.wrapper.spotify.json.AbstractJsonFactory;
import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.models.FeaturedPlaylists;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class FeaturedPlaylistsJsonFactory extends AbstractJsonFactory<FeaturedPlaylists> {

	private static final String PLAYLISTS = "playlists";
	private static final String MESSAGE = "message";
	
	private final JsonFactory<Page<SimplePlaylist>> pageFactory;
	
	public FeaturedPlaylistsJsonFactory() {
		pageFactory = new PageJsonFactory<>(new SimplePlaylistJsonFactory());
	}
	
	@Override
	public FeaturedPlaylists fromJson(JSONObject jsonObject) {
		final FeaturedPlaylists featuredPlaylists = new FeaturedPlaylists();
		featuredPlaylists.setMessage(jsonObject.getString(MESSAGE));
		featuredPlaylists.setPlaylists(pageFactory.fromJson(jsonObject.getJSONObject(PLAYLISTS)));
		return featuredPlaylists;
	}

}
