package com.wrapper.spotify.models.authentication;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class AuthorizationCodeCredentialsJsonFactory extends AbstractJsonFactory<AuthorizationCodeCredentials> {

	private static final String TOKEN_TYPE = "token_type";
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String EXPIRES_IN = "expires_in";
	private static final String ACCESS_TOKEN = "access_token";

	@Override
	public AuthorizationCodeCredentials fromJson(JSONObject jsonObject) {
		final AuthorizationCodeCredentials response = new AuthorizationCodeCredentials();

		response.setAccessToken(jsonObject.getString(ACCESS_TOKEN));
		response.setExpiresIn(jsonObject.getInt(EXPIRES_IN));
		response.setRefreshToken(jsonObject.getString(REFRESH_TOKEN));
		response.setTokenType(jsonObject.getString(TOKEN_TYPE));

		return response;
	}

}
