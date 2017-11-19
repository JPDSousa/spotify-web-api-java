/**
 * Copyright (C) 2017 Spotify AB
 */
package com.wrapper.spotify.methods.me;

import com.wrapper.spotify.HttpManager.Method;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdsBuilder;

import java.io.IOException;

@SuppressWarnings("javadoc")
public class RemoveFromMySavedTracksRequest extends AbstractRequest<String> {

	public static IdsBuilder<String> builder() {
		return new IdsBuilder<>(ME_TRACKS, RemoveFromMySavedTracksRequest::new);
	}
	
	public RemoveFromMySavedTracksRequest(IdsBuilder<String> builder) {
		super(null, Method.DELETE, builder);
	}

	@Override
	public String exec() throws IOException, WebApiException {
		return deleteJson();
	}

}
