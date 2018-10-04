package com.wrapper.spotify.models.playlist;

import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.user.User;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.Optional;

@SuppressWarnings("javadoc")
@Value.Immutable
@Gson.TypeAdapters
public interface PlaylistTrack {

    Optional<LocalDate> addedAt();

    Optional<User> addedBy();

    Track track();

}
