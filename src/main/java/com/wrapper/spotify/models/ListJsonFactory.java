package com.wrapper.spotify.models;

import java.util.List;

import com.google.common.collect.Lists;
import com.wrapper.spotify.json.AbstractJsonFactory;
import com.wrapper.spotify.json.JsonFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class ListJsonFactory<T> extends AbstractJsonFactory<List<T>> {
	
	private final String propName;
	private final JsonFactory<T> itemFactory;
	
	public ListJsonFactory(String propName, JsonFactory<T> itemFactory) {
		super();
		this.propName = propName;
		this.itemFactory = itemFactory;
	}

	@Override
	public List<T> fromJson(JSONObject jsonObject) {
		final JSONArray array = jsonObject.getJSONArray(propName);
		final List<T> list = Lists.newArrayListWithCapacity(array.size());
		for(int i = 0; i < array.size(); i++) {
			list.add(itemFactory.fromJson(array.getJSONObject(i)));
		}
		return list;
	}

}
