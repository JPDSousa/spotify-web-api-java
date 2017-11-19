package com.wrapper.spotify.exceptions;

@SuppressWarnings("javadoc")
public class WebApiException extends Exception {

	private static final long serialVersionUID = 1L;

	public WebApiException(String message) {
		super(message);
	}

	public WebApiException() {
		super();
	}

}
