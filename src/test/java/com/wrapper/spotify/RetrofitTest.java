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

import com.wrapper.spotify.utils.TestDispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.time.Duration;

public abstract class RetrofitTest {

    protected static MockWebServer webServer;
    protected static Api api;

    @BeforeAll
    static void setUp() throws IOException {
        webServer = new MockWebServer();
        webServer.setDispatcher(new TestDispatcher());
        webServer.start();

        final ImmutableRetrofitApiConfig config = ImmutableRetrofitApiConfig.builder()
                .url(webServer.url("/"))
                .readTimeout(Duration.ofSeconds(3))
                .connectTimeout(Duration.ofSeconds(1))
                .build();

        api = RetrofitApi.create(config);
    }

    @AfterAll
    static void tearDown() throws IOException {
        webServer.shutdown();
    }

}
