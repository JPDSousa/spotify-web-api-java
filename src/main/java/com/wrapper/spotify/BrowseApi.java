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
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.models.album.NewReleases;
import com.wrapper.spotify.models.category.Categories;
import com.wrapper.spotify.models.category.Category;
import com.wrapper.spotify.models.playlist.FeaturedPlaylists;
import com.wrapper.spotify.models.playlist.Playlists;
import com.wrapper.spotify.models.recomendations.GenreSeeds;
import com.wrapper.spotify.models.recomendations.Recommendations;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.time.Instant;
import java.util.Map;

@SuppressWarnings("HardcodedFileSeparator")
public interface BrowseApi {

    String BASE_URL = "/v1/browse";

    @GET(BASE_URL + "/categories/{id}")
    Call<Category> getCategory(@Path("id") String id,
                               @Query("country")CountryCode countryCode,
                               @Query("locale") String locale);

    default Call<Category> getCategory(final String id,
                                       final CountryCode countryCode,
                                       final LanguageCode localeLanguage,
                                       final CountryCode localeCountry) {
        return getCategory(id, countryCode, String.join("_", localeLanguage.getName(), localeCountry.getAlpha2()));
    }

    @GET(BASE_URL + "/categories/{id}/playlists")
    Call<Playlists> getCategoryPlaylists(@Path("id") String id,
                                         @Query("country") CountryCode countryCode,
                                         @Query("limit") Integer limit,
                                         @Query("offset") Integer offset);

    @GET(BASE_URL + "/categories")
    Call<Categories> getCategories(@Query("country") CountryCode countryCode,
                                   @Query("locale") String locale,
                                   @Query("limit") Integer limit,
                                   @Query("offset") Integer offset);

    default Call<Categories> getCategories(final CountryCode countryCode,
                                               final LanguageCode localeLanguage,
                                               final CountryCode localeCountry,
                                               final Integer limit,
                                               final Integer offset) {
        return getCategories(countryCode, String.join("_", localeLanguage.getName(), localeCountry.getAlpha2()),
                limit, offset);
    }

    @GET(BASE_URL + "/featured-playlists")
    Call<FeaturedPlaylists> getFeaturedPlaylists(@Query("locale") String locale,
                                                 @Query("country") CountryCode countryCode,
                                                 @Query("timestamp") Instant timestamp,
                                                 @Query("limit") Integer limit,
                                                 @Query("offset") Integer offset);

    default Call<FeaturedPlaylists> getFeaturedPlaylists(final LanguageCode localeLanguage,
                                                      final CountryCode localeCountry,
                                                      final CountryCode countryCode,
                                                      final Instant timestamp,
                                                      final Integer limit,
                                                      final Integer offset) {
        return getFeaturedPlaylists(String.join("_", localeLanguage.getName(), localeCountry.getAlpha2()),
                countryCode,
                timestamp,
                limit,
                offset);
    }

    @GET(BASE_URL + "/new-releases")
    Call<NewReleases> getNewReleases(@Query("country") CountryCode countryCode,
                                     @Query("limit") Integer limit,
                                     @Query("offset") Integer offset);

    @GET("/v1/recommendations")
    Call<Recommendations> getRecommendations(@Query("limit") Integer limit,
                                             @Query("market") CountryCode countryCode,
                                             @Query("seed_artists") String seedArtists,
                                             @Query("seed_genres") String seedGenres,
                                             @Query("seed_tracks") String seedTracks,
                                             @QueryMap Map<String, String> trackAttributes);

    @GET("/v1/recommendations/available-genre-seeds")
    Call<GenreSeeds> getAvailableGenreSeeds();
}
