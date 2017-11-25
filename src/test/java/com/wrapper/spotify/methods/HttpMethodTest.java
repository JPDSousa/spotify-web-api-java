package com.wrapper.spotify.methods;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.models.FeaturedPlaylists;
import com.wrapper.spotify.models.LibraryTrack;
import com.wrapper.spotify.models.NewReleases;
import com.wrapper.spotify.models.Product;
import com.wrapper.spotify.models.SpotifyEntityType;
import com.wrapper.spotify.models.album.Album;
import com.wrapper.spotify.models.album.AlbumType;
import com.wrapper.spotify.models.album.SimpleAlbum;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.artist.SimpleArtist;
import com.wrapper.spotify.models.page.Page;
import com.wrapper.spotify.models.playlist.Playlist;
import com.wrapper.spotify.models.playlist.PlaylistTrack;
import com.wrapper.spotify.models.playlist.SimplePlaylist;
import com.wrapper.spotify.models.track.SimpleTrack;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.user.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SuppressWarnings("javadoc")
public class HttpMethodTest {

	private Api api;

	@Before
	public void before() {
		api = Api.DEFAULT_API;
	}

	@Test
	public void shouldGetUser() throws Exception {
		final Request<User> request = api.getUser("wizzler")
				.httpManager(TestUtil.MockedHttpManager.returningJson("user.json"))
				.build();
		final User user = request.exec();
		assertNull(user.getEmail());
		assertEquals("wizzler", user.getId());
		assertEquals("https://open.spotify.com/user/wizzler", user.getExternalUrls().get("spotify"));
		assertNotNull(user.getFollowers());
		assertNotNull(user.getImages());
		assertTrue(user.getFollowers().getTotal() > 0);
		assertEquals("https://api.spotify.com/v1/users/wizzler", user.getHref());
	}

	@Test
	public void shouldGetPlaylistsForUser() throws Exception {
		final String accessToken = "myVeryLongAccessToken";
		final Request<Page<SimplePlaylist>> request = api
				.getPlaylistsForUser("wizzler")
				.accessToken(accessToken)
				.httpManager(TestUtil.MockedHttpManager.returningJson("user-playlists.json"))
				.build();
		final Page<SimplePlaylist> playlistsPage = request.exec();
		assertTrue(playlistsPage.getTotal() >= 0);
		assertNull(playlistsPage.getNext());
		assertEquals("https://api.spotify.com/v1/users/wizzler/playlists?offset=0&limit=10", playlistsPage.getPrevious());
		assertEquals(10, playlistsPage.getLimit());
		assertEquals(2, playlistsPage.getOffset());
		assertEquals("https://api.spotify.com/v1/users/wizzler/playlists?offset=2&limit=10", playlistsPage.getHref());
		final SimplePlaylist simplePlaylist = playlistsPage.getItems().get(0);
		final String playlistId = simplePlaylist.getId();
		assertNotNull(playlistId);
		assertTrue(playlistId.length() > 0);
		assertEquals(false, simplePlaylist.isCollaborative());
		assertEquals("http://open.spotify.com/user/wizzler/playlist/" + playlistId, simplePlaylist.getExternalUrls().get("spotify"));
		assertNotNull(simplePlaylist.getName());
		assertNotNull(simplePlaylist.getOwner());
		assertNotNull(simplePlaylist.isPublicAccess());
		assertNotNull(simplePlaylist.getTracks().getHref());
		assertNotNull(simplePlaylist.getTracks().getTotal());
		assertEquals(SpotifyEntityType.PLAYLIST, simplePlaylist.getType());
		assertEquals("spotify:user:wizzler:playlist:" + playlistId, simplePlaylist.getUri());
		assertEquals(1, simplePlaylist.getImages().size());
		assertEquals("https://i.scdn.co/image/418ce596327dc3a0f4d377db80421bffb3b94a9a", simplePlaylist.getImages().get(0).getUrl());
		assertNull(simplePlaylist.getImages().get(0).getWidth());
		assertNull(simplePlaylist.getImages().get(0).getHeight());
	}

	@Test
	public void shouldGetTracksResult() throws Exception {
		final Request<List<Track>> request = api.getTracks("0eGsygTp906u18L0Oimnem", "1lDWb6b6ieDQ2xT7ewTC3G")
				.httpManager(TestUtil.MockedHttpManager.returningJson("tracks.json"))
				.build();
		final List<Track> tracks = request.exec();
		assertEquals(2, tracks.size());
		final Track firstTrack = tracks.get(0);
		assertEquals("0eGsygTp906u18L0Oimnem", firstTrack.getId());
		final Track secondTrack = tracks.get(1);
		assertEquals("1lDWb6b6ieDQ2xT7ewTC3G", secondTrack.getId());
	}

	@Test
	public void shouldSearchTracksResult() throws Exception {
		final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("search-track.json");
		final Request<Page<Track>> request = api.searchTracks("Mr. Brightside").httpManager(mockedHttpManager).build();
		final Page<Track> trackSearchResult = request.exec();
		assertTrue(trackSearchResult.getTotal() > 0);
		assertEquals(20, trackSearchResult.getLimit());
		assertEquals(0, trackSearchResult.getOffset());
		final List<Track> tracks = trackSearchResult.getItems();
		final Track firstTrack = tracks.get(0);
		assertNotNull(firstTrack.getId());
		final String id = firstTrack.getId();
		assertNotNull(firstTrack.getAlbum());
		assertNotNull(firstTrack.getArtists());
		assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());
	}

	@Test
	public void shouldGetTrackResult() throws Exception {
		final String id = "0eGsygTp906u18L0Oimnem";
		final Request<Track> request = api.getTrack(id)
				.httpManager(TestUtil.MockedHttpManager.returningJson("track.json"))
				.build();
		final Track track = request.exec();
		assertNotNull(track);
		assertEquals(id, track.getId());
	}

	@Test
	public void shouldGetTopTracksForArtistResult() throws Exception {
		final Request<List<Track>> request = api.getTopTracksForArtist("43ZHCT0cAZBISjO8DG9PnE", CountryCode.GB)
				.httpManager(TestUtil.MockedHttpManager.returningJson("tracks-for-artist.json"))
				.build();
		final List<Track> tracks = request.exec();
		assertTrue(tracks.size() > 0);
		final Track firstTrack = tracks.get(0);
		assertNotNull(firstTrack.getAlbum());
		assertNotNull(firstTrack.getArtists());
		assertNotNull(firstTrack.getAvailableMarkets());
		assertTrue(firstTrack.getDiscNumber() > 0);
		assertTrue(firstTrack.getDuration() > 0);
		assertNotNull(firstTrack.isExplicit());
		assertNotNull(firstTrack.getExternalIds());

		final String id = firstTrack.getId();
		assertNotNull(firstTrack.getId());
		assertEquals("https://open.spotify.com/track/" + id, firstTrack.getExternalUrls().get("spotify"));
		assertEquals("https://api.spotify.com/v1/tracks/" + id, firstTrack.getHref());
		assertTrue(firstTrack.getPopularity() >= 0 && firstTrack.getPopularity() <= 100);
		assertNotNull(firstTrack.getPreviewUrl());
		assertTrue(firstTrack.getTrackNumber() >= 0);
		assertEquals(SpotifyEntityType.TRACK, firstTrack.getType());
		assertEquals("spotify:track:" + id, firstTrack.getUri());
	}

	@Test
	public void shouldGetNewReleasesResult() throws Exception {
		final Request<NewReleases> request = api.getNewReleases()
				.limit(3)
				.offset(1)
				.country(CountryCode.SE)
				.httpManager(TestUtil.MockedHttpManager.returningJson("new-releases.json"))
				.build();
		final NewReleases newReleases = request.exec();
		assertNotNull(newReleases.getAlbums());

		final Page<SimpleAlbum> albums = newReleases.getAlbums();
		assertEquals("https://api.spotify.com/v1/browse/new-releases?country=SE&offset=1&limit=3",
				albums.getHref());
		assertEquals(3, albums.getLimit());
		assertEquals(1, albums.getOffset());
		assertEquals("https://api.spotify.com/v1/browse/new-releases?country=SE&offset=4&limit=3",
				albums.getNext());
		assertEquals("https://api.spotify.com/v1/browse/new-releases?country=SE&offset=0&limit=3",
				albums.getPrevious());
		assertEquals(500, albums.getTotal());
		final SimpleAlbum firstItem = albums.getItems().get(0);
		assertEquals(AlbumType.SINGLE, firstItem.getAlbumType());
		assertEquals(1, firstItem.getAvailableMarkets().size());
		assertEquals("SE", firstItem.getAvailableMarkets().get(0));
		assertNotNull(firstItem.getExternalUrls());
		assertEquals("spotify:album:5McUiSC2VSw2ToVHR8tnzZ", firstItem.getUri());
	}

	@Test
	public void shouldCreatePlaylist() throws Exception {
		final Api api = Api.builder().accessToken("someAccessToken").build();
		final Request<Playlist> request = api.createPlaylist("thelinmichael","title")
				.publicAccess(true)
				.httpManager(TestUtil.MockedHttpManager.returningJson("created-playlist.json"))
				.build();
		final Playlist playlist = request.exec();
		assertFalse(playlist.isCollaborative());
		assertNull(playlist.getDescription());
		assertEquals("http://open.spotify.com/user/thelinmichael/playlist/2LfixThJPNO9DAreghF2WK", playlist.getExternalUrls().get("spotify"));
		assertNull(playlist.getFollowers());
		assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/2LfixThJPNO9DAreghF2WK", playlist.getHref());
		assertEquals("2LfixThJPNO9DAreghF2WK", playlist.getId());
		assertEquals(1, playlist.getImages().size());
		assertNull(playlist.getImages().get(0));
		assertEquals("Coolest Playlist", playlist.getName());
		assertNotNull(playlist.getOwner());
		assertTrue(playlist.isPublicAccess());
		assertNull(playlist.getTracks());
		assertEquals(SpotifyEntityType.PLAYLIST, playlist.getType());
		assertEquals("spotify:user:thelinmichael:playlist:2LfixThJPNO9DAreghF2WK", playlist.getUri());
	}

	@Test
	public void shouldCreatePlaylistPage() throws Exception {
		final Request<Playlist> request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
				.httpManager(TestUtil.MockedHttpManager.returningJson("playlist-response.json"))
				.build();
		final Playlist playlist = request.exec();
		assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn", playlist.getHref());
	}

	@Test
	public void shouldBeAbleToHandlePlaylistsWithLocalFiles() throws Exception {
		final Request<Playlist> request = api.getPlaylist("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
				.httpManager(TestUtil.MockedHttpManager.returningJson("playlist-localfiles-response.json"))
				.build();
		final Playlist playlist = request.exec();
		assertTrue(playlist.getTracks().getItems().get(0).getTrack().getAlbum().getAlbumType() == null);
		assertNotNull(playlist);
	}

	@Test
	public void shouldGetPlaylistTracksResult() throws Exception {
		final String accessToken = "someToken";
		final Api api = Api.builder().accessToken(accessToken).build();
		final Request<Page<PlaylistTrack>> request = api
				.getPlaylistTracks("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
				.httpManager(TestUtil.MockedHttpManager.returningJson("playlist-tracks.json"))
				.build();
		final Page<PlaylistTrack> page = request.exec();

		assertNotNull(page);
		assertEquals("https://api.spotify.com/v1/users/thelinmichael/playlists/3ktAYNcRHpazJ9qecm3ptn/tracks",
				page.getHref());
		assertEquals(100, page.getLimit());
		assertNull(page.getNext());
		assertEquals(0, page.getOffset());
		assertNull(page.getPrevious());
		assertTrue(page.getTotal() > 0);
		final PlaylistTrack playlistTrack = page.getItems().get(0);
		assertNotNull(playlistTrack.getAddedAt());
		assertNotNull(playlistTrack.getAddedBy());
		final Track track = playlistTrack.getTrack();
		assertTrue(track.getPopularity() >= 0);
	}

	@Test
	public void shouldGetRelatedArtists() throws Exception {
		final Request<List<Artist>> request = api
				.getArtistRelatedArtists("0qeei9KQnptjwb8MgkqEoy")
				.httpManager(TestUtil.MockedHttpManager.returningJson("related-artists.json"))
				.build();
		final List<Artist> artists = request.exec();
		assertFalse(artists.isEmpty());
		final Artist firstArtist = artists.get(0);
		final String id = firstArtist.getId();
		assertEquals("https://api.spotify.com/v1/artists/" + id, firstArtist.getHref());
		assertEquals(id, firstArtist.getId());
		assertEquals("spotify:artist:" + id, firstArtist.getUri());
	}

	@Test
	public void removeFromMySavedTracks() throws Exception {
		final String accessToken = "accessToken";
		final Api api = Api.builder().accessToken(accessToken)
				.httpManager(TestUtil.MockedHttpManager.returningString(""))
				.build();
		final List<String> tracksToAdd = Arrays.asList("5xFF6wNcoRwx7N3cDTgVWP", "13zm8XhfM4RBtQpjdqY44e");
		final Request<String> request = api.removeFromMySavedTracks(tracksToAdd).build();
		String response = request.exec();
		assertEquals("", response);
	}

	@Test
	public void shouldGetSavedTracks() throws Exception {
		final Api api = Api.builder().accessToken("someAccessToken").build();
		final Request<Page<LibraryTrack>> request = api.getMySavedTracks()
				.limit(5)
				.offset(1)
				.httpManager(TestUtil.MockedHttpManager.returningJson("saved-tracks.json"))
				.build();
		final Page<LibraryTrack> libraryTracks = request.exec();
		assertNotNull(libraryTracks);
		assertEquals("https://api.spotify.com/v1/me/tracks?offset=1&limit=5", libraryTracks.getHref());
		List<LibraryTrack> items = libraryTracks.getItems();
		assertEquals(5, items.size());
		LibraryTrack firstItem = libraryTracks.getItems().get(0);
		assertNotNull(firstItem.getAddedAt());
		assertNotNull(firstItem.getTrack());
		assertEquals("13zm8XhfM4RBtQpjdqY44e", firstItem.getTrack().getId());
	}

	@Test
	public void shouldGetFeaturedPlaylistsResult() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, 9, 23, 9, 0, 0);
		Date timestamp = calendar.getTime();
		final Request<FeaturedPlaylists> request = api.getFeaturedPlaylists()
				.limit(1)
				.offset(1)
				.country(CountryCode.SE)
				.timestamp(timestamp)
				.httpManager(TestUtil.MockedHttpManager.returningJson("featured-playlists.json"))
				.build();
		FeaturedPlaylists featuredPlaylists = request.exec();
		assertEquals("Behöver du hjälp att komma igång idag?", featuredPlaylists.getMessage());
		Page<SimplePlaylist> playlistPage = featuredPlaylists.getPlaylists();
		assertEquals(12, playlistPage.getTotal());
		assertEquals(1, playlistPage.getOffset());
		assertEquals(1, playlistPage.getLimit());
		assertEquals("https://api.spotify.com/v1/browse/featured-playlists?country=SE&" +
				"locale=sv_SE&timestamp=2014-10-23T09:00:00&offset=2&limit=1",
				playlistPage.getNext());
		assertEquals("https://api.spotify.com/v1/browse/featured-playlists?country=SE&" +
				"locale=sv_SE&timestamp=2014-10-23T09:00:00&offset=0&limit=1",
				playlistPage.getPrevious());
		List<SimplePlaylist> items = playlistPage.getItems();
		assertEquals(1, items.size());
		SimplePlaylist playlist = items.get(0);
		assertEquals("2BgVZaiDigaqxTbZEI2TpE", playlist.getId());
		assertEquals("Träning", playlist.getName());
	}

	@Test
	public void shouldGetCurrentUser() throws Exception {
		final Request<User> request = api.getMe()
				.accessToken("myLongAccessToken")
				.httpManager(TestUtil.MockedHttpManager.returningJson("current-user.json"))
				.build();
		final User user = request.exec();
		assertNotNull(user);
		assertEquals("Michael", user.getDisplayName());
		assertEquals("thelinmichael+test@gmail.com", user.getEmail());
		assertEquals("https://open.spotify.com/user/thelinmichael", user.getExternalUrls().get(
				"spotify"));
		assertEquals("https://api.spotify.com/v1/users/thelinmichael", user.getHref());
		assertEquals("thelinmichael", user.getId());
		assertEquals("SE", user.getCountry());
		assertNotNull(user.getFollowers());
		assertNull(user.getImages().get(0).getHeight());
		assertNull(user.getImages().get(0).getWidth());
		assertEquals("http://media.giphy.com/media/Aab07O5PYOmQ/giphy.gif", user.getImages().get(0).getUrl());
		assertEquals(Product.PREMIUM, user.getProduct());
		assertEquals("spotify:user:thelinmichael", user.getUri());
		assertEquals("1989-07-04", user.getBirthdate());
	}

	@Test
	public void shouldCheckContains() throws Exception {
		final String accessToken = "someAccessToken";
		final Api api = Api.builder().accessToken(accessToken).build();
		Request<List<Boolean>> request = api.containsMySavedTracks(
				Arrays.asList("0udZHhCi7p1YzMlvI4fXoK", "1e1VmyiAuPyM4SHhySP1oU"))
				.httpManager(TestUtil.MockedHttpManager.returningJson("yourmusic-contains.json"))
				.build();
		List<Boolean> response = request.exec();
		assertFalse(response.get(0));
		assertTrue(response.get(1));
	}

	@Test
	public void shouldChangeNameAndPublishedStatus() throws Exception  {
		final String accessToken = "someAccessToken";
		final Api api = Api.builder().accessToken(accessToken).build();
		Request<String> request = api
				.changePlaylistDetails("thelinmichael", "3ktAYNcRHpazJ9qecm3ptn")
				.publicAccess(true)
				.name("Testing playlist name change")
				.httpManager(TestUtil.MockedHttpManager.returningString(""))
				.build();
		String response = request.exec();
		assertEquals("", response);
	}

	@Test
	public void shouldGetArtistsResult() throws Exception {
		final Request<List<Artist>> request = api.getArtists("0oSGxfWSnnOXhD2fKuz2Gy", "3dBVyJ7JuOMt4GE9607Qin")
				.httpManager(TestUtil.MockedHttpManager.returningJson("artists.json"))
				.build();
		final List<Artist> artists = request.exec();
		assertEquals(2, artists.size());
		final Artist firstArtist = artists.get(0);
		final Artist secondArtist = artists.get(1);
		assertEquals("0oSGxfWSnnOXhD2fKuz2Gy", firstArtist.getId());
		assertEquals("3dBVyJ7JuOMt4GE9607Qin", secondArtist.getId());
	}

	@Test
	public void shouldSearchArtistsResult() throws Exception {
		final Request<Page<Artist>> request = api.searchArtists("tania bowra")
				.limit(20)
				.offset(0)
				.httpManager(TestUtil.MockedHttpManager.returningJson("search-artist.json"))
				.build();
		final Page<Artist> artistSearchResult = request.exec();
		assertEquals(20, artistSearchResult.getLimit());
		assertEquals(0, artistSearchResult.getOffset());
		assertTrue(artistSearchResult.getTotal() > 0);
		assertNull(artistSearchResult.getNext());
		assertNull(artistSearchResult.getPrevious());
		assertEquals("https://api.spotify.com/v1/search?query=tania+bowra&offset=0&limit=20&type=artist", artistSearchResult.getHref());
		List<Artist> artists = artistSearchResult.getItems();
		Artist firstArtist = artists.get(0);
		assertEquals("08td7MxkoHQkXnWAYD8d6Q", firstArtist.getId());
		assertEquals("https://open.spotify.com/artist/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getExternalUrls().get("spotify"));
		assertNotNull(firstArtist.getGenres());
		assertEquals("https://api.spotify.com/v1/artists/08td7MxkoHQkXnWAYD8d6Q", firstArtist.getHref());
		assertNotNull(firstArtist.getImages());
		assertEquals("Tania Bowra", firstArtist.getName());
		assertTrue(firstArtist.getPopularity() >= 0 && firstArtist.getPopularity() <= 100);
		assertEquals(SpotifyEntityType.ARTIST, firstArtist.getType());
		assertEquals("spotify:artist:08td7MxkoHQkXnWAYD8d6Q", firstArtist.getUri());
	}

	@Test
	public void shouldGetArtistResult() throws Exception {
		Request<Artist> request = api.getArtist("2BTZIqw0ntH9MvilQ3ewNY")
				.httpManager(TestUtil.MockedHttpManager.returningJson("artist.json"))
				.build();
		final Artist artist = request.exec();
		assertNotNull(artist);
		assertEquals("https://open.spotify.com/artist/2BTZIqw0ntH9MvilQ3ewNY", artist.getExternalUrls().get("spotify"));
		assertNotNull(artist.getGenres());
		assertEquals("2BTZIqw0ntH9MvilQ3ewNY", artist.getId());
		assertNotNull(artist.getImages());
		assertEquals("Cyndi Lauper", artist.getName());
		assertTrue(artist.getPopularity() >= 0 && artist.getPopularity() <= 100);
		assertEquals("spotify:artist:2BTZIqw0ntH9MvilQ3ewNY", artist.getUri());
	}

	@Test
	public void shouldGetAlbumResultForIds() throws Exception {
		final Request<List<Album>> request = api.getAlbums("2hYe61Nd2oOoM6RYCwIma1")
				.httpManager(TestUtil.MockedHttpManager.returningJson("albums.json"))
				.build();
		final List<Album> albums = request.exec();
		assertEquals(1, albums.size());
		final Album firstAlbum = albums.get(0);
		assertEquals("2hYe61Nd2oOoM6RYCwIma1", firstAlbum.getId());
		assertEquals(AlbumType.ALBUM, firstAlbum.getAlbumType());
		assertEquals("2013-11-08", firstAlbum.getReleaseDate());
		assertEquals("day", firstAlbum.getReleaseDatePrecision());
		List<SimpleArtist> artists = firstAlbum.getArtists();
		SimpleArtist firstArtist = artists.get(0);
		assertEquals("https://api.spotify.com/v1/artists/53A0W3U0s8diEn9RhXQhVz", firstArtist.getHref());
		assertEquals("53A0W3U0s8diEn9RhXQhVz", firstArtist.getId());
		final Page<SimpleTrack> tracksPage = firstAlbum.getTracks();
		assertEquals("https://api.spotify.com/v1/albums/2hYe61Nd2oOoM6RYCwIma1/tracks?offset=0&limit=50", tracksPage.getHref());
		assertEquals(0, tracksPage.getOffset());
		assertEquals(50, tracksPage.getLimit());
		assertEquals(20, tracksPage.getTotal());
		assertEquals("52J94k3JBYbHlFyg7zAABB", tracksPage.getItems().get(0).getId());
	}

	@Test
	public void shouldGetAlbumResultForArtistId() throws Exception {
		final Request<Page<SimpleAlbum>> request = api.getAlbumsForArtist("1vCWHaC5f2uS3yhpwWbIA6")
				.limit(2)
				.types(AlbumType.SINGLE)
				.market(CountryCode.US)
				.httpManager(TestUtil.MockedHttpManager.returningJson("artist-album.json"))
				.build();
		final Page<SimpleAlbum> albumSearchResult = request.exec();
		assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=0&limit=2&album_type=single&market=US", albumSearchResult.getHref());
		assertEquals(2, albumSearchResult.getLimit());
		assertEquals(0, albumSearchResult.getOffset());
		assertEquals(34, albumSearchResult.getTotal());
		assertEquals("https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?offset=2&limit=2&album_type=single&market=US", albumSearchResult.getNext());
		assertNull(albumSearchResult.getPrevious());
		final List<SimpleAlbum> albums = albumSearchResult.getItems();
		assertEquals(2, albums.size());
		final SimpleAlbum firstAlbum = albums.get(0);
		assertEquals(AlbumType.SINGLE, firstAlbum.getAlbumType());
		assertEquals("https://open.spotify.com/album/6RcscDLgp8v0mSRxvRhfG0", firstAlbum.getExternalUrls().get("spotify"));
		assertEquals("https://api.spotify.com/v1/albums/6RcscDLgp8v0mSRxvRhfG0", firstAlbum.getHref());
		assertEquals("6RcscDLgp8v0mSRxvRhfG0", firstAlbum.getId());
		assertNotNull(firstAlbum.getImages());
	}

	@Test
	public void shouldGetAlbumsResult() throws Exception {
		final Request<Page<SimpleAlbum>> request = api.searchAlbums("tania bowra")
				.httpManager(TestUtil.MockedHttpManager.returningJson("search-album.json"))
				.build();
		final Page<SimpleAlbum> albumSearchResult = request.exec();
		assertEquals("https://api.spotify.com/v1/search?query=tania%2Bbowra&offset=0&limit=20&type=album", albumSearchResult.getHref());
		assertEquals(20, albumSearchResult.getLimit());
		assertEquals(0, albumSearchResult.getOffset());
		assertNull(albumSearchResult.getNext());
		assertNull(albumSearchResult.getPrevious());
		assertEquals(1, albumSearchResult.getTotal());
		final List<SimpleAlbum> albums = albumSearchResult.getItems();
		assertEquals(1, albums.size());
		final SimpleAlbum firstAlbum = albums.get(0);
		assertEquals("https://open.spotify.com/album/6akEvsycLGftJxYudPjmqK", firstAlbum.getExternalUrls().get("spotify"));
		assertEquals("https://api.spotify.com/v1/albums/6akEvsycLGftJxYudPjmqK", firstAlbum.getHref());
		assertEquals("6akEvsycLGftJxYudPjmqK", firstAlbum.getId());
		assertEquals("Place In The Sun", firstAlbum.getName());
		assertEquals(SpotifyEntityType.ALBUM, firstAlbum.getType());
		assertEquals("spotify:album:6akEvsycLGftJxYudPjmqK", firstAlbum.getUri());
		assertNotNull(firstAlbum.getAvailableMarkets());
		assertFalse(firstAlbum.getAvailableMarkets().isEmpty());
	}

	@Test
	public void shouldGetAlbumResult() throws Exception {
		final Request<Album> request = api.getAlbum("4pox3k0CGuwwAknR9GtcoX")
				.httpManager(TestUtil.MockedHttpManager.returningJson("album.json"))
				.build();
		final Album album = request.exec();
		assertNotNull(album);
		assertEquals("4pox3k0CGuwwAknR9GtcoX", album.getId());
		assertNotNull(album.getCopyrights());
		assertFalse(album.getCopyrights().isEmpty());
	}

	@Test
	public void shouldAddToMySavedTracks() throws Exception {
		final String accessToken = "someAccessToken";
		final Api api = Api.builder().accessToken(accessToken).build();
		final List<String> tracksToAdd = Arrays.asList("4BYGxv4rxSNcTgT3DsFB9o", "0BG2iE6McPhmAEKIhfqy1X");
		final Request<String> request = api.addToMySavedTracks(tracksToAdd)
				.httpManager(TestUtil.MockedHttpManager.returningString(""))
				.build();
		final String addTrackResponse = request.exec();
		assertEquals("", addTrackResponse);
	}

}
