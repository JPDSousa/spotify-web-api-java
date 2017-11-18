package com.wrapper.spotify.methods.artists;

import static com.wrapper.spotify.methods.Paths.ARTISTS;

import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.artist.ArtistJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class ArtistRequest extends AbstractRequest<Artist> {

	public static IdBuilder<Artist> builder() {
		return new IdBuilder<>(ARTISTS + "/%s", ArtistRequest::new);
	}
	
	private final JsonFactory<Artist> artistFactory;

	protected ArtistRequest(IdBuilder<Artist> builder) {
		super(builder);
		artistFactory = new ArtistJsonFactory();
	}

	@Override
	protected Artist fromJson(JSONObject jsonObject) {
		return artistFactory.fromJson(jsonObject);
	}
	
}
