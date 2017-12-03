package com.wrapper.spotify.methods.browse;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.methods.AbstractPageBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.BuilderUtils;
import com.wrapper.spotify.methods.CountryBuilder;
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

	public static final class Builder extends AbstractPageBuilder<Builder, FeaturedPlaylists> implements CountryBuilder<Builder> {

		protected Builder() {
			super(FEATURED_PLAYLISTS, FeaturedPlaylistsRequest::new);
		}

		public Builder locale(LanguageCode language, CountryCode country) {
			assert (language != null && country != null);
			return query("locale", String.join("_", language.name(), country.name()));
		}

		public Builder timestamp(Date timestamp) {
			assert (timestamp != null);
			
			final DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			return query("timestamp", format.format(timestamp));
		}

		public Builder accessToken(String accessToken) {
			return header("Authorization", "Bearer " + accessToken);
		}

		@Override
		public Builder country(CountryCode code) {
			return BuilderUtils.country(this, code);
		}

	}
	
	public FeaturedPlaylistsRequest(Builder builder) {
		super(new FeaturedPlaylistsJsonFactory(), builder);
	}

}
