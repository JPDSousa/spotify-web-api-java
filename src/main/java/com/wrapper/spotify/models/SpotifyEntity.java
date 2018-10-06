package com.wrapper.spotify.models;

import org.immutables.mongo.Mongo;
import org.immutables.value.Value;

import java.net.URI;
import java.net.URL;
import java.util.Map;

@SuppressWarnings("javadoc")
public interface SpotifyEntity {

    @Mongo.Id
    @Value.Default
    default String mongoId() {
        return id();
    }

    SpotifyEntityType type();

    String id();

    URL href();

    Map<String, String> externalUrls();

    URI uri();

}
