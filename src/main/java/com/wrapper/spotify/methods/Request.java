package com.wrapper.spotify.methods;

import java.io.IOException;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.WebApiException;

@SuppressWarnings("javadoc")
public interface Request<T> {

	interface Builder<B, T> {
		Builder<B, T> httpManager(HttpManager httpManager);
		Builder<B, T> host(String host);
		Builder<B, T> port(int port);
		Builder<B, T> scheme(Url.Scheme scheme);
		Request<T> build();
	}

	T get() throws IOException, WebApiException;
	
	SettableFuture<T> getAsync();
	
	Url toUrl();

}
