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
package com.wrapper.spotify.utils;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public final class TestDispatcher extends Dispatcher {

    public static final char QUERY_TOKEN = '?';

    @SuppressWarnings("PublicMethodNotExposedInInterface") // not on my side
    @Override
    public MockResponse dispatch(final RecordedRequest request) {
        final String method = request.getMethod();
        if ("GET".equals(method)) {
            return mockGetResponse(request);
        }
        return new MockResponse()
                .setResponseCode(204);
    }

    private MockResponse mockGetResponse(final RecordedRequest request) {
        final String path = request.getPath()
                .substring(1)
                .replace("?", "--")
                .replace("%2C", ",")+ ".json";
        try {
            final URL url = getClass().getClassLoader().getResource(path);
            final Path bodyPath = Paths.get(Objects.requireNonNull(url).toURI());
            final String content = new String(Files.readAllBytes(bodyPath), Charset.forName("UTF-8"));
            return new MockResponse()
                    .setResponseCode(200)
                    .setBody(content);
        } catch (final URISyntaxException e) {
            throw new IllegalArgumentException(String.format("%s is not a valid path", path), e);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

}
