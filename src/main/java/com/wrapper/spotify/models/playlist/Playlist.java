package com.wrapper.spotify.models.playlist;

import com.wrapper.spotify.models.followers.Followers;
import com.wrapper.spotify.models.page.Page;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@SuppressWarnings("javadoc")
@Value.Immutable
@Gson.TypeAdapters
public interface Playlist extends GenericPlaylist {

    String description();

    Followers followers();

    Page<PlaylistTrack> tracks();

}
