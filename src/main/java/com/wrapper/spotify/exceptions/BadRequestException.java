package com.wrapper.spotify.exceptions;

@SuppressWarnings("javadoc")
public class BadRequestException extends WebApiException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}

}
