package com.wrapper.spotify.models;

import com.wrapper.spotify.models.external.ExternalUrls;

@SuppressWarnings("javadoc")
public abstract class SpotifyModel {

	private final SpotifyEntityType type;
	private final String id;
	private String href;
	private String uri;
	private ExternalUrls externalUrls;

	public SpotifyModel(SpotifyEntityType type, String id) {
		super();
		this.type = type;
		this.id = id;
	}
	
	public SpotifyEntityType getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public ExternalUrls getExternalUrls() {
		return externalUrls;
	}

	public void setExternalUrls(ExternalUrls externalUrls) {
		this.externalUrls = externalUrls;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
