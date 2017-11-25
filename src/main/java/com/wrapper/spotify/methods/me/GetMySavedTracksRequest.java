/**
 * Copyright (C) 2017 Spotify AB
 */
package com.wrapper.spotify.methods.me;

import com.wrapper.spotify.methods.AbstractPageBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.LibraryTrack;
import com.wrapper.spotify.models.LibraryTrackJsonFactory;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;

@SuppressWarnings("javadoc")
public class GetMySavedTracksRequest extends AbstractRequest<Page<LibraryTrack>> {

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder extends AbstractPageBuilder<Builder, Page<LibraryTrack>> {

		protected Builder() {
			super(ME_TRACKS, GetMySavedTracksRequest::new);
		}

		public Builder accessToken(String accessToken) {
			return header("Authorization", "Bearer " + accessToken);
		}
		
	}
	
	public GetMySavedTracksRequest(Builder builder) {
		super(new PageJsonFactory<>(null, new LibraryTrackJsonFactory()), builder);
	}

}
