package com.wrapper.spotify.methods.authentication;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.Method;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.authentication.ClientCredentials;
import com.wrapper.spotify.models.authentication.ClientCredentialsJsonFactory;

import org.apache.commons.codec.binary.Base64;

import java.util.List;

@SuppressWarnings("javadoc")
public class ClientCredentialsGrantRequest extends AbstractRequest<ClientCredentials> {

	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder extends AbstractBuilder<Builder, ClientCredentials> {

		protected Builder() {
			super(TOKEN, ClientCredentialsGrantRequest::new);
			host(Api.DEFAULT_AUTHENTICATION_HOST);
			port(Api.DEFAULT_AUTHENTICATION_PORT);
			scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);
		}

		public Builder basicAuthorizationHeader(String clientId, String clientSecret) {
			assert (clientId != null);
			assert (clientSecret != null);

			final String idSecret = String.join(":", clientId, clientSecret);
			String idSecretEncoded = new String(Base64.encodeBase64(idSecret.getBytes()));

			return header("Authorization", "Basic " + idSecretEncoded);
		}

		public Builder grantType(String grantType) {
			assert (grantType != null);
			return body("grant_type", grantType);
		}

		public Builder scopes(List<String> scopes) {
			return body("scope", String.join(" ", scopes));
		}

	}
	
	public ClientCredentialsGrantRequest(Builder builder) {
		super(new ClientCredentialsJsonFactory(), builder);
	}
	
}
