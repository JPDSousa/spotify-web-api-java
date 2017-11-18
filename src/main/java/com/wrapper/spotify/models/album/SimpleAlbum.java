package com.wrapper.spotify.models.album;

import java.util.List;

import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.image.DefaultImageHolder;

@SuppressWarnings("javadoc")
public class SimpleAlbum extends DefaultImageHolder {

	private AlbumType albumType;
	private String name;
	private List<String> availableMarkets;

	public SimpleAlbum(String id) {
		super(SpotifyEntityType.ALBUM, id);
	}

	public List<String> getAvailableMarkets() {
		return availableMarkets;
	}

	public void setAvailableMarkets(List<String> availableMarkets) {
		this.availableMarkets = availableMarkets;
	}

	public AlbumType getAlbumType() {
		return albumType;
	}

	public void setAlbumType(AlbumType albumType) {
		this.albumType = albumType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
