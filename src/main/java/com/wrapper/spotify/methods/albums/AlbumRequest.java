package com.wrapper.spotify.methods.albums;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.DefaultBuilder;
import com.wrapper.spotify.models.album.Album;
import com.wrapper.spotify.models.album.AlbumJsonFactory;

@SuppressWarnings("javadoc")
public class AlbumRequest extends AbstractRequest<Album> {

	public static DefaultBuilder<Album> builder(String albumId) {
		return new DefaultBuilder<>(joinPath(ALBUMS, albumId), AlbumRequest::new);
	}
	
	public AlbumRequest(DefaultBuilder<Album> builder) {
		super(new AlbumJsonFactory(), builder);
	}

}
