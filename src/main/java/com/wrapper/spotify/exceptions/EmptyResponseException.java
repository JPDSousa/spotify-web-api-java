package com.wrapper.spotify.exceptions;

@SuppressWarnings("javadoc")
public class EmptyResponseException extends WebApiException {

	private static final long serialVersionUID = 1L;

	public EmptyResponseException() {
		super();
	}

	public EmptyResponseException(String message) {
		super(message);
	}
}
