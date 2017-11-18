package com.wrapper.spotify.methods.browse;

import static com.wrapper.spotify.methods.Paths.NEW_RELEASES;

import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.models.NewReleases;
import com.wrapper.spotify.models.NewReleasesJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class NewReleasesRequest extends AbstractRequest<NewReleases> {

	public static final class Builder extends AbstractBuilder<Builder, NewReleases> {

		protected Builder() {
			super(NewReleasesRequest::new);
			path(NEW_RELEASES);
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

	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	private final JsonFactory<NewReleases> jsonFactory;
	
	public NewReleasesRequest(Builder builder) {
		super(builder);
		jsonFactory = new NewReleasesJsonFactory();
	}

	@Override
	protected NewReleases fromJson(JSONObject json) {
		return jsonFactory.fromJson(json);
	}
}