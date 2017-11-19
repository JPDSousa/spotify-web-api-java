package com.wrapper.spotify.methods.albums;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.album.Album;
import com.wrapper.spotify.models.album.AlbumJsonFactory;

@SuppressWarnings("javadoc")
public class AlbumRequest extends AbstractRequest<Album> {

	public static IdBuilder<Album> builder() {
		return new IdBuilder<>(ALBUMS + "/%s", AlbumRequest::new);
	}
	
	public AlbumRequest(IdBuilder<Album> builder) {
		super(new AlbumJsonFactory(), builder);
	}

}
