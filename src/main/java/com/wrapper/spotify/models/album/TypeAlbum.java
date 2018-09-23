package com.wrapper.spotify.models.album;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("javadoc")
public enum TypeAlbum {

    @SerializedName(value = "album", alternate = "ALBUM")
    ALBUM("album"),
    @SerializedName("single")
    SINGLE("single"),
    @SerializedName("appears_on")
    APPEARS_ON("appears_on"),
    @SerializedName("compilation")
    COMPILATION("compilation");

    private final String type;

    TypeAlbum(final String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }

    @Override
    public String toString() {
        return "TypeAlbum{" +
                "type='" + this.type + '\'' +
                "} " + super.toString();
    }
}
