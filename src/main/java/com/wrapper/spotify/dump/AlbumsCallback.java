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

import com.google.common.util.concurrent.FutureCallback;
import com.wrapper.spotify.models.album.AlbumRepository;
import com.wrapper.spotify.models.album.Albums;
import one.util.streamex.StreamEx;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class AlbumsCallback implements Callback<Albums> {

    public static Callback<Albums> create(final AlbumRepository collection,
                                          final FutureCallback<Integer> dbOperationCallback) {
        return new AlbumsCallback(collection, dbOperationCallback);
    }

    private final AlbumRepository collection;
    private final FutureCallback<Integer> dbOperationCallback;

    private AlbumsCallback(final AlbumRepository collection,
                           final FutureCallback<Integer> dbOperationCallback) {
        this.collection = collection;
        this.dbOperationCallback = dbOperationCallback;
    }

    @Override
    public void onResponse(final Call<Albums> call, final Response<Albums> response) {
        StreamEx.ofNullable(response.body())
                .map(Albums::values)
                .forEach(this.collection::insert);
    }

    @Override
    public void onFailure(final Call<Albums> call, final Throwable t) {
        throw new IllegalArgumentException("Call failed: " + call.request(), t);
    }
}
