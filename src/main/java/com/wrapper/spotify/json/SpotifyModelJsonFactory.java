package com.wrapper.spotify.json;

import java.util.ArrayList;
import java.util.List;

import com.wrapper.spotify.models.SpotifyModel;
import com.wrapper.spotify.models.external.ExternalIds;
import com.wrapper.spotify.models.external.ExternalIdsJsonFactory;
import com.wrapper.spotify.models.external.ExternalUrlsJsonFactory;
import com.wrapper.spotify.models.followers.Followers;
import com.wrapper.spotify.models.followers.FollowersJsonFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public abstract class SpotifyModelJsonFactory<T extends SpotifyModel> extends AbstractJsonFactory<T> {

	private static final String FOLLOWERS = "followers";
	private static final String ID = "id";
	private static final String EXTERNAL_URLS = "external_urls";
	private static final String URI = "uri";
	private static final String HREF = "href";
	
	private static final String EXTERNAL_IDS = "external_ids";
	private static final String AVAILABLE_MARKETS = "available_markets";
	private static final String GENRES = "genres";
	
	private final ExternalIdsJsonFactory externalIdsJsonFactory;
	private final ExternalUrlsJsonFactory externalUrlsJsonFactory;
	private final FollowersJsonFactory followersJsonFactory;
	
	public SpotifyModelJsonFactory() {
		externalIdsJsonFactory = new ExternalIdsJsonFactory();
		externalUrlsJsonFactory = new ExternalUrlsJsonFactory();
		followersJsonFactory = new FollowersJsonFactory();
	}

	protected void fillObject(T baseObject, JSONObject jsonObject) {
		baseObject.setHref(jsonObject.getString(HREF));
		baseObject.setUri(jsonObject.getString(URI));
		baseObject.setExternalUrls(externalUrlsJsonFactory.fromJson(jsonObject.getJSONObject(EXTERNAL_URLS)));
	}
	
	protected final String getId(JSONObject jsonObject) {
		return jsonObject.getString(ID);
	}
	
	protected final List<String> getAvailableMarkets(JSONObject jsonObject) {
		return createAvailableMarkets(jsonObject.getJSONArray(AVAILABLE_MARKETS));
	}
	
	private final List<String> createAvailableMarkets(JSONArray availableMarketsJson) {
		final List<String> availableMarkets = new ArrayList<String>();
		for (int i = 0; i < availableMarketsJson.size(); i++) {
			availableMarkets.add(availableMarketsJson.getString(i));
		}
		return availableMarkets;
	}
	
	protected final ExternalIds getExternalIds(JSONObject jsonObject) {
		return externalIdsJsonFactory.fromJson(jsonObject.getJSONObject(EXTERNAL_IDS));
	}
	
	protected List<String> getGenres(JSONObject jsonObject) {
		return createGenres(jsonObject.getJSONArray(GENRES));
	}
	
	private List<String> createGenres(JSONArray genres) {
		final List<String> returnedGenres = new ArrayList<String>();
		for (int i = 0; i < genres.size(); i++) {
			returnedGenres.add(genres.getString(i));
		}
		return returnedGenres;
	}
	
	protected Followers getFollowers(JSONObject jsonObject) {
		return followersJsonFactory.fromJson(jsonObject.getJSONObject(FOLLOWERS));
	}

}
