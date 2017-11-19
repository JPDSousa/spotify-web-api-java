package com.wrapper.spotify.exceptions;

@SuppressWarnings("javadoc")
public class ServerErrorException extends WebApiException {

	private static final long serialVersionUID = 1L;

	public ServerErrorException(String message) {
		super(message);
	}
}
