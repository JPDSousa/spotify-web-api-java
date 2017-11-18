package com.wrapper.spotify.models.user;

import com.wrapper.spotify.models.Product;
import com.wrapper.spotify.models.image.ImageHolderJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class UserJsonFactory extends ImageHolderJsonFactory<User> {

	private static final String BIRTHDATE = "birthdate";
	private static final String COUNTRY = "country";
	private static final String PRODUCT = "product";
	private static final String EMAIL = "email";
	private static final String DISPLAY_NAME = "display_name";

	@Override
	public User fromJson(JSONObject jsonObject) {
		final User user = new User(getId(jsonObject));
		fillObject(user, jsonObject);
		return user;
	}

	@Override
	protected void fillObject(User baseObject, JSONObject jsonObject) {
		super.fillObject(baseObject, jsonObject);
		// Always in the user object
		baseObject.setFollowers(getFollowers(jsonObject));

		if (existsAndNotNull(DISPLAY_NAME, jsonObject)) {
			baseObject.setDisplayName(jsonObject.getString(DISPLAY_NAME));
		}
		if (existsAndNotNull(EMAIL, jsonObject)) {
			baseObject.setEmail(jsonObject.getString(EMAIL));
		}
		if (existsAndNotNull(PRODUCT, jsonObject)) {
			baseObject.setProduct(createProduct(jsonObject.getString(PRODUCT)));
		}
		if (existsAndNotNull(COUNTRY, jsonObject)) {
			baseObject.setCountry(jsonObject.getString(COUNTRY));
		}
		if (existsAndNotNull(BIRTHDATE, jsonObject)) {
			baseObject.setBirthdate(jsonObject.getString(BIRTHDATE));
		}
	}
	
	private static Product createProduct(String product) {
		return Product.valueOf(product.toUpperCase());
	}

}
