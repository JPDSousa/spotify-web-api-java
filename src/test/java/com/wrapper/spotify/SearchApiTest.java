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
import org.junit.jupiter.api.Test;

import java.io.IOException;

@SuppressWarnings({"ExtendsUtilityClass", "JUnitTestMethodWithNoAssertions"})
class SearchApiTest extends RetrofitTest {

    @Test
    void testSearchArtists() throws IOException {
        api.search().artists("Muse").execute();
        api.search().artists("Muse", CountryCode.US).execute();
        api.search().artists("Muse", CountryCode.US, 10, 5).execute();
    }

    @Test
    void testSearchAlbums() throws IOException {
        api.search().albums("The Heist").execute();
        api.search().albums("The Heist", CountryCode.US).execute();
        api.search().albums("The Heist", CountryCode.US, 10, 5).execute();
    }

    @Test
    void testSearchTracks() throws IOException {
        api.search().tracks("Tonight").execute();
        api.search().tracks("Tonight", CountryCode.US).execute();
        api.search().tracks("Tonight", CountryCode.US, 10, 5).execute();
    }

    @Test
    void testSearchPlaylists() throws IOException {
        api.search().playlists("Best").execute();
        api.search().playlists("Best", 10, 5).execute();
    }
}