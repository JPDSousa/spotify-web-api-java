package com.wrapper.spotify.models.image;

import java.util.List;

import com.wrapper.spotify.models.Image;
import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.SpotifyModel;

@SuppressWarnings("javadoc")
public class DefaultImageHolder extends SpotifyModel implements ImageHolder {
	
	private List<Image> images;

	public DefaultImageHolder(SpotifyEntityType type, String id) {
		super(type, id);
	}

	@Override
	public List<Image> getImages() {
		return images;
	}

	@Override
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	

}
