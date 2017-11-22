package com.wrapper.spotify.methods.authentication;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.methods.Request;
import com.wrapper.spotify.models.authentication.ClientCredentials;

import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("javadoc")
public class ClientCredentialsGrantRequestTest {

	@Test
	public void shouldGetAccessToken_sync() throws Exception {
		final String clientId = "myClientId";
		final String clientSecret = "myClientSecret";

		final Api api = Api.builder()
				.clientId(clientId)
				.clientSecret(clientSecret)
				.build();

		final Request<ClientCredentials> request = api
				.clientCredentialsGrant()
				.httpManager(TestUtil.MockedHttpManager.returningJson("application-authentication-token.json"))
				.build();

		final ClientCredentials response = request.exec();
		assertEquals(3600, response.getExpiresIn());
		assertEquals("BQAh_5C4JzOMLuF0W-UVTtaOhZaX0bjgJ5B8giFun_i7AJRKTpZ-VB1mFd3hWLLWRsZNihc_fG1xUlnW9sLBjQ", response.getAccessToken());
		assertEquals("Bearer", response.getTokenType());
	}

}
