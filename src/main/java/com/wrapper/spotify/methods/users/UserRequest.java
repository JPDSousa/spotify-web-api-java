package com.wrapper.spotify.methods.users;

import javax.swing.JOptionPane;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.DefaultBuilder;
import com.wrapper.spotify.models.user.User;
import com.wrapper.spotify.models.user.UserJsonFactory;

@SuppressWarnings("javadoc")
public class UserRequest extends AbstractRequest<User> {

	public static DefaultBuilder<User> builder(String userId) {
		return new DefaultBuilder<>(joinPath(USERS, userId), UserRequest::new);
	}
	
	public UserRequest(DefaultBuilder<User> builder) {
		super(new UserJsonFactory(), builder);
	}
	
}
