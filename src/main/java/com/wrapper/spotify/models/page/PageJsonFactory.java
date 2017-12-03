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
	private final String propName;
	
	public PageJsonFactory(String propName, JsonFactory<T> itemFactory) {
		this.itemFactory = itemFactory;
		this.propName = propName;
	}
	
	@Override
	public Page<T> fromJson(JSONObject jsonObject) {
		final JSONObject unwrapped = propName != null ? jsonObject.getJSONObject(propName) : jsonObject;
		final Page<T> page = createItemlessPage(unwrapped);
		if(existsAndNotNull(ITEMS, unwrapped)) {
			page.setItems(itemFactory.fromJson(unwrapped.getJSONArray(ITEMS)));
		}
		return page;
	}

	private Page<T> createItemlessPage(JSONObject jsonObject) {
		final Page<T> page = new Page<>(propName, itemFactory);
		if(existsAndNotNull(HREF, jsonObject)) {
			page.setHref(jsonObject.getString(HREF));
		}
		if(existsAndNotNull(LIMIT, jsonObject)) {
			page.setLimit(jsonObject.getInt(LIMIT));
		}
		if (existsAndNotNull(NEXT, jsonObject)) {
			page.setNext(jsonObject.getString(NEXT));
		}
		if(existsAndNotNull(OFFSET, jsonObject)) {
			page.setOffset(jsonObject.getInt(OFFSET));
		}
		if (existsAndNotNull(PREVIOUS, jsonObject)) {
			page.setPrevious(jsonObject.getString(PREVIOUS));
		}
		if (existsAndNotNull(TOTAL, jsonObject)) {
			page.setTotal(jsonObject.getInt(TOTAL));
		}
		return page;
	}

}
