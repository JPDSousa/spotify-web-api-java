package com.wrapper.spotify.methods.browse;

import static com.wrapper.spotify.methods.Paths.FEATURED_PLAYLISTS;

import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.FeaturedPlaylists;
import com.wrapper.spotify.models.playlist.FeaturedPlaylistsJsonFactory;

import net.sf.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("javadoc")
public class FeaturedPlaylistsRequest extends AbstractRequest<FeaturedPlaylists> {

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder extends AbstractBuilder<Builder, FeaturedPlaylists> {

		protected Builder() {
			super(FeaturedPlaylistsRequest::new);
			path(FEATURED_PLAYLISTS);
		}

		public Builder limit(int limit) {
			assert (limit > 0);
			return parameter("limit", String.valueOf(limit));
		}

		public Builder offset(int offset) {
			assert (offset >= 0);
			return parameter("offset", String.valueOf(offset));
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
	
	private final JsonFactory<FeaturedPlaylists> jsonFactory;
	
	public FeaturedPlaylistsRequest(Builder builder) {
		super(builder);
		jsonFactory = new FeaturedPlaylistsJsonFactory();
	}

	@Override
	protected FeaturedPlaylists fromJson(JSONObject json) {
		return jsonFactory.fromJson(json);
	}

}
