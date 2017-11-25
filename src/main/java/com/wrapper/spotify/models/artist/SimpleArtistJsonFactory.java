package com.wrapper.spotify.models.artist;

import com.wrapper.spotify.json.SpotifyModelJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class SimpleArtistJsonFactory extends SpotifyModelJsonFactory<SimpleArtist> {

	private static final String NAME = "name";

	@Override
	public SimpleArtist fromJson(JSONObject jsonObject) {
		final SimpleArtist simpleArtist = new SimpleArtist(getId(jsonObject));
		fillObject(simpleArtist, jsonObject);
		return simpleArtist;
	}

	@Override
	protected void fillObject(SimpleArtist baseObject, JSONObject jsonObject) {
		super.fillObject(baseObject, jsonObject);
		baseObject.setName(jsonObject.getString(NAME));
	}
	
}
