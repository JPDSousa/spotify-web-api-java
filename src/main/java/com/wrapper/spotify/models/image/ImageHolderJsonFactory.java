package com.wrapper.spotify.models.image;

import java.util.ArrayList;
import java.util.List;

import com.wrapper.spotify.json.SpotifyModelJsonFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public abstract class ImageHolderJsonFactory<T extends DefaultImageHolder> extends SpotifyModelJsonFactory<T> {

	private static final String URL = "url";
	private static final String WIDTH = "width";
	private static final String HEIGHT = "height";
	private static final String IMAGES = "images";

	@Override
	protected void fillObject(T baseObject, JSONObject jsonObject) {
		super.fillObject(baseObject, jsonObject);
		baseObject.setImages(createImages(jsonObject.getJSONArray(IMAGES)));
	}
	
	public static List<Image> createImages(JSONArray images) {
		final List<Image> returnedImages = new ArrayList<Image>();
		for (int i = 0; i < images.size(); i++) {
			returnedImages.add(createImage(images.getJSONObject(i)));
		}
		return returnedImages;
	}
	
	public static Image createImage(JSONObject image) {
		if (JSONNull.getInstance().equals(image)) {
			return null;
		}
		final Image returnedImage = new Image();
		if (image.containsKey(HEIGHT) && !image.get(HEIGHT).equals(JSONNull.getInstance())) {
			returnedImage.setHeight(image.getInt(HEIGHT));
		}
		if (image.containsKey(WIDTH) && !image.get(WIDTH).equals(JSONNull.getInstance())) {
			returnedImage.setWidth(image.getInt(WIDTH));
		}
		if (image.containsKey(URL) && !image.get(URL).equals(JSONNull.getInstance())) {
			returnedImage.setUrl(image.getString(URL));
		}
		return returnedImage;
	}

}
