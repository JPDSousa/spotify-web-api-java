package com.wrapper.spotify.methods;

import java.io.IOException;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.WebApiException;

@SuppressWarnings("javadoc")
public interface Request<T> {
	
	public static final String ALBUMS = "/v1/albums";
	public static final String ARTISTS = "/v1/artists";
	public static final String TRACKS = "/v1/tracks";
	public static final String AUDIO_FEATURES = "/v1/audio-features";
	public static final String SEARCH = "/v1/search";
	
	public static final String TOKEN = "/api/token";
	public static final String AUTHORIZATION = "/authorize";
	
	public static final String BROWSE = "/v1/browse";
	public static final String NEW_RELEASES = BROWSE + "/new-releases";
	public static final String FEATURED_PLAYLISTS = BROWSE + "/featured-playlists";
	
	public static final String USERS = "/v1/users";
	
	public static final String ME = "/v1/me";
	public static final String ME_TRACKS = ME + "/tracks";
	public static final String ME_TRACKS_CONTAINS = ME_TRACKS + "/contains";

	interface Builder<B, T> {
		Builder<B, T> httpManager(HttpManager httpManager);
		Builder<B, T> host(String host);
		Builder<B, T> port(int port);
		Builder<B, T> scheme(Url.Scheme scheme);
		Request<T> build();
	}

	T exec() throws IOException, WebApiException;
	
	SettableFuture<T> execAsync();
	
	Url toUrl();

	String toStringWithQueryParameters();

}
