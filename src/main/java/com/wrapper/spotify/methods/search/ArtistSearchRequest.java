package com.wrapper.spotify.methods.search;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.SearchBuilder;
import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.artist.ArtistJsonFactory;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;

@SuppressWarnings("javadoc")
public class ArtistSearchRequest extends AbstractRequest<Page<Artist>> {

	public static SearchBuilder<Page<Artist>> builder() {
		return new SearchBuilder<>(SpotifyEntityType.ARTIST, ArtistSearchRequest::new);
	}
	
	public ArtistSearchRequest(SearchBuilder<Page<Artist>> builder) {
		super(new PageJsonFactory<>(new ArtistJsonFactory()), builder);
	}
	
}
