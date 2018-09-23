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

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.immutables.value.Value;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.Duration;
import java.util.ServiceLoader;
import java.util.concurrent.TimeUnit;

@Value.Immutable
public interface RetrofitApiConfig {

    @Value.Default
    default String host() {
        return "api.spotify.com";
    }

    @Value.Default
    default int port() {
        return 443;
    }

    @Value.Default
    default String scheme() {
        return "https";
    }

    @Value.Default
    default HttpUrl url() {
        return new HttpUrl.Builder()
                .host(host())
                .port(port())
                .scheme(scheme())
                .build();
    }

    @Value.Default
    default Duration readTimeout() {
        return Duration.ofSeconds(10);
    }

    @Value.Default
    default Duration connectTimeout() {
        return Duration.ofSeconds(10);
    }

    @Value.Default
    default RateLimiter rateLimiter() {
        return RateLimiter.create(20);
    }

    @Value.Default
    default OkHttpClient httpClient() {
        final Duration readTimeout = readTimeout();
        final Duration connectTimeout = connectTimeout();
        return new OkHttpClient.Builder()
                .addInterceptor(new RateLimiterInterceptor(rateLimiter()))
                .readTimeout(readTimeout.getSeconds(), TimeUnit.SECONDS)
                .connectTimeout(connectTimeout.getSeconds(), TimeUnit.SECONDS)
                .build();
    }

    @Value.Default
    default Gson gson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        for (final TypeAdapterFactory factory : ServiceLoader.load(TypeAdapterFactory.class)) {
            gsonBuilder.registerTypeAdapterFactory(factory);
        }
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

        return gsonBuilder.create();
    }

    @Value.Default
    default Retrofit retrofit() {
        return new Retrofit.Builder()
                .client(httpClient())
                .baseUrl(url())
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .build();
    }

}
