package com.wrapper.spotify.models.followers;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class FollowersJsonFactory extends AbstractJsonFactory<Followers> {

	private static final String TOTAL = "total";
	private static final String HREF = "href";

	@Override
	public Followers fromJson(JSONObject jsonObject) {
		final Followers returnedFollowers = new Followers();
		if (existsAndNotNull(HREF, jsonObject)) {
			returnedFollowers.setHref(jsonObject.getString(HREF));
		}
		if (existsAndNotNull(TOTAL, jsonObject)) {
			returnedFollowers.setTotal(jsonObject.getInt(TOTAL));
		}
		return returnedFollowers;
	}

}
