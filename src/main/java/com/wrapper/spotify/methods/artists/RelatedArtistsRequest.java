package com.wrapper.spotify.methods.artists;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.artist.ArtistJsonFactory;

import java.util.List;

@SuppressWarnings("javadoc")
public class RelatedArtistsRequest extends AbstractRequest<List<Artist>> {

	public static IdBuilder<List<Artist>> builder() {
		return new IdBuilder<>(ARTISTS + "/%s/related-artists", RelatedArtistsRequest::new);
	}
	
	public RelatedArtistsRequest(IdBuilder<List<Artist>> builder) {
		super(new ListJsonFactory<>("artists", new ArtistJsonFactory()), builder);
	}

}
