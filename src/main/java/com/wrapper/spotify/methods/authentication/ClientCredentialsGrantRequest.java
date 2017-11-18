package com.wrapper.spotify.methods.authentication;

import static com.wrapper.spotify.methods.Paths.TOKEN;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.authentication.ClientCredentials;
import com.wrapper.spotify.models.authentication.ClientCredentialsJsonFactory;

import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.util.List;

@SuppressWarnings("javadoc")
public class ClientCredentialsGrantRequest extends AbstractRequest<ClientCredentials> {

	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder extends AbstractBuilder<Builder, ClientCredentials> {

		protected Builder() {
			super(ClientCredentialsGrantRequest::new);
			host(Api.DEFAULT_AUTHENTICATION_HOST);
			port(Api.DEFAULT_AUTHENTICATION_PORT);
			scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);

			path(TOKEN);
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
	
	private final JsonFactory<ClientCredentials> jsonFactory;
	
	public ClientCredentialsGrantRequest(Builder builder) {
		super(builder);
		jsonFactory = new ClientCredentialsJsonFactory();
	}

	@Override
	protected ClientCredentials fromJson(JSONObject json) {
		return jsonFactory.fromJson(json);
	}
}
