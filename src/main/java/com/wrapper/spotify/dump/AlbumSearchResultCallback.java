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
package com.wrapper.spotify.dump;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.album.AlbumSearchResults;
import com.wrapper.spotify.models.album.Albums;
import com.wrapper.spotify.models.album.ReleasableAlbum;
import com.wrapper.spotify.models.page.Page;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Optional;
import java.util.logging.Logger;

public final class AlbumSearchResultCallback implements Callback<AlbumSearchResults> {

    private static final Logger logger = Logger.getLogger(AlbumSearchResultCallback.class.getName());

    public static Callback<AlbumSearchResults> create(final Api api,
                                                      final String query,
                                                      final int offset,
                                                      final int limit, final Callback<Albums> albumsCallback) {
        return new AlbumSearchResultCallback(albumsCallback, api, query, offset, limit);
    }

    private final Callback<Albums> albumsCallback;
    private final Api api;
    private final String query;
    private final int offset;
    private final int limit;

    private AlbumSearchResultCallback(final Callback<Albums> albumsCallback,
                                      final Api api,
                                      final String query,
                                      final int offset,
                                      final int limit) {
        this.albumsCallback = albumsCallback;
        this.api = api;
        this.query = query;
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public void onResponse(final Call<AlbumSearchResults> call, final Response<AlbumSearchResults> response) {
        Optional.ofNullable(response.body())
                .map(AlbumSearchResults::values)
                .ifPresent(this::processResults);
    }

    @SuppressWarnings("AutoBoxing")
    private void processResults(final Page<ReleasableAlbum> albumsPage) {
        final int target = albumsPage.offset() + albumsPage.limit();
        logger.info("Processing query '" + this.query + "' from " + albumsPage.offset()
                + " to " + target + " in " + albumsPage.total());

        Observable.just(albumsPage)
                .flatMapIterable(Page::items)
                .map(ReleasableAlbum::id)
                .buffer(20)
                .subscribe(ids -> this.api.albums().getAlbums(ids).enqueue(this.albumsCallback));
        albumsPage.next().ifPresent(next -> searchNext());
    }

    private void searchNext() {
        this.api.search().albums(this.query, this.limit, this.offset + this.limit)
                .enqueue(create(this.api, this.query, this.limit + this.offset, this.limit, this.albumsCallback));
    }

    @Override
    public void onFailure(final Call<AlbumSearchResults> call, final Throwable t) {
        throw new IllegalArgumentException("Call failed: " + call.request(), t);
    }

    @Override
    public String toString() {
        return "AlbumSearchResultCallback{" +
                "albumsCallback=" + this.albumsCallback +
                ", api=" + this.api +
                ", query='" + this.query + '\'' +
                ", offset=" + this.offset +
                ", limit=" + this.limit +
                "}";
    }
}
