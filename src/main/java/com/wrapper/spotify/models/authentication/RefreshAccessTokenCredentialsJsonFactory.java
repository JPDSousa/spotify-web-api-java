package com.wrapper.spotify.models.authentication;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class RefreshAccessTokenCredentialsJsonFactory extends AbstractJsonFactory<RefreshAccessTokenCredentials> {

	private static final String EXPIRES_IN = "expires_in";
	private static final String ACCESS_TOKEN = "access_token";
	private static final String TOKEN_TYPE = "token_type";

	@Override
	public RefreshAccessTokenCredentials fromJson(JSONObject jsonObject) {
		final RefreshAccessTokenCredentials refreshAccessTokenResponse = new RefreshAccessTokenCredentials();

		refreshAccessTokenResponse.setTokenType(jsonObject.getString(TOKEN_TYPE));
		refreshAccessTokenResponse.setAccessToken(jsonObject.getString(ACCESS_TOKEN));
		refreshAccessTokenResponse.setExpiresIn(jsonObject.getInt(EXPIRES_IN));

		return refreshAccessTokenResponse;
	}

}
