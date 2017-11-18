package com.wrapper.spotify.models.external;

import java.util.Map;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class ExternalIdsJsonFactory extends AbstractJsonFactory<ExternalIds> {

	@Override
	public ExternalIds fromJson(JSONObject jsonObject) {
		final ExternalIds returnedExternalIds = new ExternalIds();
		final Map<String,String> addedIds = returnedExternalIds.getExternalIds();

		for (Object keyObject : jsonObject.keySet()) {
			final String key = (String) keyObject;
			addedIds.put(key, jsonObject.getString(key));
		}

		return returnedExternalIds;
	}

}
