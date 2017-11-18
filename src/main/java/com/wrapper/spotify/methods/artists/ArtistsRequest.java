package com.wrapper.spotify.methods.artists;

import static com.wrapper.spotify.methods.Paths.ARTISTS;

import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdsBuilder;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.artist.ArtistJsonFactory;

import net.sf.json.JSONObject;

import java.util.List;

@SuppressWarnings("javadoc")
public class ArtistsRequest extends AbstractRequest<List<Artist>> {

	public static IdsBuilder<Artist> builder() {
		return new IdsBuilder<>(ARTISTS, ArtistsRequest::new);
	}

	private final JsonFactory<Artist> artistFactory;
	
	public ArtistsRequest(IdsBuilder<Artist> builder) {
		super(builder);
		artistFactory = new ArtistJsonFactory();
	}

	@Override
	protected List<Artist> fromJson(JSONObject jsonObject) {
		return artistFactory.fromJson(jsonObject.getJSONArray("artists"));
	}
}
