package com.wrapper.spotify.models.external;

import java.util.Map;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class ExternalUrlsJsonFactory extends AbstractJsonFactory<ExternalUrls> {

	@Override
	public ExternalUrls fromJson(JSONObject jsonObject) {
		final ExternalUrls returnedExternalUrls = new ExternalUrls();
		final Map<String,String> addedExternalUrls = returnedExternalUrls.getExternalUrls();
		for (Object keyObject : jsonObject.keySet()) {
			final String key = (String) keyObject;
			addedExternalUrls.put(key, jsonObject.getString(key));
		}
		return returnedExternalUrls;
	}

}
