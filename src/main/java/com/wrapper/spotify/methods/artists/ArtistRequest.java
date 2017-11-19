package com.wrapper.spotify.methods.artists;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.artist.ArtistJsonFactory;

@SuppressWarnings("javadoc")
public class ArtistRequest extends AbstractRequest<Artist> {

	public static IdBuilder<Artist> builder() {
		return new IdBuilder<>(ARTISTS + "/%s", ArtistRequest::new);
	}
	
	public ArtistRequest(IdBuilder<Artist> builder) {
		super(new ArtistJsonFactory(), builder);
	}
	
}
