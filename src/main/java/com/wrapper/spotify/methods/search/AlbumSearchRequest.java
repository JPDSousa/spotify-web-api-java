package com.wrapper.spotify.methods.search;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.SearchBuilder;
import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.album.SimpleAlbum;
import com.wrapper.spotify.models.album.SimpleAlbumJsonFactory;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;

@SuppressWarnings("javadoc")
public class AlbumSearchRequest extends AbstractRequest<Page<SimpleAlbum>> {

	public static SearchBuilder<Page<SimpleAlbum>> builder() {
		return new SearchBuilder<Page<SimpleAlbum>>(SpotifyEntityType.ALBUM, AlbumSearchRequest::new);
	}
	
	public AlbumSearchRequest(SearchBuilder<Page<SimpleAlbum>> builder) {
		super(new PageJsonFactory<>("albums", new SimpleAlbumJsonFactory()), builder);
	}

}
