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

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.models.album.Album;
import com.wrapper.spotify.models.track.Track;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

import java.util.Collection;

@SuppressWarnings("HardcodedFileSeparator")
public interface LibraryApi {

    @GET("/me/albums/contains")
    Call<Boolean> checkSavedAlbum(@Query("ids") String ids);

    default Call<Boolean> checkSavedAlbum(final Collection<String> ids) {
        return checkSavedAlbum(join(ids));
    }

    @GET("/me/tracks/contains")
    Call<Boolean> checkSavedTracks(@Query("ids") String ids);

    default Call<Boolean> checkSavedTracks(final Collection<String> ids) {
        return checkSavedTracks(join(ids));
    }

    @GET("/me/albums")
    Call<Collection<Album>> getSavedAlbums(@Query("market") CountryCode market,
                                           @Query("limit") Integer limit,
                                           @Query("offset") Integer offset);

    @GET("/me/tracks")
    Call<Collection<Track>> getSavedTracks(@Query("market") CountryCode market,
                                           @Query("limit") Integer limit,
                                           @Query("offset") Integer offset);

    @DELETE("/me/albums")
    Call<Void> removeSavedAlbums(@Query("ids") String ids);

    default Call<Void> removeSavedAlbums(final Collection<String> ids) {
        return removeSavedAlbums(join(ids));
    }

    @DELETE("/me/tracks")
    Call<Void> removeSavedTracks(@Query("ids") String ids);

    default Call<Void> removeSavedTracks(final Collection<String> ids) {
        return removeSavedTracks(join(ids));
    }

    @PUT("/me/albums")
    Call<Void> saveAlbums(@Query("ids") String ids);

    default Call<Void> saveAlbums(final Collection<String> ids) {
        return saveAlbums(join(ids));
    }

    @PUT("/me/tracks")
    Call<Void> saveTracks(@Query("ids") String ids);

    default Call<Void> saveTracks(final Collection<String> ids) {
        return saveTracks(join(ids));
    }

    default String join(final Collection<String> items) {
        return String.join(",", items);
    }

}
