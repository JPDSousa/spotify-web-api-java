package com.wrapper.spotify.models.authentication;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@SuppressWarnings("javadoc")
@Gson.TypeAdapters
@Value.Immutable
public interface ClientCredentials extends Credentials {

}
