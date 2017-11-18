package com.wrapper.spotify.models.artist;

import java.util.List;

import com.wrapper.spotify.models.Followers;
import com.wrapper.spotify.models.image.Image;
import com.wrapper.spotify.models.image.ImageHolder;

@SuppressWarnings("javadoc")
public class Artist extends SimpleArtist implements ImageHolder {

	private List<String> genres;
	private List<Image> images;
	private int popularity;

	private Followers followers;

	public Artist(String id) {
		super(id);
	}

	public Followers getFollowers() {
		return followers;
	}

	public void setFollowers(Followers followers) {
		this.followers = followers;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	@Override
	public List<Image> getImages() {
		return images;
	}

	@Override
	public void setImages(List<Image> images) {
		this.images = images;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

}
