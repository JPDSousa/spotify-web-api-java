package com.wrapper.spotify;

import com.google.common.util.concurrent.RateLimiter;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.methods.*;
import com.wrapper.spotify.methods.albums.AlbumRequest;
import com.wrapper.spotify.methods.albums.AlbumsRequest;
import com.wrapper.spotify.methods.artists.AlbumsForArtistRequest;
import com.wrapper.spotify.methods.artists.ArtistRequest;
import com.wrapper.spotify.methods.artists.ArtistsRequest;
import com.wrapper.spotify.methods.artists.RelatedArtistsRequest;
import com.wrapper.spotify.methods.artists.TopTracksRequest;
import com.wrapper.spotify.methods.audiofeatures.AudioFeatureRequest;
import com.wrapper.spotify.methods.authentication.AuthorizationCodeGrantRequest;
import com.wrapper.spotify.methods.authentication.AuthorizationURLRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.methods.authentication.RefreshAccessTokenRequest;
import com.wrapper.spotify.methods.browse.FeaturedPlaylistsRequest;
import com.wrapper.spotify.methods.browse.NewReleasesRequest;
import com.wrapper.spotify.methods.me.AddToMySavedTracksRequest;
import com.wrapper.spotify.methods.me.ContainsMySavedTracksRequest;
import com.wrapper.spotify.methods.me.CurrentUserRequest;
import com.wrapper.spotify.methods.me.GetMySavedTracksRequest;
import com.wrapper.spotify.methods.me.RemoveFromMySavedTracksRequest;
import com.wrapper.spotify.methods.page.PageRequest;
import com.wrapper.spotify.methods.search.AlbumSearchRequest;
import com.wrapper.spotify.methods.search.ArtistSearchRequest;
import com.wrapper.spotify.methods.search.TrackSearchRequest;
import com.wrapper.spotify.methods.tracks.TrackRequest;
import com.wrapper.spotify.methods.tracks.TracksRequest;
import com.wrapper.spotify.methods.users.AddTrackToPlaylistRequest;
import com.wrapper.spotify.methods.users.ChangePlaylistDetailsRequest;
import com.wrapper.spotify.methods.users.PlaylistCreationRequest;
import com.wrapper.spotify.methods.users.PlaylistRequest;
import com.wrapper.spotify.methods.users.PlaylistTracksRequest;
import com.wrapper.spotify.methods.users.UserPlaylistsRequest;
import com.wrapper.spotify.methods.users.UserRequest;
import com.wrapper.spotify.models.album.SimpleAlbum;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.audio.AudioFeature;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.user.User;

import net.sf.json.JSONArray;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import org.rocksdb.RocksDB;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Instances of the Api class provide access to the Spotify Web API.
 */
@SuppressWarnings("javadoc")
public class Api {
	
	static {
		RocksDB.loadLibrary();
	}

	/**
	 * The default host of Spotify API calls.
	 */
	public static final String DEFAULT_HOST = "api.spotify.com";

	/**
	 * The default port of Spotify API calls.
	 */
	public static final int DEFAULT_PORT = 443;

	/**
	 * A HttpManager configured with default settings.
	 */
	public static final HttpClient DEFAULT_HTTP_CLIENT = HttpClientBuilder.create().build();

	/**
	 * The default http scheme of Spotify API calls.
	 */
	public static final String DEFAULT_SCHEME = "https";

	public static final String DEFAULT_AUTHENTICATION_HOST = "accounts.spotify.com";

	public static final int DEFAULT_AUTHENTICATION_PORT = 443;

	public static final String DEFAULT_AUTHENTICATION_SCHEME = "https";

	/**
	 * Api instance with the default settings.
	 */
	public static final Api DEFAULT_API = Api.builder().build();

	private final HttpClient httpManager;
	private final String scheme;
	private final int port;
	private final String host;
	private final String accessToken;
	private final String refreshToken;
	private final String clientId;
	private final String clientSecret;
	private final String redirectURI;
	private final RateLimiter rateLimiter;
	private final RocksDB cache;

	private Api(Builder builder) {
		assert (builder.host != null);
		assert (builder.port > 0);
		assert (builder.scheme != null);
		assert builder.rateLimiter != null;

		if (builder.httpManager == null) {
			this.httpManager = SpotifyHttpManager
					.builder()
					.build();
		} else {
			this.httpManager = builder.httpManager;
		}
		cache = builder.cache;
		rateLimiter = builder.rateLimiter;
		scheme = builder.scheme;
		host = builder.host;
		port = builder.port;
		accessToken = builder.accessToken;
		refreshToken = builder.refreshToken;
		clientId = builder.clientId;
		clientSecret = builder.clientSecret;
		redirectURI = builder.redirectURI;
	}

	public static Builder builder() {
		return new Builder();
	}

	public <I> Request<Page<I>> getNextPage(Page<I> currentPage) {
		final String href = currentPage.getNext();
		if(href == null) {
			return null;
		}
		return getPage(currentPage, href);
	}

	public <I> Request<Page<I>> getPreviousPage(Page<I> currentPage) {
		final String href = currentPage.getPrevious();
		if(href == null) {
			return null;
		}
		return getPage(currentPage, href);
	}

	private <I> Request<Page<I>> getPage(Page<I> currentPage, String href) {
		try {
			return PageRequest.builder(href, currentPage.getPropName(), currentPage.getJsonFactory())
					.build();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns a an album with the id given below.
	 *
	 * @param id The base62 id of the album you're trying to retrieve.
	 * @return An {AlbumRequest.Builder} instance.
	 */
	public AlbumRequest.Builder getAlbum(String id) {
		return setDefaults(AlbumRequest.builder(id));
	}

	public AlbumsRequest.Builder getAlbums(String... ids) {
		return getAlbums(Arrays.asList(ids));
	}

	public AlbumsRequest.Builder getAlbums(List<String> ids) {
		return setDefaults(AlbumsRequest.builder())
				.ids(ids);
	}

	public AlbumsForArtistRequest.Builder getAlbumsForArtist(String artistId) {
		return setDefaults(AlbumsForArtistRequest.builder(artistId));
	}

	public DefaultBuilder<Artist> getArtist(String id) {
		return setDefaults(ArtistRequest.builder(id));
	}

	public IdsBuilder<List<Artist>> getArtists(String... ids) {
		return getArtists(Arrays.asList(ids));
	}

	public IdsBuilder<List<Artist>> getArtists(List<String> ids) {
		return setDefaults(ArtistsRequest.builder())
				.ids(ids);
	}

	public TrackRequest.Builder getTrack(String id) {
		return setDefaults(TrackRequest.builder(id));
	}

	public TracksRequest.Builder getTracks(String... ids) {
		return getTracks(Arrays.asList(ids));
	}

	public TracksRequest.Builder getTracks(List<String> ids) {
		return setDefaults(TracksRequest.builder())
				.ids(ids);
	}

	public SearchBuilder<Page<SimpleAlbum>> searchAlbums(String query) {
		return setDefaults(AlbumSearchRequest.builder())
				.query(query);
	}

	public SearchBuilder<Page<Track>> searchTracks(String query) {
		return setDefaults(TrackSearchRequest.builder())
				.query(query);
	}

	public SearchBuilder<Page<Artist>> searchArtists(String query) {
		return setDefaults(ArtistSearchRequest.builder())
				.query(query);
	}

	public NewReleasesRequest.Builder getNewReleases() {
		return setDefaults(NewReleasesRequest.builder());
	}

	public DefaultBuilder<AudioFeature> getAudioFeature(String id) {
		return setDefaults(AudioFeatureRequest.builder(id));
	}

	/**
	 * Used to get Featured Playlists.
	 * @return A builder that can be used to build requests to get featured playlists.
	 */
	public FeaturedPlaylistsRequest.Builder getFeaturedPlaylists() {
		return setDefaults(FeaturedPlaylistsRequest.builder());
	}

	public TopTracksRequest.Builder getTopTracksForArtist(String artistId, CountryCode countryCode) {
		return setDefaults(TopTracksRequest.builder(artistId))
				.country(countryCode);
	}

	public DefaultBuilder<User> getUser(String userId) {
		return setDefaults(UserRequest.builder(userId));
	}

	public UserPlaylistsRequest.Builder getPlaylistsForUser(String userId) {
		return setDefaults(UserPlaylistsRequest.builder(userId));
	}

	/**
	 * Returns a builder that can be used to build requests for authorization code
	 * grants.
	 * Requires client ID, client secret, and redirect URI to be set.
	 * @param code An authorization code.
	 * @return A builder that builds authorization code grant requests.
	 */
	public AuthorizationCodeGrantRequest.Builder authorizationCodeGrant(String code) {
		return AuthorizationCodeGrantRequest.builder()
				.grantType("authorization_code")
				.basicAuthorizationHeader(clientId, clientSecret)
				.code(code)
				.redirectUri(redirectURI);
	}

	/**
	 * Returns a builder that can be used to build requests to refresh an access token
	 * that has been retrieved using the authorization code grant flow.
	 * @return A builder that builds refresh access token requests.
	 */
	public RefreshAccessTokenRequest.Builder refreshAccessToken() {
		return RefreshAccessTokenRequest.builder()
				.grantType("refresh_token")
				.refreshToken(refreshToken)
				.basicAuthorizationHeader(clientId, clientSecret);
	}

	/**
	 * Returns a builder that can be used to build requests for client credential grants.
	 * Requires client ID and client secret to be set.
	 * @return A builder that builds client credential grant requests.
	 */
	public ClientCredentialsGrantRequest.Builder clientCredentialsGrant() {
		return ClientCredentialsGrantRequest.builder()
				.grantType("client_credentials")
				.basicAuthorizationHeader(clientId, clientSecret);
	}

	/**
	 * Get a playlist.
	 * @param userId The playlist's owner's username.
	 * @param playlistId The playlist's ID.
	 * @return A builder object that can be used to build a request to retrieve a playlist.
	 */
	public PlaylistRequest.Builder getPlaylist(String userId, String playlistId) {
		return setDefaults(PlaylistRequest.builder(userId, playlistId));
	}

	/**
	 * Get information about the user that has given authorization to the application.
	 * @return A builder object that can be used to build a request to retrieve information
	 * about the current user.
	 */
	public CurrentUserRequest.Builder getMe() {
		return setDefaults(CurrentUserRequest.builder());
	}

	/**
	 * Create a playlist.
	 * @param userId The playlist's owner.
	 * @param title The name of the playlist.
	 * @return A builder object that can be used to build a request to create a playlist.
	 */
	public PlaylistCreationRequest.Builder createPlaylist(String userId, String title) {
		return setDefaults(PlaylistCreationRequest.builder(userId))
				.title(title);
	}

	/**
	 * Get artists related/similar to an artist.
	 * @param artistId The artist's id.
	 * @return A builder object that can be used to build a request to retrieve similar artists.
	 */
	public DefaultBuilder<List<Artist>> getArtistRelatedArtists(String artistId) {
		return setDefaults(RelatedArtistsRequest.builder(artistId));
	}

	/**
	 * Get a playlist's tracks.
	 * @param userId The playlist's owner's username.
	 * @param playlistId The playlist's id.
	 * @return A builder object that can be used to build a request to retrieve playlist tracks.
	 */
	public PlaylistTracksRequest.Builder getPlaylistTracks(String userId, String playlistId) {
		return setDefaults(PlaylistTracksRequest.builder(userId, playlistId));
	}

	private <B extends AbstractBuilder<?, ?>> B setDefaults(B builder) {
		builder.httpClient(httpManager);
		builder.scheme(scheme);
		builder.host(host);
		builder.port(port);
		builder.rateLimiter(rateLimiter);
		builder.cache(cache);
		if (accessToken != null) {
			builder.header("Authorization", "Bearer " + accessToken);
		}
		builder.header("Accept", "application/json");
		builder.header("Content-Type", "application/json");
		return builder;
	}

	/**
	 * Add tracks to a playlist.
	 * @param userId The owner's username.
	 * @param playlistId The playlist's ID.
	 * @param trackUris URIs of the tracks to add.
	 * @return A builder object that can e used to build a request to add tracks to a playlist.
	 */
	public AddTrackToPlaylistRequest.Builder addTracksToPlaylist(String userId, String playlistId, List<String> trackUris) {
		final JSONArray tracks = new JSONArray();
		tracks.addAll(trackUris);
		return setDefaults(AddTrackToPlaylistRequest.builder(userId, playlistId))
				.body(tracks);
	}

	/**
	 * Update a playlist's properties.
	 * @param userId The owner's username.
	 * @param playlistId The playlist's ID.
	 * @return A builder object that can be used to build a request to change a playlist's details.
	 */
	public ChangePlaylistDetailsRequest.Builder changePlaylistDetails(String userId, String playlistId) {
		return setDefaults(ChangePlaylistDetailsRequest.builder(userId, playlistId));
	}

	/**
	 * Get a users Your Music tracks.
	 * @return A builder object that can be used to build a request to get the user's Your Music library.
	 */
	public GetMySavedTracksRequest.Builder getMySavedTracks() {
		return setDefaults(GetMySavedTracksRequest.builder());
	}

	/**
	 * Check if a track is saved in the user's Your Music library.
	 * @param trackIds The tracks ids to check for in the user's Your Music library.
	 * @return A builder object that can be used to check if a user has saved a track.
	 */
	public IdsBuilder<List<Boolean>> containsMySavedTracks(List<String> trackIds) {
		return setDefaults(ContainsMySavedTracksRequest.builder())
				.ids(trackIds);
	}

	/**
	 * Remove a track if saved to the user's Your Music library.
	 * @param trackIds The track ids to remove from the user's Your Music library.
	 * @return A builder object that can be used to remove tracks from the user's library.
	 */
	public IdsBuilder<String> removeFromMySavedTracks(List<String> trackIds) {
		return setDefaults(RemoveFromMySavedTracksRequest.builder())
				.ids(trackIds);
	}

	/**
	 * Save tracks in the user's Your Music library.
	 * @param trackIds The track ids to add to the user's library.
	 * @return A builder object that can be used to add tracks to the user's library.
	 */
	public AddToMySavedTracksRequest.Builder addToMySavedTracks(List<String> trackIds) {
		return setDefaults(AddToMySavedTracksRequest.builder())
				.ids(trackIds);
	}

	/**
	 * Retrieve a URL where the user can give the application permissions.
	 * @param scopes The scopes corresponding to the permissions the application needs
	 * @param state state A parameter that you can use to maintain a value between the request
	 *              and the callback to redirect_uri.It is useful to prevent CSRF exploits.
	 * @return The URL where the user can give application permissions.
	 */
	public String createAuthorizeURL(List<String> scopes, String state) {
		final AuthorizationURLRequest.Builder builder = AuthorizationURLRequest.builder();
		builder.clientId(clientId);
		builder.responseType("code");
		builder.redirectURI(redirectURI);
		if (scopes != null) {
			builder.scopes(scopes);
		}
		if (state != null) {
			builder.state(state);
		}
		return builder.build().toStringWithQueryParameters();
	}

	/**
	 * Retrieve a URL where the user can give the application permissions.
	 * This method returns a builder instead, so that any optional parameters can be added.
	 * @param scopes The scopes corresponding to the permissions the application needs.
	 * @return A builder that when built creates a URL where the user can give the application
	 * permissions.
	 */
	public AuthorizationURLRequest.Builder createAuthorizeURL(List<String> scopes) {
		final AuthorizationURLRequest.Builder builder = AuthorizationURLRequest.builder();
		builder.clientId(clientId);
		builder.responseType("code");
		builder.redirectURI(redirectURI);
		if (scopes != null) {
			builder.scopes(scopes);
		}
		return builder;
	}

	public static class Builder {

		private String host = DEFAULT_HOST;
		private int port = DEFAULT_PORT;
		private HttpClient httpClient = Api.DEFAULT_HTTP_CLIENT;
		private String scheme = DEFAULT_SCHEME;
		private RateLimiter rateLimiter = RateLimiter.create(20);
		private String accessToken;
		private String redirectURI;
		private String clientId;
		private String clientSecret;
		private String refreshToken;
		private RocksDB cache;
		
		public Builder cache(RocksDB cache) {
			this.cache = cache;
			return this;
		}

		public Builder rateLimiter(RateLimiter rateLimiter) {
			this.rateLimiter = rateLimiter;
			return this;
		}

		public Builder scheme(String scheme) {
			this.scheme = scheme;
			return this;
		}

		public Builder host(String host) {
			this.host = host;
			return this;
		}

		public Builder port(int port) {
			this.port = port;
			return this;
		}

		public Builder httpManager(HttpClient httpClient) {
			this.httpClient = httpClient;
			return this;
		}

		public Builder accessToken(String accessToken) {
			this.accessToken = accessToken;
			return this;
		}

		public Builder refreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
			return this;
		}

		public Builder clientId(String clientId) {
			this.clientId = clientId;
			return this;
		}

		public Builder clientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
			return this;
		}

		public Builder redirectURI(String redirectURI) {
			this.redirectURI = redirectURI;
			return this;
		}

		public Api build() {
			assert (host != null);
			assert (port > 0);
			assert (scheme != null);

			return new Api(this);
		}

	}

}

