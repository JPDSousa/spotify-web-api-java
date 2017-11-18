package com.wrapper.spotify.models.album;

import com.wrapper.spotify.models.image.ImageHolderJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class SimpleAlbumJsonFactory extends ImageHolderJsonFactory<SimpleAlbum> {

	private static final String NAME = "name";
	private static final String ALBUM_TYPE = "album_type";

	@Override
	public SimpleAlbum fromJson(JSONObject jsonObject) {
		final SimpleAlbum simpleAlbum = new SimpleAlbum(getId(jsonObject));
		fillObject(simpleAlbum, jsonObject);
		return simpleAlbum;
	}

	@Override
	protected void fillObject(SimpleAlbum baseObject, JSONObject jsonObject) {
		super.fillObject(baseObject, jsonObject);
		baseObject.setAlbumType(createAlbumType(jsonObject.getString(ALBUM_TYPE)));
		baseObject.setName(jsonObject.getString(NAME));
		baseObject.setAvailableMarkets(getAvailableMarkets(jsonObject));
	}
	
	private AlbumType createAlbumType(String albumType) {
		if (NULL.equalsIgnoreCase(albumType)) {
			return null;
		}
		return AlbumType.valueOf(albumType.toUpperCase());
	}
	
}
