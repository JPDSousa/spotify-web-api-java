/**
 * Copyright (C) 2017 Spotify AB
 */
package com.wrapper.spotify.models;

import java.time.LocalDate;

import com.wrapper.spotify.models.track.Track;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@Gson.TypeAdapters
public interface LibraryTrack {

    LocalDate addedAt();

    Track track();

}
