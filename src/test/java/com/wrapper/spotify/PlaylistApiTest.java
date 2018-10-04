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
import com.wrapper.spotify.models.playlist.ImmutableChangePlaylistDetails;
import com.wrapper.spotify.models.playlist.ImmutableCreatePlaylistDetails;
import com.wrapper.spotify.models.playlist.ImmutablePlaylistReorder;
import com.wrapper.spotify.models.playlist.ImmutableRemoveAllTracks;
import com.wrapper.spotify.models.playlist.ImmutableURIEntry;
import com.wrapper.spotify.models.playlist.PlaylistReorder;
import com.wrapper.spotify.models.playlist.RemoveAllTracks;
import com.wrapper.spotify.models.playlist.URIEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

@SuppressWarnings({"JUnitTestMethodWithNoAssertions", "ExtendsUtilityClass"})
class PlaylistApiTest extends RetrofitTest {

    @Test
    public void testAddTracks() throws IOException {
        final String uris = "spotify:track:4iV5W9uYEdYUVa79Axb7Rh,spotify:track:1301WleyT98MSxVHPZCA6M";
        api.playlists().addTracks("3cEYpjA9oz9GiPac4AsH4n", uris, 0).execute();
    }

    @Test
    public void testChangePlaylistDetails() throws IOException {
        final ImmutableChangePlaylistDetails details = ImmutableChangePlaylistDetails.builder()
                .name("New Playlist Name")
                .build();
        api.playlists().update("3cEYpjA9oz9GiPac4AsH4n", details).execute();
    }

    @Test
    public void testCreatePlaylist() {
        final ImmutableCreatePlaylistDetails details = ImmutableCreatePlaylistDetails.builder()
                .name("New Playlist Name")
                .description("Just A Playlist Creation Test")
                .build();
        api.playlists().create("thelinmichael", details);
    }

    @Test
    public void testGetUserPlaylists() throws IOException {
        api.playlists().getUserPlaylists(10, 5).execute();
    }

    @Test
    public void testGetPlaylistCover() throws IOException {
        api.playlists().getCover("3cEYpjA9oz9GiPac4AsH4n").execute();
    }

    @Test
    public void testGetPlaylist() throws IOException {
        api.playlists().get("3cEYpjA9oz9GiPac4AsH4n").execute();
    }

    @Test
    public void testGetPlaylistMarket() throws IOException {
        api.playlists().get("3cEYpjA9oz9GiPac4AsH4n", CountryCode.ES).execute();
    }

    @Test
    public void testGetPlaylistTracks() throws IOException {
        api.playlists().getTracks("3cEYpjA9oz9GiPac4AsH4n", 10, 5, CountryCode.ES).execute();
    }

    @Test
    public void testRemoveTracksFromPlaylist() throws IOException {
        final URIEntry uri1 = ImmutableURIEntry.builder()
                .uri("spotify:track:2DB2zVP1LVu6jjyrvqD44z")
                .build();
        final URIEntry uri2 = ImmutableURIEntry.builder()
                .uri("spotify:track:5ejwTEOCsaDEjvhZTcU6lg")
                .build();
        final RemoveAllTracks tracks = ImmutableRemoveAllTracks.builder()
                .addTracks(uri1)
                .addTracks(uri2)
                .build();
        api.playlists().removeTracks("3cEYpjA9oz9GiPac4AsH4n", tracks).execute();
    }

    @Test
    public void testReorderPlaylistTracks() throws IOException {
        final PlaylistReorder reorder = ImmutablePlaylistReorder.builder()
                .rangeStart(1)
                .rangeLength(2)
                .insertBefore(3)
                .build();
        api.playlists().reorderTracks("3cEYpjA9oz9GiPac4AsH4n", reorder).execute();
    }

    @Test
    public void testReplaceTracks() throws IOException {
        api.playlists().replaceTracks("3cEYpjA9oz9GiPac4AsH4n", "somerandomuri").execute();
    }

    @Test
    public void testUploadCover() throws IOException {
        final String image = "imageContent";
        final Base64.Encoder encoder = Base64.getEncoder();

        api.playlists().uploadCover("3cEYpjA9oz9GiPac4AsH4n",
                encoder.encode(image.getBytes(Charset.forName("UTF-8")))).execute();
    }

}