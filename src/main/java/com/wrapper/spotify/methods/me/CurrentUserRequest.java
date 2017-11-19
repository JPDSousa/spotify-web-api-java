package com.wrapper.spotify.methods.me;

import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.user.User;
import com.wrapper.spotify.models.user.UserJsonFactory;

@SuppressWarnings("javadoc")
public class CurrentUserRequest extends AbstractRequest<User> {

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder extends AbstractBuilder<Builder, User> {

		protected Builder() {
			super(ME, CurrentUserRequest::new);
		}

		public Builder accessToken(String accessToken) {
			return header("Authorization", "Bearer " + accessToken);
		}

	}
	
	public CurrentUserRequest(Builder builder) {
		super(new UserJsonFactory(), builder);
	}

}
