package com.wrapper.spotify.models.playlist;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@SuppressWarnings("javadoc")
@Value.Immutable
@Gson.TypeAdapters
public interface PlaylistTracksInformation {

    String href();

    int total();

}
