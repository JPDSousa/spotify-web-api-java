package com.wrapper.spotify.models.album;

import java.util.ArrayList;
import java.util.List;

import com.wrapper.spotify.models.Copyright;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.artist.SimpleArtist;
import com.wrapper.spotify.models.artist.SimpleArtistJsonFactory;
import com.wrapper.spotify.models.image.ImageHolderJsonFactory;
import com.wrapper.spotify.models.track.SimpleTrack;
import com.wrapper.spotify.models.track.SimpleTrackJsonFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class AlbumJsonFactory extends ImageHolderJsonFactory<Album> {

	private static final String ITEMS = "items";
	private static final String TRACKS = "tracks";
	private static final String RELEASE_DATE_PRECISION = "release_date_precision";
	private static final String RELEASE_DATE = "release_date";
	private static final String POPULARITY = "popularity";
	private static final String COPYRIGHTS = "copyrights";
	private static final String ARTISTS = "artists";
	
	private final SimpleAlbumJsonFactory innerFactory;
	private final SimpleTrackJsonFactory trackFactory;
	private final SimpleArtistJsonFactory artistFactory;
	
	public AlbumJsonFactory() {
		innerFactory = new SimpleAlbumJsonFactory();
		trackFactory = new SimpleTrackJsonFactory();
		artistFactory = new SimpleArtistJsonFactory();
	}
	
	@Override
	public Album fromJson(JSONObject jsonObject) {
		final Album album = new Album(getId(jsonObject));
		super.fillObject(album, jsonObject);
		return album;
	}

	@Override
	protected void fillObject(Album baseObject, JSONObject jsonObject) {
		innerFactory.fillObject(baseObject, jsonObject);
		super.fillObject(baseObject, jsonObject);
		final List<SimpleArtist> artists = artistFactory.fromJson(jsonObject.getJSONArray(ARTISTS));
		baseObject.setArtists(artists);
		baseObject.setCopyrights(createCopyrights(jsonObject.getJSONArray(COPYRIGHTS)));
		baseObject.setExternalIds(getExternalIds(jsonObject));
		baseObject.setGenres(getGenres(jsonObject));
		baseObject.setPopularity(jsonObject.getInt(POPULARITY));
		baseObject.setReleaseDate(jsonObject.getString(RELEASE_DATE));
		baseObject.setReleaseDatePrecision(jsonObject.getString(RELEASE_DATE_PRECISION));
		baseObject.setTracks(createSimpleTrackPage(jsonObject.getJSONObject(TRACKS)));
	}
	
	private List<Copyright> createCopyrights(JSONArray copyrightsJson) {
		final List<Copyright> copyrights = new ArrayList<Copyright>();
		for (int i = 0; i < copyrightsJson.size(); i++) {
			final Copyright copyright = new Copyright();
			final JSONObject copyrightJson = copyrightsJson.getJSONObject(i);
			if (existsAndNotNull("text", copyrightJson)) {
				copyright.setText(copyrightJson.getString("text"));
			}
			if (existsAndNotNull("type", copyrightJson)) {
				copyright.setType(copyrightJson.getString("type"));
			}
			copyrights.add(copyright);
		}
		return copyrights;
	}
	
	private Page<SimpleTrack> createSimpleTrackPage(JSONObject jsonObject) {
		final Page<SimpleTrack> page = createItemlessSimpleTrackPage(jsonObject);
		page.setItems(trackFactory.fromJson(jsonObject.getJSONArray(ITEMS)));
		return page;
	}
	
	private Page<SimpleTrack> createItemlessSimpleTrackPage(JSONObject pageJson) {
		final Page<SimpleTrack> page = new Page<>();
		page.setHref(pageJson.getString("href"));
		page.setLimit(pageJson.getInt("limit"));
		if (existsAndNotNull("next", pageJson)) {
			page.setNext(pageJson.getString("next"));
		}
		page.setOffset(pageJson.getInt("offset"));
		if (existsAndNotNull("previous", pageJson)) {
			page.setPrevious(pageJson.getString("previous"));
		}
		page.setTotal(pageJson.getInt("total"));
		return page;
	}

}
