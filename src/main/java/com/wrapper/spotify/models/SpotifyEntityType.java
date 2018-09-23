package com.wrapper.spotify.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("javadoc")
public enum SpotifyEntityType {

    @SerializedName("album")
    ALBUM("album"),
    @SerializedName("track")
    TRACK("track"),
    @SerializedName("artist")
    ARTIST("artist"),
    @SerializedName("user")
    USER("user"),
    @SerializedName("playlist")
    PLAYLIST("playlist"),
    @SerializedName("category")
    CATEGORY("category");

    public final String type;

    SpotifyEntityType(final String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
