package com.wrapper.spotify.models.playlist;

import java.time.LocalDate;

import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.user.User;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@SuppressWarnings("javadoc")
@Value.Immutable
@Gson.TypeAdapters
public interface PlaylistTrack {

    LocalDate addedAt();

    User addedBy();

    Track track();

}
