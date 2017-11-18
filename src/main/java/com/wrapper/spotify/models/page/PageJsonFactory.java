package com.wrapper.spotify.models.page;

import com.wrapper.spotify.json.AbstractJsonFactory;
import com.wrapper.spotify.json.JsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class PageJsonFactory<T> extends AbstractJsonFactory<Page<T>> {

	private static final String ITEMS = "items";
	private static final String LIMIT = "limit";
	private static final String NEXT = "next";
	private static final String OFFSET = "offset";
	private static final String PREVIOUS = "previous";
	private static final String TOTAL = "total";
	private static final String HREF = "href";
	
	private final JsonFactory<T> itemFactory;
	
	public PageJsonFactory(JsonFactory<T> itemFactory) {
		this.itemFactory = itemFactory;
	}
	
	@Override
	public Page<T> fromJson(JSONObject jsonObject) {
		final Page<T> page = createItemlessPage(jsonObject);
		page.setItems(itemFactory.fromJson(jsonObject.getJSONArray(ITEMS)));
		return page;
	}

	private Page<T> createItemlessPage(JSONObject jsonObject) {
		final Page<T> page = new Page<>();
		page.setHref(jsonObject.getString(HREF));
		page.setLimit(jsonObject.getInt(LIMIT));
		if (existsAndNotNull(NEXT, jsonObject)) {
			page.setNext(jsonObject.getString(NEXT));
		}
		page.setOffset(jsonObject.getInt(OFFSET));
		if (existsAndNotNull(PREVIOUS, jsonObject)) {
			page.setPrevious(jsonObject.getString(PREVIOUS));
		}
		page.setTotal(jsonObject.getInt(TOTAL));
		return page;
	}

}
