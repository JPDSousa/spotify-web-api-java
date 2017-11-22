package com.wrapper.spotify;

import static com.wrapper.spotify.methods.Request.*;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.wrapper.spotify.UtilProtos.Url.Scheme;
import com.wrapper.spotify.methods.Request;
import com.wrapper.spotify.models.FeaturedPlaylists;
import com.wrapper.spotify.models.LibraryTrack;
import com.wrapper.spotify.models.NewReleases;
import com.wrapper.spotify.models.SnapshotResult;
import com.wrapper.spotify.models.album.Album;
import com.wrapper.spotify.models.album.AlbumType;
import com.wrapper.spotify.models.album.SimpleAlbum;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.audio.AudioFeature;
import com.wrapper.spotify.models.authentication.AuthorizationCodeCredentials;
import com.wrapper.spotify.models.authentication.ClientCredentials;
import com.wrapper.spotify.models.authentication.RefreshAccessTokenCredentials;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.playlist.Playlist;
import com.wrapper.spotify.models.playlist.PlaylistTrack;
import com.wrapper.spotify.models.playlist.SimplePlaylist;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.user.User;

import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.wrapper.spotify.Assertions.*;
import static junit.framework.TestCase.assertEquals;

@SuppressWarnings("javadoc")
public class ApiTest {
	
	private static final String BASE_URL = "https://api.spotify.com:443";
	
	private static Api api;

	@Before
	public final void before() {
		api = Api.DEFAULT_API;
	}
	
	@Test
	public void shouldCreateAGetAlbumUrl() {
		final String id = "5oEljuMoe9MXH6tBIPbd5e";
		final String expected = BASE_URL + ALBUMS + "/" + id;
		final Request<Album> request = api.getAlbum(id).build();
		assertEquals(expected, request.toString());
	}

	@Test
	public void shouldCreateGetAudioFeaturesUrl(){
		final String id = "1hmNbafW4sAPNaGc7LeXAZ";
		final String expected = BASE_URL + AUDIO_FEATURES + "/" + id;
		final Request<AudioFeature> request = api.getAudioFeature(id).build();
		assertEquals(expected, request.toString());
	}

	@Test
	public void shouldCreateAGetArtistUrl() {
		final String id = "5rSXSAkZ67PYJSvpUpkOr7";
		final String expected = BASE_URL + ARTISTS + "/" + id;
		final Request<Artist> request = api.getArtist(id).build();
		assertEquals(expected, request.toString());
	}

	@Test
	public void shouldCreateAGetTrackUrl() {
		final String id = "6hDH3YWFdcUNQjubYztIsG";
		final Request<Track> request = api.getTrack(id).build();
		assertEquals("https://api.spotify.com:443/v1/tracks/6hDH3YWFdcUNQjubYztIsG", request.toString());
	}

	@Test
	public void shouldCreateAGetAlbumsUrl() {
		final String[] ids = {"6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv"};
		final Request<List<Album>> request = api.getAlbums(ids).build();
		assertEquals("https://api.spotify.com:443/v1/albums", request.toString());
		assertHasParameter(request.toUrl(), "ids", String.join(",", ids));
	}

	@Test
	public void shouldCreateAGetAlbumsUrlFromAList() {
		final List<String> ids = Arrays.asList("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv");
		final Request<List<Album>> request = api.getAlbums(ids).build();
		assertEquals("https://api.spotify.com:443/v1/albums", request.toString());
		assertHasParameter(request.toUrl(), "ids", String.join(",", ids));
	}

	@Test
	public void shouldCreateAGetTracksUrl() {
		final String[] ids = {"6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv"};
		final Request<List<Track>> request = api.getTracks(ids).build();
		assertEquals("https://api.spotify.com:443/v1/tracks", request.toString());
		assertHasParameter(request.toUrl(), "ids", String.join(",", ids));
	}

	@Test
	public void shouldCreateAGetTracksUrlFromList() {
		final List<String> ids = Arrays.asList("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv");
		final Request<List<Track>> request = api.getTracks(ids).build();
		assertEquals("https://api.spotify.com:443/v1/tracks", request.toString());
		assertHasParameter(request.toUrl(), "ids", String.join(",", ids));
	}

	@Test
	public void shouldCreateAUrlForArtistsAlbum() {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
	}

	@Test
	public void shouldHaveMultipleAlbumTypeParametersInArtistsAlbumUrl() {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq")
				.types(AlbumType.ALBUM, AlbumType.SINGLE)
				.market(CountryCode.SE)
				.build();

		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
		assertHasParameter(request.toUrl(), "album_type", "ALBUM,SINGLE");
		assertHasParameter(request.toUrl(), "market", "SE");
	}

	@Test
	public void shouldHaveSingleAlbumTypeParametersInArtistsAlbumUrl() {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(AlbumType.SINGLE).build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
		assertHasParameter(request.toUrl(), "album_type", "SINGLE");
	}

	@Test
	public void shouldFailIfAlbumTypeParametersIsInArtistsAlbumUrl() {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(AlbumType.SINGLE).build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
		assertHasParameter(request.toUrl(), "album_type", "SINGLE");
	}

	@Test
	public void shouldHaveLimitParameterInArtistsAlbumUrl() {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").limit(2).build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
		assertHasParameter(request.toUrl(), "limit", "2");
	}

	@Test
	public void shouldHaveOffsetParameterInArtistsAlbumUrl() {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").offset(5).build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
		assertHasParameter(request.toUrl(), "offset", "5");
	}

	@Test
	public void shouldHaveSeveralQueryParametersAtTheSameTimeInArtistsAlbumUrl() {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(AlbumType.SINGLE).limit(2).offset(5).build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
		assertHasParameter(request.toUrl(), "offset", "5");
		assertHasParameter(request.toUrl(), "limit", "2");
		assertHasParameter(request.toUrl(), "album_type", "SINGLE");
	}

	@Test
	public void shouldCreateAGetArtistsUrl() {
		final Request<List<Artist>> request = api.getArtists("4AK6F7OLvEQ5QYCBNiQWHq", "6rEzedK7cKWjeQWdAYvWVG").build();
		assertEquals("https://api.spotify.com:443/v1/artists", request.toString());
		assertHasParameter(request.toUrl(), "ids", "4AK6F7OLvEQ5QYCBNiQWHq,6rEzedK7cKWjeQWdAYvWVG");
	}

	@Test
	public void shouldCreateSearchUrl() {
		final Request<Page<Track>> request = api.searchTracks("moulat swalf").build();
		assertEquals("https://api.spotify.com:443/v1/search", request.toString());
		assertHasParameter(request.toUrl(), "q", "moulat swalf");
		assertHasParameter(request.toUrl(), "type", "track");
	}

	@Test
	public void shouldCreateSearchUrlForAlbum() {
		final Request<Page<SimpleAlbum>> request = api.searchAlbums("meeep").marketFromToken().build();
		assertEquals("https://api.spotify.com:443/v1/search", request.toString());
		assertHasParameter(request.toUrl(), "q", "meeep");
		assertHasParameter(request.toUrl(), "type", "album");
		assertHasParameter(request.toUrl(), "market", "from_token");
	}

	@Test
	public void shouldCreateSearchUrlForArtist() {
		final Request<Page<Artist>> request = api.searchArtists("meeep").market(CountryCode.GB).build();
		assertEquals("https://api.spotify.com:443/v1/search", request.toString());
		assertHasParameter(request.toUrl(), "q", "meeep");
		assertHasParameter(request.toUrl(), "type", "artist");
		assertHasParameter(request.toUrl(), "market", "GB");
	}

	@Test
	public void shouldCreateSearchUrlWithLimitParameter() {
		final Request<Page<Track>> request = api.searchTracks("moulat swalf").limit(2).market(CountryCode.SE).build();
		assertEquals("https://api.spotify.com:443/v1/search", request.toString());
		assertHasParameter(request.toUrl(), "q", "moulat swalf");
		assertHasParameter(request.toUrl(), "limit", "2");
		assertHasParameter(request.toUrl(), "type", "track");
		assertHasParameter(request.toUrl(), "market", "SE");
	}

	@Test
	public void shouldCreateSearchUrlWithOffsetParameter() {
		final Request<Page<Track>> request = api.searchTracks("moulat swalf").offset(2).build();
		assertEquals("https://api.spotify.com:443/v1/search", request.toString());
		assertHasParameter(request.toUrl(), "q", "moulat swalf");
		assertHasParameter(request.toUrl(), "offset", "2");
		assertHasParameter(request.toUrl(), "type", "track");
	}

	@Test
	public void shouldModifySchemeInUrl() {
		final Api api = Api.builder().scheme(Scheme.HTTP).build();
		final Request<Album> request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
		assertEquals("http://api.spotify.com:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
	}

	@Test
	public void shouldModifyPortInUrl() {
		final Api api = Api.builder().port(8080).build();
		final Request<Album> request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
		assertEquals("https://api.spotify.com:8080/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
	}

	@Test
	public void shouldModifyHostInUrl() {
		Api api = Api.builder().host("www.wrapper.se").build();
		final Request<Album> request = api.getAlbum("5oEljuMoe9MXH6tBIPbd5e").build();
		assertEquals("https://www.wrapper.se:443/v1/albums/5oEljuMoe9MXH6tBIPbd5e", request.toString());
	}

	@Test
	public void shouldCreateTopTracksUrl() {
		final Request<List<Track>> request = api.getTopTracksForArtist("0LcJLqbBmaGUft1e9Mm8HV", CountryCode.GB).build();
		assertEquals("https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/toptracks", request.toString());
		assertHasParameter(request.toUrl(), "country", "GB");
	}

	@Test
	public void shouldCreateUserProfileUrl() {
		Api api = Api.DEFAULT_API;
		final Request<User> request = api.getUser("wizzler").build();
		assertEquals("https://api.spotify.com:443/v1/users/wizzler", request.toString());
	}

	@Test
	public void shouldCreateUrlForListingAUsersPlaylists() {
		final String accessToken = "myVeryLongAccessToken";
		final Api api = Api.builder().accessToken(accessToken).build();
		final Request<Page<SimplePlaylist>> request = api.getPlaylistsForUser("wizzler").build();

		assertEquals("https://api.spotify.com:443/v1/users/wizzler/playlists", request.toString());
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateRequestForTokensUrl() {
		final String clientId = "myClientId";
		final String clientSecret = "myClientSecret";
		final String redirectURI = "myRedirectUri";
		final String code = "returnedCode";

		final Api api = Api.builder()
				.clientId(clientId)
				.clientSecret(clientSecret)
				.redirectURI(redirectURI)
				.build();

		final Request<AuthorizationCodeCredentials> request = api.authorizationCodeGrant(code).build();

		assertEquals("https://accounts.spotify.com:443/api/token", request.toString());
		assertHasBodyParameter(request.toUrl(), "grant_type", "authorization_code");
		assertHasBodyParameter(request.toUrl(), "code", code);
		assertHasBodyParameter(request.toUrl(), "redirect_uri", redirectURI);

		final String idSecret = clientId + ":" + clientSecret;
		assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
	}

	@Test
	public void shouldCreateRefreshAccessTokenUrl() {
		final String clientId = "myClientId";
		final String clientSecret = "myClientSecret";
		final String refreshToken = "myRefreshToken";

		final Api api = Api
				.builder()
				.clientId(clientId)
				.clientSecret(clientSecret)
				.refreshToken(refreshToken)
				.build();

		final Request<RefreshAccessTokenCredentials> request = api.refreshAccessToken().build();

		assertEquals("https://accounts.spotify.com:443/api/token", request.toString());
		assertHasBodyParameter(request.toUrl(), "grant_type", "refresh_token");
		assertHasBodyParameter(request.toUrl(), "refresh_token", refreshToken);

		final String idSecret = clientId + ":" + clientSecret;
		assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
	}

	@Test
	public void shouldCreatePlaylistLookupUrl() {
		final String accessToken = "myVeryLongAccessToken";
		final Api api = Api.builder().accessToken(accessToken).build();

		final String playlistId = "3ktAYNcRHpazJ9qecm3ptn";
		final String userId = "thelinmichael";

		final Request<Playlist> request = api.getPlaylist(userId, playlistId).build();

		assertEquals("https://api.spotify.com:443/v1/users/" + userId + "/playlists/" + playlistId, request.toString());
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateCurrentUserLookupUrl() {
		final String accessToken = "myVeryLongAccessToken";

		final Api api = Api.builder().accessToken(accessToken).build();

		final Request<User> request = api.getMe().build();

		assertEquals("https://api.spotify.com:443/v1/me", request.toString());
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateCreatePlaylistUrl() {
		final String accessToken = "myVeryLongAccessToken";
		final Api api = Api.builder().accessToken(accessToken).build();

		final String myUsername = "thelinmichael";
		final String title = "The greatest playlist ever";
		final boolean publicAccess = true;

		final Request<Playlist> request = api.createPlaylist(myUsername, title).publicAccess(publicAccess).build();

		assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists", request.toString());
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
		assertHasHeader(request.toUrl(), "Content-Type", "application/json");
		assertHasJsonBody(request.toUrl(), "{\"name\":\"The greatest playlist ever\",\"public\":\"true\"}");
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateAddTrackToPlaylistUrl() {
		final String accessToken = "myVeryLongAccessToken";
		final Api api = Api.builder().accessToken(accessToken).build();

		final String myUsername = "thelinmichael";
		final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
		final List<String> tracksToAdd = Arrays.asList("spotify:track:4BYGxv4rxSNcTgT3DsFB9o","spotify:tracks:0BG2iE6McPhmAEKIhfqy1X");
		final int insertIndex = 3;

		final Request<SnapshotResult> request = api.addTracksToPlaylist(myUsername, myPlaylistId, tracksToAdd).position(insertIndex).build();

		assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId + "/tracks", request.toString());
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
		assertHasHeader(request.toUrl(), "Content-Type", "application/json");
		assertHasJsonBody(request.toUrl(), "[\"spotify:track:4BYGxv4rxSNcTgT3DsFB9o\",\"spotify:tracks:0BG2iE6McPhmAEKIhfqy1X\"]");
		assertHasParameter(request.toUrl(), "position", String.valueOf(insertIndex));
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateChangePlaylistDetailsUrl() {
		final String accessToken = "myVeryLongAccessToken";
		final Api api = Api.builder().accessToken(accessToken).build();

		final String myUsername = "thelinmichael";
		final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";

		final boolean isPublic = false;
		final String name = "Testing name change";

		final Request<String> request = api.changePlaylistDetails(myUsername, myPlaylistId)
				.publicAccess(isPublic)
				.name(name)
				.build();

		assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId,
				request.toString());
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
		assertHasHeader(request.toUrl(), "Content-Type", "application/json");

		JSONObject jsonBody = JSONObject.fromObject(request.toUrl().getJsonBody());
		assertEquals(name, jsonBody.getString("name"));
		assertEquals(isPublic, jsonBody.getBoolean("public"));

		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateClientCredentialsGrantUrl() {
		final String clientId = "myClientId";
		final String clientSecret = "myClientSecret";

		final Api api = Api.builder()
				.clientId(clientId)
				.clientSecret(clientSecret)
				.build();

		final List<String> scopes = Arrays.asList("some-scope", "some-other-scope");

		final Request<ClientCredentials> request = api.clientCredentialsGrant().scopes(scopes).build();

		assertEquals("https://accounts.spotify.com:443/api/token", request.toString());

		assertHasBodyParameter(request.toUrl(), "grant_type", "client_credentials");
		assertHasBodyParameter(request.toUrl(), "scope", "some-scope some-other-scope");

		final String idSecret = clientId + ":" + clientSecret;
		assertHasHeader(request.toUrl(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
	}

	@Test
	public void shouldCreateAGetPlaylistTracksURL() {
		final String accessToken = "myAccessToken";
		final String userId = "thelinmichael";
		final String playlistId = "5ieJqeLJjjI8iJWaxeBLuK";

		final Api api = Api.builder()
				.accessToken(accessToken)
				.build();

		final Request<Page<PlaylistTrack>> request = api
				.getPlaylistTracks(userId, playlistId)
				.fields("items")
				.limit(20)
				.offset(1)
				.build();

		assertEquals("https://api.spotify.com:443/v1/users/" + userId + "/playlists/" + playlistId + "/tracks", request.toString());
		assertHasParameter(request.toUrl(), "fields", "items");
		assertHasParameter(request.toUrl(), "limit", "20");
		assertHasParameter(request.toUrl(), "offset", "1");
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateRelatedArtistsURL() {
		final Api api = Api.DEFAULT_API;

		final String artistId = "0qeei9KQnptjwb8MgkqEoy";

		final Request<List<Artist>> request = api.getArtistRelatedArtists(artistId).build();

		assertEquals("https://api.spotify.com:443/v1/artists/" + artistId + "/related-artists", request.toString());
	}

	@Test
	public void shouldCreateAuthorizeURL() {
		final String redirectURI = "http://www.michaelthelin.se/test-callback";
		final String clientId = "fcecfc79122e4cd299473677a17cbd4d";

		final Api api = Api.builder()
				.clientId(clientId)
				.redirectURI(redirectURI)
				.build();

		final List<String> scopes = Arrays.asList("user-read-private", "user-read-email");
		final String state = "someExpectedStateString";

		String authorizeURL = api.createAuthorizeURL(scopes, state);
		assertEquals("https://accounts.spotify.com:443/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code&redirect_uri=http://www.michaelthelin.se/test-callback&scope=user-read-private%20user-read-email&state=someExpectedStateString", authorizeURL);
	}

	@Test
	public void shouldCreateAuthorizeUrlWithOptionalParameters() {
		final String redirectURI = "http://www.michaelthelin.se/test-callback";
		final String clientId = "fcecfc79122e4cd299473677a17cbd4d";

		final Api api = Api.builder()
				.clientId(clientId)
				.redirectURI(redirectURI)
				.build();

		final List<String> scopes = Arrays.asList("user-read-private", "user-read-email");
		final String state = "someExpectedStateString";

		String authorizeURL = api.createAuthorizeURL(scopes).
				state(state).
				showDialog(false).
				build().
				toStringWithQueryParameters();

		assertEquals("https://accounts.spotify.com:443/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code&redirect_uri=http://www.michaelthelin.se/test-callback&scope=user-read-private%20user-read-email&state=someExpectedStateString&show_dialog=false", authorizeURL);
	}

	@Test
	public void shouldCreateGetMyTracksURL() {
		final String accessToken = "myAccessToken";

		final Api api = Api.builder()
				.accessToken(accessToken)
				.build();

		final Request<Page<LibraryTrack>> request = api
				.getMySavedTracks()
				.limit(5)
				.offset(1)
				.build();

		assertEquals("https://api.spotify.com:443/v1/me/tracks", request.toString());
		assertHasParameter(request.toUrl(), "limit", "5");
		assertHasParameter(request.toUrl(), "offset", "1");
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreatePutTracksURL() {
		final String accessToken = "myAccessToken";

		final Api api = Api.builder()
				.accessToken(accessToken)
				.build();

		final Request<String> request = api
				.addToMySavedTracks(Arrays.asList("test", "test2"))
				.build();

		assertEquals("https://api.spotify.com:443/v1/me/tracks", request.toString());
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateRemoveTracksURL() {
		final String accessToken = "myAccessToken";

		final Api api = Api.builder()
				.accessToken(accessToken)
				.build();

		final Request<String> request = api
				.removeFromMySavedTracks(Arrays.asList("test", "test2"))
				.build();

		assertEquals("https://api.spotify.com:443/v1/me/tracks", request.toString());
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateGetNewReleasesRequest() {
		final String accessToken = "myAccessToken";

		final Api api = Api.builder()
				.accessToken(accessToken)
				.build();

		final Request<NewReleases> request = api.getNewReleases()
				.limit(4)
				.offset(1)
				.country(CountryCode.SE)
				.build();

		assertEquals("https://api.spotify.com:443/v1/browse/new-releases", request.toString());
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
		assertHasParameter(request.toUrl(), "limit", "4");
		assertHasParameter(request.toUrl(), "offset", "1");
		assertHasParameter(request.toUrl(), "country", "SE");
	}

	@Test
	public void shouldCreateFeaturedPlaylistsRequest() {
		final String accessToken = "myAccessToken";

		final Api api = Api.builder()
				.accessToken(accessToken)
				.build();


		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, 11, 22, 13, 59, 30);
		Date timestamp = calendar.getTime();

		final Request<FeaturedPlaylists> request = api
				.getFeaturedPlaylists()
				.country(CountryCode.SE)
				.locale(LanguageCode.es, CountryCode.MX)
				.limit(5)
				.offset(1)
				.timestamp(timestamp)
				.build();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		assertEquals("https://api.spotify.com:443/v1/browse/featured-playlists", request.toString());
		assertHasHeader(request.toUrl(), "Authorization", "Bearer " + accessToken);
		assertHasParameter(request.toUrl(), "limit", "5");
		assertHasParameter(request.toUrl(), "offset", "1");
		assertHasParameter(request.toUrl(), "country", "SE");
		assertHasParameter(request.toUrl(), "locale", "es_MX");
		assertHasParameter(request.toUrl(), "timestamp", format.format(timestamp));
	}

}
