package com.wrapper.spotify.models.json;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public interface JsonFactory<T> {
	
	T fromJson(String jsonString);
	
	T fromJson(JSONObject jsonObject);
	
	List<T> fromJson(JSONArray jsonObject);

}
