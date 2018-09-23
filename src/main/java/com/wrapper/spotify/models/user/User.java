package com.wrapper.spotify.models.user;

import com.wrapper.spotify.models.followers.Followers;
import org.immutables.gson.Gson;
import org.immutables.value.Value;

@SuppressWarnings("javadoc")
@Value.Immutable
@Gson.TypeAdapters(fieldNamingStrategy = true)
public interface User extends GenericUser {

    String email();

    Product product();

    String country();

    Followers followers();

    String birthdate();

}
