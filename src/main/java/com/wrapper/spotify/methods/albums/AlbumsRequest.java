package com.wrapper.spotify.methods.albums;

import static com.wrapper.spotify.methods.Paths.ALBUMS;

import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdsBuilder;
import com.wrapper.spotify.models.album.Album;
import com.wrapper.spotify.models.album.AlbumJsonFactory;

import net.sf.json.JSONObject;

import java.util.List;

@SuppressWarnings("javadoc")
public class AlbumsRequest extends AbstractRequest<List<Album>> {

	public static IdsBuilder<Album> builder() {
		return new IdsBuilder<>(ALBUMS, AlbumsRequest::new);
	}
	
	private final JsonFactory<Album> jsonFactory;

	public AlbumsRequest(IdsBuilder<Album> builder) {
		super(builder);
		jsonFactory = new AlbumJsonFactory();
	}

	@Override
	protected List<Album> fromJson(JSONObject jsonObject) {
		return jsonFactory.fromJson(jsonObject.getJSONArray("albums"));
	}
}