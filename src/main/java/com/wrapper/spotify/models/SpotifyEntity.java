package com.wrapper.spotify.models;

import java.net.URI;
import java.net.URL;
import java.util.Map;

@SuppressWarnings("javadoc")
public interface SpotifyEntity {

    SpotifyEntityType type();

    String id();

    URL href();

    Map<String, String> externalUrls();

    URI uri();

}
