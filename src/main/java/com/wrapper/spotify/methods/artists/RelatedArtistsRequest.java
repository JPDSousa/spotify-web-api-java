package com.wrapper.spotify.methods.artists;

import static com.wrapper.spotify.methods.Paths.ARTISTS;

import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.artist.ArtistJsonFactory;

import net.sf.json.JSONObject;

import java.util.List;

@SuppressWarnings("javadoc")
public class RelatedArtistsRequest extends AbstractRequest<List<Artist>> {

	public static IdBuilder<List<Artist>> builder() {
		return new IdBuilder<>(ARTISTS + "/%s/related-artists", RelatedArtistsRequest::new);
	}
	
	private final JsonFactory<Artist> jsonFactory;
	
	public RelatedArtistsRequest(IdBuilder<List<Artist>> builder) {
		super(builder);
		jsonFactory = new ArtistJsonFactory();
	}

	@Override
	protected List<Artist> fromJson(JSONObject json) {
		return jsonFactory.fromJson(json.getJSONArray("artists"));
	}

}
