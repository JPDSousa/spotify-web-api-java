/**
 * Copyright (C) 2017 Spotify AB
 */
package com.wrapper.spotify.methods.me;

import com.google.common.collect.Lists;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.IdsBuilder;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("javadoc")
public class ContainsMySavedTracksRequest extends AbstractRequest<List<Boolean>> {

	public static IdsBuilder<List<Boolean>> builder() {
		return new IdsBuilder<>(50, ME_TRACKS_CONTAINS, b -> {
			b.header("Content-Type", "application/json");
			return new ContainsMySavedTracksRequest(b);
		});
	}
	
	public ContainsMySavedTracksRequest(IdsBuilder<List<Boolean>> builder) {
		super(null, builder);
	}

	@Override
	public List<Boolean> exec() throws IOException {
		return createBooleans(getJson());
	}
	
	private static List<Boolean> createBooleans(String response) {
		final JSONArray jsonArray = JSONArray.fromObject(response);
		final List<Boolean> returnedArray = Lists.newArrayListWithCapacity(jsonArray.size());
		for (Object item : jsonArray) {
			returnedArray.add(Boolean.valueOf(String.valueOf(item)));
		}
		return returnedArray;
	}

}
