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
import com.wrapper.spotify.models.album.Albums;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.track.SimpleTrack;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Collection;

@SuppressWarnings("HardcodedFileSeparator")
public interface AlbumApi {

    String BASE_URL = "/v1/albums";

    @GET(BASE_URL + "/{id}")
    Call<Album> getAlbum(@Path("id") String id);

    @GET(BASE_URL + "/{id}")
    Call<Album> getAlbum(@Path("id") String id, @Query("market") CountryCode market);

    @GET(BASE_URL)
    Call<Albums> getAlbums(@Query("ids") String ids);

    @GET(BASE_URL)
    Call<Albums> getAlbums(@Query("ids") String ids, @Query("market") final CountryCode market);

    default Call<Albums> getAlbums(final Collection<String> ids) {
        return getAlbums(join(ids));
    }

    default Call<Albums> getAlbums(final Collection<String> ids, final CountryCode market) {
        return getAlbums(join(ids), market);
    }

    @GET(BASE_URL + "/{id}/tracks")
    Call<Page<SimpleTrack>> getAlbumTracks(@Path("id") String id);

    @GET(BASE_URL + "/{id}/tracks")
    Call<Page<SimpleTrack>> getAlbumTracks(@Path("id") String id,
                                           @Query("limit") int limit,
                                           @Query("offset") int offset);

    @GET(BASE_URL + "/{id}/tracks")
    Call<Page<SimpleTrack>> getAlbumTracks(@Path("id") String id,
                                           @Query("market") CountryCode market);

    @GET(BASE_URL + "/{id}/tracks")
    Call<Page<SimpleTrack>> getAlbumTracks(@Path("id") String id,
                                           @Query("limit") Integer limit,
                                           @Query("offset") Integer offset,
                                           @Query("market") CountryCode market);

    default String join(final Collection<String> ids) {
        return String.join(",", ids);
    }

}
