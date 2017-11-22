package com.wrapper.spotify.methods.artists;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdsBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.artist.ArtistJsonFactory;

import java.util.List;

@SuppressWarnings("javadoc")
public class ArtistsRequest extends AbstractRequest<List<Artist>> {

	public static IdsBuilder<List<Artist>> builder() {
		return new IdsBuilder<>(50, ARTISTS, ArtistsRequest::new);
	}

	public ArtistsRequest(IdsBuilder<List<Artist>> builder) {
		super(new ListJsonFactory<>("artists", new ArtistJsonFactory()), builder);
	}

}
