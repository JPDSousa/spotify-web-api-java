package com.wrapper.spotify.models.track;

import java.util.List;

import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.SpotifyModel;
import com.wrapper.spotify.models.artist.SimpleArtist;

@SuppressWarnings("javadoc")
public class SimpleTrack extends SpotifyModel {

	private String name;

	private int trackNumber;

	private int discNumber;
	private int duration;
	private boolean explicit;
	private List<SimpleArtist> artists;

	private List<String> availableMarkets;

	private String previewUrl;
	
	public SimpleTrack(String id) {
		super(SpotifyEntityType.TRACK, id);
	}

	public List<String> getAvailableMarkets() {
		return availableMarkets;
	}

	public void setAvailableMarkets(List<String> availableMarkets) {
		this.availableMarkets = availableMarkets;
	}

	public List<SimpleArtist> getArtists() {
		return artists;
	}

	public void setArtists(List<SimpleArtist> artists) {
		this.artists = artists;
	}

	public int getDiscNumber() {
		return discNumber;
	}

	public void setDiscNumber(int discNumber) {
		this.discNumber = discNumber;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isExplicit() {
		return explicit;
	}

	public void setExplicit(boolean explicit) {
		this.explicit = explicit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

}
