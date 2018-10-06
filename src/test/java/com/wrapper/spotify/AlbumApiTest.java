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

import com.google.common.collect.ImmutableList;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.models.album.Album;
import org.junit.jupiter.api.Test;
import retrofit2.Call;

import java.io.IOException;

class AlbumApiTest extends RetrofitTest {

    @Test
    void testGetAlbum() throws IOException {
        final String id = "2BTZIqw0ntH9MvilQ3ewNY";
        final CountryCode code = CountryCode.GE;
        final Call<Album> albumRequest = api.albums().getAlbum(id, code);

        albumRequest.execute();
    }

    @Test
    void testGetAlbums() throws IOException {
        final String id1 = "382ObEPsp2rxGrnsizN5TX";
        final String id2 = "1A2GTWGtFfWp7KSQTwWOyo";
        final String id3 = "2noRn2Aes5aoNVsU6iWThc";
        final String ids = id1 + "," + id2 + "," + id3;
        final CountryCode code = CountryCode.ES;

        api.albums().getAlbums(ids, code).execute();
        api.albums().getAlbums(ImmutableList.of(id1, id2, id3), code).execute();
        api.albums().getAlbums(ids).execute();
        api.albums().getAlbums(ImmutableList.of(id1, id2, id3)).execute();
    }

    @Test
    void testGetAlbumTracks() throws IOException {
        final String id = "6akEvsycLGftJxYudPjmqK";
        final CountryCode market = CountryCode.AD;

        api.albums().getAlbumTracks(id).execute();
        api.albums().getAlbumTracks(id, market).execute();
        api.albums().getAlbumTracks(id, 2, 0).execute();
        api.albums().getAlbumTracks(id, 2, 0, market).execute();
    }

}