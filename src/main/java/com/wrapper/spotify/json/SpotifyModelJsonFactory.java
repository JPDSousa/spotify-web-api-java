package com.wrapper.spotify.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wrapper.spotify.models.ExternalIds;
import com.wrapper.spotify.models.ExternalUrls;
import com.wrapper.spotify.models.Followers;
import com.wrapper.spotify.models.SpotifyModel;

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

	protected void fillObject(T baseObject, JSONObject jsonObject) {
		baseObject.setHref(jsonObject.getString(HREF));
		baseObject.setUri(jsonObject.getString(URI));
		baseObject.setExternalUrls(createExternalUrls(jsonObject.getJSONObject(EXTERNAL_URLS)));
	}
	
	private ExternalUrls createExternalUrls(JSONObject externalUrls) {
		final ExternalUrls returnedExternalUrls = new ExternalUrls();
		final Map<String,String> addedExternalUrls = returnedExternalUrls.getExternalUrls();
		for (Object keyObject : externalUrls.keySet()) {
			final String key = (String) keyObject;
			addedExternalUrls.put(key, externalUrls.getString(key));
		}
		return returnedExternalUrls;
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
		return createExternalIds(jsonObject.getJSONObject(EXTERNAL_IDS));
	}
	
	private final ExternalIds createExternalIds(JSONObject externalIds) {
		final ExternalIds returnedExternalIds = new ExternalIds();
		final Map<String,String> addedIds = returnedExternalIds.getExternalIds();

		for (Object keyObject : externalIds.keySet()) {
			final String key = (String) keyObject;
			addedIds.put(key, externalIds.getString(key));
		}

		return returnedExternalIds;
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
	
	protected static Followers getFollowers(JSONObject jsonObject) {
		return createFollowers(jsonObject.getJSONObject(FOLLOWERS));
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
