package com.wrapper.spotify.methods;

import java.io.IOException;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.WebApiException;

@SuppressWarnings("javadoc")
public interface Request<T> {

	interface Builder<E, T> {
		Builder<E, T> httpManager(HttpManager httpManager);
		Builder<E, T> host(String host);
		Builder<E, T> port(int port);
		Builder<E, T> scheme(Url.Scheme scheme);
		Request<T> build();
	}

	T get() throws IOException, WebApiException;
	
	SettableFuture<T> getAsync();
	
	Url toUrl();

}
