package com.wrapper.spotify.methods.browse;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.PageBuilder;
import com.wrapper.spotify.models.NewReleases;
import com.wrapper.spotify.models.NewReleasesJsonFactory;

@SuppressWarnings("javadoc")
public class NewReleasesRequest extends AbstractRequest<NewReleases> {

	public static final class Builder extends PageBuilder<Builder, NewReleases> {

		protected Builder() {
			super(NEW_RELEASES, NewReleasesRequest::new);
		}

		public Builder country(String countryCode) {
			assert (countryCode != null);
			return parameter("country", countryCode);
		}

	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public NewReleasesRequest(Builder builder) {
		super(new NewReleasesJsonFactory(), builder);
	}
	
}