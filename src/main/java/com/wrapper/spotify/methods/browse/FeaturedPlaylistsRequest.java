package com.wrapper.spotify.methods.browse;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.PageBuilder;
import com.wrapper.spotify.models.FeaturedPlaylists;
import com.wrapper.spotify.models.playlist.FeaturedPlaylistsJsonFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("javadoc")
public class FeaturedPlaylistsRequest extends AbstractRequest<FeaturedPlaylists> {

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder extends PageBuilder<Builder, FeaturedPlaylists> {

		protected Builder() {
			super(FEATURED_PLAYLISTS, FeaturedPlaylistsRequest::new);
		}

		public Builder country(String countryCode) {
			assert (countryCode != null);
			return parameter("country", countryCode);
		}

		public Builder locale(String locale) {
			assert (locale != null);
			return parameter("locale", locale);
		}

		public Builder timestamp(Date timestamp) {
			assert (timestamp != null);
			final DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			return parameter("timestamp", format.format(timestamp));
		}

		public Builder accessToken(String accessToken) {
			return header("Authorization", "Bearer " + accessToken);
		}

	}
	
	public FeaturedPlaylistsRequest(Builder builder) {
		super(new FeaturedPlaylistsJsonFactory(), builder);
	}

}
