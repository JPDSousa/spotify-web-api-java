package com.wrapper.spotify;

import static com.wrapper.spotify.methods.Request.*;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
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
	private static final String BASE_URL_HTTP = "http://api.spotify.com:443"; 

	private static Api api;
	private static DB cache;

	@BeforeClass
	public static final void beforeClass() {
		cache = DBMaker
				.fileDB(Paths.get("cache").toString())
				.fileMmapEnable()
				.make();
	}

	@AfterClass
	public static final void afterClass() {
		cache.close();
	}

	@Before
	public final void before() {
		api = Api.builder().cache(cache).build();
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
	public void shouldCreateAGetAlbumsUrl() throws UnsupportedEncodingException {
		final String[] ids = {"6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv"};
		final Request<List<Album>> request = api.getAlbums(ids).build();
		assertEquals("https://api.spotify.com:443/v1/albums?ids=6hDH3YWFdcUNQjubYztIsG%2C2IA4WEsWAYpV9eKkwR2UYv", request.toString());
		assertHasParameter(request.toUrl(), "ids", String.join(",", ids));
	}

	@Test
	public void shouldCreateAGetAlbumsUrlFromAList() throws UnsupportedEncodingException {
		final List<String> ids = Arrays.asList("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv");
		final Request<List<Album>> request = api.getAlbums(ids).build();
		assertEquals("https://api.spotify.com:443/v1/albums?ids=6hDH3YWFdcUNQjubYztIsG%2C2IA4WEsWAYpV9eKkwR2UYv", request.toString());
		assertHasParameter(request.toUrl(), "ids", String.join(",", ids));
	}

	@Test
	public void shouldCreateAGetTracksUrl() throws UnsupportedEncodingException {
		final String[] ids = {"6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv"};
		final Request<List<Track>> request = api.getTracks(ids).build();
		assertEquals("https://api.spotify.com:443/v1/tracks?ids=6hDH3YWFdcUNQjubYztIsG%2C2IA4WEsWAYpV9eKkwR2UYv", request.toString());
		assertHasParameter(request.toUrl(), "ids", String.join(",", ids));
	}

	@Test
	public void shouldCreateAGetTracksUrlFromList() throws UnsupportedEncodingException {
		final List<String> ids = Arrays.asList("6hDH3YWFdcUNQjubYztIsG", "2IA4WEsWAYpV9eKkwR2UYv");
		final Request<List<Track>> request = api.getTracks(ids).build();
		assertEquals("https://api.spotify.com:443/v1/tracks?ids=6hDH3YWFdcUNQjubYztIsG%2C2IA4WEsWAYpV9eKkwR2UYv", request.toString());
		assertHasParameter(request.toUrl(), "ids", String.join(",", ids));
	}

	@Test
	public void shouldCreateAUrlForArtistsAlbum() {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums", request.toString());
	}

	@Test
	public void shouldHaveMultipleAlbumTypeParametersInArtistsAlbumUrl() throws UnsupportedEncodingException {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq")
				.types(AlbumType.ALBUM, AlbumType.SINGLE)
				.market(CountryCode.SE)
				.build();

		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?album_type=ALBUM%2CSINGLE&market=SE", request.toString());
		assertHasParameter(request.toUrl(), "album_type", "ALBUM,SINGLE");
		assertHasParameter(request.toUrl(), "market", "SE");
	}

	@Test
	public void shouldHaveSingleAlbumTypeParametersInArtistsAlbumUrl() throws UnsupportedEncodingException {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(AlbumType.SINGLE).build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?album_type=SINGLE", request.toString());
		assertHasParameter(request.toUrl(), "album_type", "SINGLE");
	}

	@Test
	public void shouldFailIfAlbumTypeParametersIsInArtistsAlbumUrl() throws UnsupportedEncodingException {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(AlbumType.SINGLE).build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?album_type=SINGLE", request.toString());
		assertHasParameter(request.toUrl(), "album_type", "SINGLE");
	}

	@Test
	public void shouldHaveLimitParameterInArtistsAlbumUrl() throws UnsupportedEncodingException {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").limit(2).build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?limit=2", request.toString());
		assertHasParameter(request.toUrl(), "limit", "2");
	}

	@Test
	public void shouldHaveOffsetParameterInArtistsAlbumUrl() throws UnsupportedEncodingException {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").offset(5).build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?offset=5", request.toString());
		assertHasParameter(request.toUrl(), "offset", "5");
	}

	@Test
	public void shouldHaveSeveralQueryParametersAtTheSameTimeInArtistsAlbumUrl() throws UnsupportedEncodingException {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("4AK6F7OLvEQ5QYCBNiQWHq").types(AlbumType.SINGLE).limit(2).offset(5).build();
		assertEquals("https://api.spotify.com:443/v1/artists/4AK6F7OLvEQ5QYCBNiQWHq/albums?album_type=SINGLE&limit=2&offset=5", request.toString());
		assertHasParameter(request.toUrl(), "offset", "5");
		assertHasParameter(request.toUrl(), "limit", "2");
		assertHasParameter(request.toUrl(), "album_type", "SINGLE");
	}

	@Test
	public void shouldCreateAGetArtistsUrl() throws UnsupportedEncodingException {
		final Request<List<Artist>> request = api.getArtists("4AK6F7OLvEQ5QYCBNiQWHq", "6rEzedK7cKWjeQWdAYvWVG").build();
		assertEquals("https://api.spotify.com:443/v1/artists?ids=4AK6F7OLvEQ5QYCBNiQWHq%2C6rEzedK7cKWjeQWdAYvWVG", request.toString());
		assertHasParameter(request.toUrl(), "ids", "4AK6F7OLvEQ5QYCBNiQWHq,6rEzedK7cKWjeQWdAYvWVG");
	}

	@Test
	public void shouldCreateSearchUrl() throws UnsupportedEncodingException {
		final Request<Page<Track>> request = api.searchTracks("moulat swalf").build();
		assertEquals("https://api.spotify.com:443/v1/search?type=track&q=moulat+swalf", request.toString());
		assertHasParameter(request.toUrl(), "q", "moulat swalf");
		assertHasParameter(request.toUrl(), "type", "track");
	}

	@Test
	public void shouldCreateSearchUrlForAlbum() throws UnsupportedEncodingException {
		final Request<Page<SimpleAlbum>> request = api.searchAlbums("meeep").marketFromToken().build();
		assertEquals("https://api.spotify.com:443/v1/search?type=album&q=meeep&market=from_token", request.toString());
		assertHasParameter(request.toUrl(), "q", "meeep");
		assertHasParameter(request.toUrl(), "type", "album");
		assertHasParameter(request.toUrl(), "market", "from_token");
	}

	@Test
	public void shouldCreateSearchUrlForArtist() throws UnsupportedEncodingException {
		final Request<Page<Artist>> request = api.searchArtists("meeep").market(CountryCode.GB).build();
		assertEquals("https://api.spotify.com:443/v1/search?type=artist&q=meeep&market=GB", request.toString());
		assertHasParameter(request.toUrl(), "q", "meeep");
		assertHasParameter(request.toUrl(), "type", "artist");
		assertHasParameter(request.toUrl(), "market", "GB");
	}

	@Test
	public void shouldCreateSearchUrlWithLimitParameter() throws UnsupportedEncodingException {
		final Request<Page<Track>> request = api.searchTracks("moulat swalf").limit(2).market(CountryCode.SE).build();
		assertEquals("https://api.spotify.com:443/v1/search?type=track&q=moulat+swalf&limit=2&market=SE", request.toString());
		assertHasParameter(request.toUrl(), "q", "moulat swalf");
		assertHasParameter(request.toUrl(), "limit", "2");
		assertHasParameter(request.toUrl(), "type", "track");
		assertHasParameter(request.toUrl(), "market", "SE");
	}

	@Test
	public void shouldCreateSearchUrlWithOffsetParameter() throws UnsupportedEncodingException {
		final Request<Page<Track>> request = api.searchTracks("moulat swalf").offset(2).build();
		assertEquals("https://api.spotify.com:443/v1/search?type=track&q=moulat+swalf&offset=2", request.toString());
		assertHasParameter(request.toUrl(), "q", "moulat swalf");
		assertHasParameter(request.toUrl(), "offset", "2");
		assertHasParameter(request.toUrl(), "type", "track");
	}

	@Test
	public void shouldModifySchemeInUrl() {
		final Api api = Api.builder().scheme("http").build();
		final String id = "5oEljuMoe9MXH6tBIPbd5e";
		final Request<Album> request = api.getAlbum(id).build();
		final String expected = BASE_URL_HTTP + Request.ALBUMS + "/" + id;
		assertEquals(expected, request.toString());
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
	public void shouldCreateTopTracksUrl() throws UnsupportedEncodingException {
		final Request<List<Track>> request = api.getTopTracksForArtist("0LcJLqbBmaGUft1e9Mm8HV", CountryCode.GB).build();
		assertEquals("https://api.spotify.com:443/v1/artists/0LcJLqbBmaGUft1e9Mm8HV/toptracks?country=GB", request.toString());
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
		final String userId = "wizzler";
		final Request<Page<SimplePlaylist>> request = api.getPlaylistsForUser(userId).build();

		assertEquals(BASE_URL + "/v1/users/wizzler/playlists", request.toString());
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
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
		assertHasBodyParameter(request.getBody(), "grant_type", "authorization_code");
		assertHasBodyParameter(request.getBody(), "code", code);
		assertHasBodyParameter(request.getBody(), "redirect_uri", redirectURI);

		final String idSecret = clientId + ":" + clientSecret;
		assertHasHeader(request.getHeader(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
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
		assertHasBodyParameter(request.getBody(), "grant_type", "refresh_token");
		assertHasBodyParameter(request.getBody(), "refresh_token", refreshToken);

		final String idSecret = clientId + ":" + clientSecret;
		assertHasHeader(request.getHeader(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
	}

	@Test
	public void shouldCreatePlaylistLookupUrl() {
		final String accessToken = "myVeryLongAccessToken";
		final Api api = Api.builder().accessToken(accessToken).build();

		final String playlistId = "3ktAYNcRHpazJ9qecm3ptn";
		final String userId = "thelinmichael";

		final Request<Playlist> request = api.getPlaylist(userId, playlistId).build();

		assertEquals("https://api.spotify.com:443/v1/users/" + userId + "/playlists/" + playlistId, request.toString());
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateCurrentUserLookupUrl() {
		final String accessToken = "myVeryLongAccessToken";

		final Api api = Api.builder().accessToken(accessToken).build();

		final Request<User> request = api.getMe().build();

		assertEquals("https://api.spotify.com:443/v1/me", request.toString());
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
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
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
		assertHasHeader(request.getHeader(), "Content-Type", "application/json");
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateAddTrackToPlaylistUrl() throws UnsupportedEncodingException {
		final String accessToken = "myVeryLongAccessToken";
		final Api api = Api.builder().accessToken(accessToken).build();

		final String myUsername = "thelinmichael";
		final String myPlaylistId = "5ieJqeLJjjI8iJWaxeBLuK";
		final List<String> tracksToAdd = Arrays.asList("spotify:track:4BYGxv4rxSNcTgT3DsFB9o","spotify:tracks:0BG2iE6McPhmAEKIhfqy1X");
		final int insertIndex = 3;

		final Request<SnapshotResult> request = api.addTracksToPlaylist(myUsername, myPlaylistId, tracksToAdd).position(insertIndex).build();

		assertEquals("https://api.spotify.com:443/v1/users/thelinmichael/playlists/" + myPlaylistId + "/tracks?position=3", request.toString());
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
		assertHasHeader(request.getHeader(), "Content-Type", "application/json");
		assertEquals(request.getBody(), "[\"spotify:track:4BYGxv4rxSNcTgT3DsFB9o\",\"spotify:tracks:0BG2iE6McPhmAEKIhfqy1X\"]");
		assertHasParameter(request.toUrl(), "position", String.valueOf(insertIndex));
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
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
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
		assertHasHeader(request.getHeader(), "Content-Type", "application/json");

		JSONObject jsonBody = JSONObject.fromObject(request.getBody());
		assertEquals(name, jsonBody.getString("name"));
		assertEquals(isPublic, jsonBody.getBoolean("public"));

		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
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

		assertHasBodyParameter(request.getBody(), "grant_type", "client_credentials");
		assertHasBodyParameter(request.getBody(), "scope", "some-scope some-other-scope");

		final String idSecret = clientId + ":" + clientSecret;
		assertHasHeader(request.getHeader(), "Authorization", "Basic " + new String(Base64.encodeBase64(idSecret.getBytes())));
	}

	@Test
	public void shouldCreateAGetPlaylistTracksURL() throws UnsupportedEncodingException {
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

		final String expected = "https://api.spotify.com:443/v1/users/" 
				+ userId 
				+ "/playlists/" 
				+ playlistId 
				+ "/tracks?fields=items&limit=20&offset=1";
		assertEquals(expected, request.toString());
		assertHasParameter(request.toUrl(), "fields", "items");
		assertHasParameter(request.toUrl(), "limit", "20");
		assertHasParameter(request.toUrl(), "offset", "1");
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
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
		final String expected = "https://accounts.spotify.com:443"
				+ "/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code"
				+ "&redirect_uri=http%3A%2F%2Fwww.michaelthelin.se%2Ftest-callback&scope=user-read-private+user-read-email&state=someExpectedStateString";
		assertEquals(expected, authorizeURL);
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

		String authorizeURL = api.createAuthorizeURL(scopes)
				.state(state)
				.showDialog(false)
				.build()
				.toString();

		assertEquals("https://accounts.spotify.com:443/authorize?client_id=fcecfc79122e4cd299473677a17cbd4d&response_type=code&redirect_uri=http%3A%2F%2Fwww.michaelthelin.se%2Ftest-callback&scope=user-read-private+user-read-email&state=someExpectedStateString&show_dialog=false", authorizeURL);
	}

	@Test
	public void shouldCreateGetMyTracksURL() throws UnsupportedEncodingException {
		final String accessToken = "myAccessToken";

		final Api api = Api.builder()
				.accessToken(accessToken)
				.build();

		final Request<Page<LibraryTrack>> request = api
				.getMySavedTracks()
				.limit(5)
				.offset(1)
				.build();

		assertEquals("https://api.spotify.com:443/v1/me/tracks?limit=5&offset=1", request.toString());
		assertHasParameter(request.toUrl(), "limit", "5");
		assertHasParameter(request.toUrl(), "offset", "1");
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
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
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
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

		assertEquals("https://api.spotify.com:443/v1/me/tracks?ids=test%2Ctest2", request.toString());
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
	}

	@Test
	public void shouldCreateGetNewReleasesRequest() throws UnsupportedEncodingException {
		final String accessToken = "myAccessToken";

		final Api api = Api.builder()
				.accessToken(accessToken)
				.build();

		final Request<NewReleases> request = api.getNewReleases()
				.limit(4)
				.offset(1)
				.country(CountryCode.SE)
				.build();

		assertEquals("https://api.spotify.com:443/v1/browse/new-releases?limit=4&offset=1&country=SE", request.toString());
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
		assertHasParameter(request.toUrl(), "limit", "4");
		assertHasParameter(request.toUrl(), "offset", "1");
		assertHasParameter(request.toUrl(), "country", "SE");
	}

	@Test
	public void shouldCreateFeaturedPlaylistsRequest() throws UnsupportedEncodingException {
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

		assertEquals("https://api.spotify.com:443/v1/browse/featured-playlists?country=SE&locale=es_MX&limit=5&offset=1&timestamp=2014-12-22T13%3A59%3A30", request.toString());
		assertHasHeader(request.getHeader(), "Authorization", "Bearer " + accessToken);
		assertHasParameter(request.toUrl(), "limit", "5");
		assertHasParameter(request.toUrl(), "offset", "1");
		assertHasParameter(request.toUrl(), "country", "SE");
		assertHasParameter(request.toUrl(), "locale", "es_MX");
		assertHasParameter(request.toUrl(), "timestamp", format.format(timestamp));
	}

}
