package com.wrapper.spotify.json;

import java.util.List;

import com.google.common.collect.Lists;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public abstract class AbstractJsonFactory<T> implements JsonFactory<T> {

	protected static final String NULL = "null";
	
	protected static boolean existsAndNotNull(String key, JSONObject jsonObject) {
		return jsonObject.containsKey(key) &&
				!JSONNull.getInstance().equals(jsonObject.get(key));
	}
	
	@Override
	public T fromJson(String jsonString) {
		return fromJson(JSONObject.fromObject(jsonString));
	}

	@Override
	public List<T> fromJson(JSONArray jsonObject) {
		final List<T> items = Lists.newArrayListWithCapacity(jsonObject.size());
		for(int i = 0; i < jsonObject.size(); i++) {
			items.add(fromJson(jsonObject.getJSONObject(i)));
		}
		return items;
	}

}
