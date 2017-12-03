package com.wrapper.spotify.methods.authentication;

import com.google.common.base.Joiner;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;

import java.util.List;

@SuppressWarnings("javadoc")
public class AuthorizationURLRequest extends AbstractRequest<Void> {

	public static AuthorizationURLRequest.Builder builder() {
		return new Builder();
	}

	public static final class Builder extends AbstractBuilder<Builder, Void> {

		protected Builder() {
			super(AUTHORIZATION, AuthorizationURLRequest::new);
			host(Api.DEFAULT_AUTHENTICATION_HOST);
			port(Api.DEFAULT_AUTHENTICATION_PORT);
			scheme(Api.DEFAULT_AUTHENTICATION_SCHEME);
		}

		public Builder scopes(List<String> scopes) {
			return query("scope", Joiner.on(" ").join(scopes));
		}

		public Builder state(String state) {
			return query("state", state);
		}

		public Builder responseType(String responseType) {
			return query("response_type", responseType);
		}

		public Builder clientId(String clientId) {
			return query("client_id", clientId);
		}

		public Builder redirectURI(String redirectURI) {
			return query("redirect_uri", redirectURI);
		}

		public Builder showDialog(boolean showDialog) {
			return query("show_dialog", String.valueOf(showDialog));
		}
		
	}
	
	public AuthorizationURLRequest(Builder builder) {
		super(null, builder);
	}

}
