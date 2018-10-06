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
package com.wrapper.spotify.system;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.MoreExecutors;
import com.mongodb.MongoClient;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.CredentialsProvider;
import com.wrapper.spotify.ImmutableCredentialsProvider;
import com.wrapper.spotify.ImmutableRetrofitApiConfig;
import com.wrapper.spotify.RetrofitApi;
import com.wrapper.spotify.RetrofitApiConfig;
import com.wrapper.spotify.dump.AlbumSearchResultCallback;
import com.wrapper.spotify.dump.AlbumsCallback;
import com.wrapper.spotify.dump.MongoOperationHandler;
import com.wrapper.spotify.models.album.AlbumRepository;
import com.wrapper.spotify.models.album.Albums;
import org.immutables.mongo.repository.RepositorySetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Callback;

import java.time.Year;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class DumpToMongoDBTest {

    private Api api;
    private AlbumRepository albumsCollection;
    private FutureCallback<Integer> dbCallback;

    @BeforeEach
    public void setup() {
        final CredentialsProvider credentials = ImmutableCredentialsProvider.builder()
                .clientId("6d27a1bcfddc4d8b909a415f78ea1233")
                .clientSecret("e3d01bbb6b634760a3a7bc41e337966d")
                .build();
        final RetrofitApiConfig config = ImmutableRetrofitApiConfig.builder()
                .credentials(credentials)
                .build();
        this.api = RetrofitApi.create(config);

        final MongoClient client = new MongoClient("localhost", 27017);
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        this.albumsCollection = new AlbumRepository(RepositorySetup.builder()
                .gson(config.credentials().gson())
                .executor(MoreExecutors.listeningDecorator(threadPool))
                .database(client.getDatabase("test"))
                .build());
        this.dbCallback = MongoOperationHandler.create();
    }

    @Test
    public void dumpToMongoDB() {
        final Callback<Albums> albumsCallback = AlbumsCallback.create(this.albumsCollection, this.dbCallback);
        final int limit = 50;
        for (int i = Year.now().getValue(); i > 1800; i--) {
            final String query = "year:" + i;
            this.api.search().albums(query, limit, 0).enqueue(AlbumSearchResultCallback.create(
                    this.api,
                    query,
                    0,
                    limit,
                    albumsCallback));
        }
        while (true);
    }
}
