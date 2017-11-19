package com.wrapper.spotify.methods.artists;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.DefaultBuilder;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.artist.ArtistJsonFactory;

@SuppressWarnings("javadoc")
public class ArtistRequest extends AbstractRequest<Artist> {

	public static DefaultBuilder<Artist> builder(String artistId) {
		return new DefaultBuilder<>(joinPath(ARTISTS, artistId), ArtistRequest::new);
	}
	
	public ArtistRequest(DefaultBuilder<Artist> builder) {
		super(new ArtistJsonFactory(), builder);
	}
	
}
