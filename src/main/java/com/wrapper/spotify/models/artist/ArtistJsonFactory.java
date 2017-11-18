package com.wrapper.spotify.models.artist;

import static com.wrapper.spotify.models.image.ImageHolderJsonFactory.*;

import com.wrapper.spotify.models.Followers;
import com.wrapper.spotify.models.json.SpotifyModelJsonFactory;

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
		baseObject.setFollowers(createFollowers(jsonObject.getJSONObject("followers")));
		baseObject.setImages(createImages(jsonObject.getJSONArray("images")));
		baseObject.setName(jsonObject.getString("name"));
		baseObject.setPopularity(jsonObject.getInt("popularity"));
	}
	
	private static Followers createFollowers(JSONObject followers) {
		final Followers returnedFollowers = new Followers();
		if (existsAndNotNull(HREF, followers)) {
			returnedFollowers.setHref(followers.getString("href"));
		}
		if (existsAndNotNull("total", followers)) {
			returnedFollowers.setTotal(followers.getInt("total"));
		}
		return returnedFollowers;
	}
	

}
