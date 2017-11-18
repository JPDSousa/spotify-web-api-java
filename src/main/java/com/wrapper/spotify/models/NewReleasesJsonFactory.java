package com.wrapper.spotify.models;

import com.wrapper.spotify.json.AbstractJsonFactory;
import com.wrapper.spotify.models.album.SimpleAlbum;
import com.wrapper.spotify.models.album.SimpleAlbumJsonFactory;
import com.wrapper.spotify.models.page.PageJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class NewReleasesJsonFactory extends AbstractJsonFactory<NewReleases> {

	private static final String ALBUMS = "albums";
	
	private final PageJsonFactory<SimpleAlbum> pageJsonFactory;
	
	public NewReleasesJsonFactory() {
		pageJsonFactory = new PageJsonFactory<>(new SimpleAlbumJsonFactory());
	}

	@Override
	public NewReleases fromJson(JSONObject jsonObject) {
		final NewReleases newReleases = new NewReleases();
		newReleases.setAlbums(pageJsonFactory.fromJson(jsonObject.getJSONObject(ALBUMS)));
		return newReleases;
	}

}
