package com.wrapper.spotify.models.artist;

import static com.wrapper.spotify.models.image.ImageHolderJsonFactory.*;

import com.wrapper.spotify.json.SpotifyModelJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class ArtistJsonFactory extends SpotifyModelJsonFactory<Artist> {
	
	private final SimpleArtistJsonFactory simpleArtistFactory;

	public ArtistJsonFactory() {
		super();
		this.simpleArtistFactory = new SimpleArtistJsonFactory();
	}

	@Override
	public Artist fromJson(JSONObject jsonObject) {
		final Artist artist = new Artist(getId(jsonObject));
		fillObject(artist, jsonObject);
		return artist;
	}

	@Override
	protected void fillObject(Artist baseObject, JSONObject jsonObject) {
		simpleArtistFactory.fillObject(baseObject, jsonObject);
		super.fillObject(baseObject, jsonObject);
		baseObject.setGenres(getGenres(jsonObject));
		baseObject.setFollowers(getFollowers(jsonObject));
		baseObject.setImages(createImages(jsonObject.getJSONArray("images")));
		baseObject.setPopularity(jsonObject.getInt("popularity"));
	}
	
}
