package com.wrapper.spotify.methods.artists;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.DefaultBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.artist.ArtistJsonFactory;

import java.util.List;

@SuppressWarnings("javadoc")
public class RelatedArtistsRequest extends AbstractRequest<List<Artist>> {

	public static DefaultBuilder<List<Artist>> builder(String artistId) {
		return new DefaultBuilder<>(joinPath(ARTISTS, artistId, "related-artists"), RelatedArtistsRequest::new);
	}
	
	public RelatedArtistsRequest(DefaultBuilder<List<Artist>> builder) {
		super(new ListJsonFactory<>("artists", new ArtistJsonFactory()), builder);
	}

}
