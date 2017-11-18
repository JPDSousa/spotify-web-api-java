package com.wrapper.spotify.methods.artists;

import static com.wrapper.spotify.methods.Paths.ARTISTS;

import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

import net.sf.json.JSONObject;

import java.util.List;

@SuppressWarnings("javadoc")
public class TopTracksRequest extends AbstractRequest<List<Track>> {

	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder extends IdBuilder<List<Track>> {

		public Builder() {
			super(ARTISTS + "/%s/toptracks", TopTracksRequest::new);
		}

		public Builder countryCode(String countryCode) {
			assert (countryCode != null);
			parameter("country", countryCode);
			return this;
		}

	}
	
	private final JsonFactory<Track> trackFactory;
	
	public TopTracksRequest(IdBuilder<List<Track>> builder) {
		super(builder);
		trackFactory = new TrackJsonFactory();
	}

	@Override
	protected List<Track> fromJson(JSONObject json) {
		return trackFactory.fromJson(json.getJSONArray("tracks"));
	}

}
