package com.wrapper.spotify.models.user;

import com.wrapper.spotify.models.Product;
import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.followers.Followers;
import com.wrapper.spotify.models.image.DefaultImageHolder;

@SuppressWarnings("javadoc")
public class User extends DefaultImageHolder {

	private String displayName;
	private String email;
	private Followers followers;
	private String country;
	private Product product;
	private String birthdate;

	public User(String id) {
		super(SpotifyEntityType.USER, id);
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Followers getFollowers() {
		return followers;
	}

	public void setFollowers(Followers followers) {
		this.followers = followers;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
}
