package com.wrapper.spotify.models.external;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("javadoc")
public class ExternalUrls {

	private Map<String,String> externalUrls = new HashMap<String, String>();

	public Map<String, String> getExternalUrls() {
		return externalUrls;
	}

	public String get(String key) {
		return externalUrls.get(key);
	}

}
