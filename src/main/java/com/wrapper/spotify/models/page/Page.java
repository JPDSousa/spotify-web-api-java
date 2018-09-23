package com.wrapper.spotify.models.page;

import java.util.List;
import java.util.Optional;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@Gson.TypeAdapters
public interface Page<T> extends GenericPage<T> {

    int offset();

    Optional<String> previous();

}
