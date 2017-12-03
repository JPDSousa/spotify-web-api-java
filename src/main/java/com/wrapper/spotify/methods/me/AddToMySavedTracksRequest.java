/**
 * Copyright (C) 2017 Spotify AB
 */
package com.wrapper.spotify.methods.me;

import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("javadoc")
public class AddToMySavedTracksRequest extends AbstractRequest<String> {

	public static AddToMySavedTracksRequest.Builder builder() {
		return new Builder();
	}

	public static class Builder extends AbstractBuilder<Builder, String> {

		protected Builder() {
			super(ME_TRACKS, AddToMySavedTracksRequest::new);
		}

		public Builder ids(List<String> trackIds) {
			body(JSONArray.fromObject(trackIds));
			return this;
		}

	}
	
	public AddToMySavedTracksRequest(Builder builder) {
		super(null, builder);
	}
	
	@Override
	public String exec() throws IOException {
		return putJson();
	}

}
