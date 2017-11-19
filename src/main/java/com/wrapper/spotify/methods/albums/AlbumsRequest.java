package com.wrapper.spotify.methods.albums;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdsBuilder;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.album.Album;
import com.wrapper.spotify.models.album.AlbumJsonFactory;

import java.util.List;

@SuppressWarnings("javadoc")
public class AlbumsRequest extends AbstractRequest<List<Album>> {

	public static IdsBuilder<List<Album>> builder() {
		return new IdsBuilder<>(ALBUMS, AlbumsRequest::new);
	}
	
	public AlbumsRequest(IdsBuilder<List<Album>> builder) {
		super(new ListJsonFactory<>("albums", new AlbumJsonFactory()), builder);
	}
	
}