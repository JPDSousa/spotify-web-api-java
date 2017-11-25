package com.wrapper.spotify.methods.search;

import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.SearchBuilder;
import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.page.PageJsonFactory;
import com.wrapper.spotify.models.playlist.SimplePlaylist;
import com.wrapper.spotify.models.playlist.SimplePlaylistJsonFactory;

@SuppressWarnings("javadoc")
public class PlaylistSearchRequest extends AbstractRequest<Page<SimplePlaylist>> {
	
	public static SearchBuilder<Page<SimplePlaylist>> builder() {
		return new SearchBuilder<>(SpotifyEntityType.PLAYLIST, PlaylistSearchRequest::new);
	}

	public PlaylistSearchRequest(SearchBuilder<Page<SimplePlaylist>> builder) {
		super(new PageJsonFactory<>("playlists", new SimplePlaylistJsonFactory()), builder);
	}

}
