package com.wrapper.spotify.methods.authentication;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.methods.Request;
import com.wrapper.spotify.models.authentication.RefreshAccessTokenCredentials;

import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("javadoc")
public class RefreshAccessTokenRequestTest {

	@Test
	public void shouldRefreshToken() throws Exception {
		final String clientId = "myClientId";
		final String clientSecret = "myClientSecret";
		final String refreshToken = "myRefreshToken";

		final Api api = Api
				.builder()
				.clientId(clientId)
				.clientSecret(clientSecret)
				.refreshToken(refreshToken)
				.build();

		final Request<RefreshAccessTokenCredentials> request = api
				.refreshAccessToken()
				.httpClient(TestUtil.MockedHttpManager.returningJson("auth-tokens.json"))
				.build();

		RefreshAccessTokenCredentials refreshAccessTokenResponse = request.exec();
		assertNotNull(refreshAccessTokenResponse.getExpiresIn());
		assertNotNull(refreshAccessTokenResponse.getAccessToken());
		assertNotNull(refreshAccessTokenResponse.getTokenType());
	}
}
