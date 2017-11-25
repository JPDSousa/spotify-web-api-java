package com.wrapper.spotify.models.page;

import java.util.ArrayList;
import java.util.List;

import com.wrapper.spotify.json.JsonFactory;

@SuppressWarnings("javadoc")
public class Page<T> {

	private String href;
	private List<T> items = new ArrayList<T>();
	private int limit;
	private String next;
	private int offset;
	private String previous;
	private int total;
	private final JsonFactory<T> jsonFactory;
	private final String propName;
	
	public Page(String propName, JsonFactory<T> jsonFactory) {
		this.jsonFactory = jsonFactory;
		this.propName = propName;
	}
	
	public JsonFactory<T> getJsonFactory() {
		return jsonFactory;
	}
	
	public String getPropName() {
		return propName;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
