package com.wrapper.spotify.models.authentication;

import com.wrapper.spotify.json.AbstractJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class ClientCredentialsJsonFactory extends AbstractJsonFactory<ClientCredentials> {

	private static final String TOKEN_TYPE = "token_type";
	private static final String EXPIRES_IN = "expires_in";
	private static final String ACCESS_TOKEN = "access_token";

	@Override
	public ClientCredentials fromJson(JSONObject jsonObject) {
		final ClientCredentials token = new ClientCredentials();

		token.setAccessToken(jsonObject.getString(ACCESS_TOKEN));
		token.setExpiresIn(jsonObject.getInt(EXPIRES_IN));
		token.setTokenType(jsonObject.getString(TOKEN_TYPE));

		return token;
	}

}
