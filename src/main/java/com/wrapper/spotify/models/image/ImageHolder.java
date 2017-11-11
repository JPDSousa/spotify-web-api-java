package com.wrapper.spotify.models.image;

import java.util.List;

import com.wrapper.spotify.models.Image;

@SuppressWarnings("javadoc")
public interface ImageHolder {

	void setImages(List<Image> images);

	List<Image> getImages();

}
