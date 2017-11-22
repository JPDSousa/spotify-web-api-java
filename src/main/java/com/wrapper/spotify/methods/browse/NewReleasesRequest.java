package com.wrapper.spotify.methods.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.methods.AbstractPageBuilder;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.BuilderUtils;
import com.wrapper.spotify.methods.CountryBuilder;
import com.wrapper.spotify.models.NewReleases;
import com.wrapper.spotify.models.NewReleasesJsonFactory;

@SuppressWarnings("javadoc")
public class NewReleasesRequest extends AbstractRequest<NewReleases> {

	public static final class Builder extends AbstractPageBuilder<Builder, NewReleases> implements CountryBuilder<Builder> {

		protected Builder() {
			super(NEW_RELEASES, NewReleasesRequest::new);
		}

		@Override
		public Builder country(CountryCode countryCode) {
			return BuilderUtils.country(this, countryCode);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

	public NewReleasesRequest(Builder builder) {
		super(new NewReleasesJsonFactory(), builder);
	}

}