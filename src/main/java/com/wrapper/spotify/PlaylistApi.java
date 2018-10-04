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
import com.wrapper.spotify.models.image.Image;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.playlist.ChangePlaylistDetails;
import com.wrapper.spotify.models.playlist.CreatePlaylistDetails;
import com.wrapper.spotify.models.playlist.Playlist;
import com.wrapper.spotify.models.playlist.PlaylistReorder;
import com.wrapper.spotify.models.playlist.PlaylistSnapshot;
import com.wrapper.spotify.models.playlist.PlaylistTrack;
import com.wrapper.spotify.models.playlist.RemoveAllTracks;
import com.wrapper.spotify.models.playlist.RemoveSpecificTracks;
import com.wrapper.spotify.models.playlist.RemoveSpecificTracksFromSnapshot;
import com.wrapper.spotify.models.playlist.SimplePlaylist;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("HardcodedFileSeparator")
public interface PlaylistApi {

    String BASE_URL = "/v1";

    @POST(BASE_URL + "/playlists/{playlist_id}/tracks")
    Call<PlaylistSnapshot> addTracks(@Path("playlist_id") String playlistId,
                                     @Query("uris") String trackURIs,
                                     @Query("position") Integer position);

    default Call<PlaylistSnapshot> addTracks(final String playlistId,
                                             final Collection<String> trackURIs,
                                             final Integer position) {
        return addTracks(playlistId, join(trackURIs), position);
    }

    @PUT(BASE_URL + "/playlists/{playlist_id}")
    Call<Void> update(@Path("playlist_id") String playlistId,
                      @Body ChangePlaylistDetails details);

    @POST(BASE_URL + "/users/{user_id}/playlists")
    Call<Playlist> create(@Path("user_id") String userId,
                          @Body CreatePlaylistDetails details);

    @GET(BASE_URL + "/me/playlists")
    Call<Page<SimplePlaylist>> getUserPlaylists(@Query("limit") Integer limit,
                                                @Query("offset") Integer offset);

    @GET(BASE_URL + "/playlists/{playlist_id}/images")
    Call<List<Image>> getCover(@Path("playlist_id") String playlistId);

    @PUT(BASE_URL + "/playlists/{playlist_id}/images")
    Call<Void> uploadCover(@Path("playlist_id") String playlistId,
                           @Body byte[] base64Image);

    @GET(BASE_URL + "/playlists/{playlist_id}")
    Call<Playlist> get(@Path("playlist_id") String playlistId,
                       @Query("market") CountryCode market);

    @GET(BASE_URL + "/playlists/{playlist_id}")
    Call<Playlist> get(@Path("playlist_id") String playlistId);

    @GET(BASE_URL + "/playlists/{playlist_id}/tracks")
    Call<Page<PlaylistTrack>> getTracks(@Path("playlist_id") String playlistId,
                                        @Query("limit") int limit,
                                        @Query("offset") int offset,
                                        @Query("market") CountryCode market);

    @HTTP(method = "DELETE", path = BASE_URL + "/playlists/{playlist_id}/tracks", hasBody = true)
    Call<PlaylistSnapshot> removeTracks(@Path("playlist_id") String playlistId,
                                        @Body RemoveAllTracks removeAllTracks);

    @HTTP(method = "DELETE", path = BASE_URL + "/playlists/{playlist_id}/tracks", hasBody = true)
    Call<PlaylistSnapshot> removeTracks(@Path("playlist_id") String playlistId,
                                        @Body RemoveSpecificTracks removeAllTracks);

    @HTTP(method = "DELETE", path = BASE_URL + "/playlists/{playlist_id}/tracks", hasBody = true)
    Call<PlaylistSnapshot> removeTracks(@Path("playlist_id") String playlistId,
                                        @Body RemoveSpecificTracksFromSnapshot removeAllTracks);

    @PUT(BASE_URL + "/playlists/{playlist_id}/tracks")
    Call<PlaylistSnapshot> reorderTracks(@Path("playlist_id") String playlistId,
                                         @Body PlaylistReorder reorder);

    @PUT(BASE_URL + "/playlists/{playlist_id}/tracks")
    Call<Void> replaceTracks(@Path("playlist_id") String playlistId,
                             @Query("uris") String uris);

    default String join(final Collection<String> items) {
        return String.join(",", items);
    }
}
