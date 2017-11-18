package com.wrapper.spotify.methods.albums;

import static com.wrapper.spotify.methods.Paths.ALBUMS;

import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdBuilder;
import com.wrapper.spotify.models.album.Album;
import com.wrapper.spotify.models.album.AlbumJsonFactory;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class AlbumRequest extends AbstractRequest<Album> {

	public static IdBuilder<Album> builder() {
		return new IdBuilder<>(ALBUMS + "/%s", AlbumRequest::new);
	}
	
	private final JsonFactory<Album> albumFactory;

	public AlbumRequest(IdBuilder<Album> builder) {
		super(builder);
		albumFactory = new AlbumJsonFactory();
	}

	@Override
	protected Album fromJson(JSONObject jsonObject) {
		return albumFactory.fromJson(jsonObject);
	}

}
