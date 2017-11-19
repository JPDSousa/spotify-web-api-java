package com.wrapper.spotify.exceptions;

@SuppressWarnings("javadoc")
public class WebApiAuthenticationException extends WebApiException {

	private static final long serialVersionUID = 1L;

	public WebApiAuthenticationException(String message) {
		super(message);
	}
}
