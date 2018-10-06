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
import com.wrapper.spotify.models.album.AlbumSearchResults;
import com.wrapper.spotify.models.artist.ArtistSearchResults;
import com.wrapper.spotify.models.playlist.PlaylistSearchResults;
import com.wrapper.spotify.models.track.TrackSearchResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    String BASE_URL = "/v1/search";

    @GET(BASE_URL + "?type=artist")
    Call<ArtistSearchResults> artists(@Query("q") String query);

    @GET(BASE_URL + "?type=artist")
    Call<ArtistSearchResults> artists(@Query("q") String query, @Query("market") CountryCode market);

    @GET(BASE_URL + "?type=artist")
    Call<ArtistSearchResults> artists(@Query("q") String query,
                               @Query("limit") int limit,
                               @Query("offset") int offset);

    @GET(BASE_URL + "?type=artist")
    Call<ArtistSearchResults> artists(@Query("q") String query,
                               @Query("market") CountryCode market,
                               @Query("limit") int limit,
                               @Query("offset") int offset);

    @GET(BASE_URL + "?type=album")
    Call<AlbumSearchResults> albums(@Query("q") String query);

    @GET(BASE_URL + "?type=album")
    Call<AlbumSearchResults> albums(@Query("q") String query, @Query("market") CountryCode market);

    @GET(BASE_URL + "?type=album")
    Call<AlbumSearchResults> albums(@Query("q") String query,
                             @Query("limit") int limit,
                             @Query("offset") int offset);

    @GET(BASE_URL + "?type=album")
    Call<AlbumSearchResults> albums(@Query("q") String query,
                             @Query("market") CountryCode market,
                             @Query("limit") int limit,
                             @Query("offset") int offset);

    @GET(BASE_URL + "?type=track")
    Call<TrackSearchResults> tracks(@Query("q") String query);

    @GET(BASE_URL + "?type=track")
    Call<TrackSearchResults> tracks(@Query("q") String query, @Query("market") CountryCode market);

    @GET(BASE_URL + "?type=track")
    Call<TrackSearchResults> tracks(@Query("q") String query,
                             @Query("limit") int limit,
                             @Query("offset") int offset);

    @GET(BASE_URL + "?type=track")
    Call<TrackSearchResults> tracks(@Query("q") String query,
                             @Query("market") CountryCode market,
                             @Query("limit") int limit,
                             @Query("offset") int offset);

    @GET(BASE_URL + "?type=playlist")
    Call<PlaylistSearchResults> playlists(@Query("q") String query);

    @GET(BASE_URL + "?type=playlist")
    Call<PlaylistSearchResults> playlists(@Query("q") String query,
                                          @Query("limit") int limit,
                                          @Query("offset") int offset);

}
