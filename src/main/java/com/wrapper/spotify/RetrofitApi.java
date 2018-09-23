/*******************************************************************************
 * Copyright (C) 2018 Joao Sousa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.wrapper.spotify;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ServiceLoader;

public final class RetrofitApi implements Api {

    public static final String DEFAULT_AUTHENTICATION_HOST = "accounts.spotify.com";

    public static final int DEFAULT_AUTHENTICATION_PORT = 443;

    public static final String DEFAULT_AUTHENTICATION_SCHEME = "https";

    public static Api create(final RetrofitApiConfig config) {
        return new RetrofitApi(config);
    }

    private final AlbumApi albumApi;
    private final ArtistApi artistApi;
    private final BrowseApi browseApi;
    private final PlaylistApi playlistApi;
    private final FollowApi followApi;
    private final LibraryApi libraryApi;
    private final PersonalizationApi personalizationApi;

    RetrofitApi(final RetrofitApiConfig config) {
        final Retrofit retrofit = config.retrofit();
        this.albumApi = retrofit.create(AlbumApi.class);
        this.artistApi = retrofit.create(ArtistApi.class);
        this.browseApi = retrofit.create(BrowseApi.class);
        this.playlistApi = retrofit.create(PlaylistApi.class);
        this.followApi = retrofit.create(FollowApi.class);
        this.libraryApi = retrofit.create(LibraryApi.class);
        this.personalizationApi = retrofit.create(PersonalizationApi.class);
    }

    @Override
    public AlbumApi albums() {
        return this.albumApi;
    }

    @Override
    public ArtistApi artists() {
        return this.artistApi;
    }

    @Override
    public BrowseApi browse() {
        return this.browseApi;
    }

    @Override
    public PlaylistApi playlists() {
        return this.playlistApi;
    }


    @Override
    public FollowApi follow() {
        return this.followApi;
    }

    @Override
    public LibraryApi library() {
        return this.libraryApi;
    }

    @Override
    public PersonalizationApi personalization() {
        return this.personalizationApi;
    }
}
