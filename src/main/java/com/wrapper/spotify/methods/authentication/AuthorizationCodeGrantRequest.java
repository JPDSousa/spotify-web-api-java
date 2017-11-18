package com.wrapper.spotify.methods.authentication;

import static com.wrapper.spotify.methods.Paths.TOKEN;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.authentication.AuthorizationCodeCredentials;
import com.wrapper.spotify.models.authentication.AuthorizationCodeCredentialsJsonFactory;

import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

@SuppressWarnings("javadoc")
public class AuthorizationCodeGrantRequest extends AbstractRequest<AuthorizationCodeCredentials> {

	public static final class Builder extends AbstractBuilder<Builder, AuthorizationCodeCredentials> {

		protected Builder() {
			super(AuthorizationCodeGrantRequest::new);
			host(Api.DEFAULT_AUTHENTICATION_HOST);
			port(Api.DEFAULT_AUTHENTICATION_PORT);
			scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);

			path(TOKEN);
		}

		public Builder grantType(String grantType) {
			assert (grantType != null);
			return body("grant_type", grantType);
		}

		public Builder code(String code) {
			assert (code != null);
			return body("code", code);
		}

		public Builder redirectUri(String redirectUri) {
			assert (redirectUri != null);
			return body("redirect_uri", redirectUri);
		}

		public Builder basicAuthorizationHeader(String clientId, String clientSecret) {
			assert (clientId != null);
			assert (clientSecret != null);

			String idSecret = clientId + ":" + clientSecret;
			String idSecretEncoded = new String(Base64.encodeBase64(idSecret.getBytes()));

			return header("Authorization", "Basic " + idSecretEncoded);
		}

	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	private final JsonFactory<AuthorizationCodeCredentials> jsonFactory;
	
	public AuthorizationCodeGrantRequest(Builder builder) {
		super(builder);
		jsonFactory = new AuthorizationCodeCredentialsJsonFactory();
	}

	@Override
	protected AuthorizationCodeCredentials fromJson(JSONObject json) {
		return jsonFactory.fromJson(json);
	}

}
