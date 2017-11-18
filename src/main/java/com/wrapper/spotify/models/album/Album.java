package com.wrapper.spotify.models.album;

import java.util.List;

import com.wrapper.spotify.models.Copyright;
import com.wrapper.spotify.models.artist.SimpleArtist;
import com.wrapper.spotify.models.external.ExternalIds;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.track.SimpleTrack;

@SuppressWarnings("javadoc")
public class Album extends SimpleAlbum{

	private List<SimpleArtist> artists;
	private List<Copyright> copyrights;
	private ExternalIds externalIds;
	private List<String> genres;
	private int popularity;
	private String releaseDate;
	private String releaseDatePrecision;
	private Page<SimpleTrack> tracks;

	public Album(String id) {
		super(id);
	}

	public List<SimpleArtist> getArtists() {
		return artists;
	}

	public void setArtists(List<SimpleArtist> artists) {
		this.artists = artists;
	}

	public List<Copyright> getCopyrights() {
		return copyrights;
	}

	public void setCopyrights(List<Copyright> copyrights) {
		this.copyrights = copyrights;
	}

	public ExternalIds getExternalIds() {
		return externalIds;
	}

	public void setExternalIds(ExternalIds externalIds) {
		this.externalIds = externalIds;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public Page<SimpleTrack> getTracks() {
		return tracks;
	}

	public void setTracks(Page<SimpleTrack> tracks) {
		this.tracks = tracks;
	}

	public String getReleaseDatePrecision() {
		return releaseDatePrecision;
	}

	public void setReleaseDatePrecision(String releaseDatePrecision) {
		this.releaseDatePrecision = releaseDatePrecision;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
}
