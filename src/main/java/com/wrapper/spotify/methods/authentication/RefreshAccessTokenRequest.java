package com.wrapper.spotify.methods.authentication;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.authentication.RefreshAccessTokenCredentials;
import com.wrapper.spotify.models.authentication.RefreshAccessTokenCredentialsJsonFactory;

import org.apache.commons.codec.binary.Base64;

@SuppressWarnings("javadoc")
public class RefreshAccessTokenRequest extends AbstractRequest<RefreshAccessTokenCredentials> {

	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder extends AbstractBuilder<Builder, RefreshAccessTokenCredentials> {

		public Builder() {
			super(TOKEN, RefreshAccessTokenRequest::new);
			host(Api.DEFAULT_AUTHENTICATION_HOST);
			port(Api.DEFAULT_AUTHENTICATION_PORT);
			scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);
		}

		public Builder basicAuthorizationHeader(String clientId, String clientSecret) {
			assert (clientId != null);
			assert (clientSecret != null);
			final String idSecret = String.join(":", clientId, clientSecret);
			final String idSecretEncoded = new String(Base64.encodeBase64(idSecret.getBytes()));

			return header("Authorization", "Basic " + idSecretEncoded);
		}
		
		public Builder grantType(String grantType) {
			assert grantType != null;
			return body("grant_type", grantType);
		}

		public Builder refreshToken(String refreshToken) {
			assert (refreshToken != null);
			return body("refresh_token", refreshToken);
		}
	}
	
	public RefreshAccessTokenRequest(Builder builder) {
		super(new RefreshAccessTokenCredentialsJsonFactory(), builder);
	}

}
