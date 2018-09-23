package com.wrapper.spotify.models.playlist;

import com.google.gson.annotations.SerializedName;
import com.wrapper.spotify.models.SpotifyEntity;
import com.wrapper.spotify.models.image.ImageHolder;
import com.wrapper.spotify.models.user.SimpleUser;
import com.wrapper.spotify.models.user.User;

import java.util.Optional;

public interface GenericPlaylist extends ImageHolder, SpotifyEntity {

    boolean collaborative();

    SimpleUser owner();

    String name();

    @SerializedName("public")
    Optional<Boolean> publicAccess();

}
