package com.wrapper.spotify;

import com.wrapper.spotify.json.JsonFactory;
import com.wrapper.spotify.models.ListJsonFactory;
import com.wrapper.spotify.models.album.Album;
import com.wrapper.spotify.models.album.AlbumJsonFactory;
import com.wrapper.spotify.models.artist.Artist;
import com.wrapper.spotify.models.artist.ArtistJsonFactory;
import com.wrapper.spotify.models.track.Track;
import com.wrapper.spotify.models.track.TrackJsonFactory;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("javadoc")
public class JsonTest {

	@Test
	public void shouldCreateArtist() throws Exception {
		final JsonFactory<Artist> factory = new ArtistJsonFactory();
		final String json = TestUtil.readTestData("artist.json");
		final Artist artist = factory.fromJson(json);
		assertEquals("https://api.spotify.com/v1/artists/2BTZIqw0ntH9MvilQ3ewNY", artist.getHref());
	}

	@Test
	public void shouldCreateSeveralArtists() throws Exception {
		final JsonFactory<List<Artist>> factory = new ListJsonFactory<>("artists", new ArtistJsonFactory());
		final String json = TestUtil.readTestData("artists.json");
		final List<Artist> artists = factory.fromJson(json);
		assertEquals(2, artists.size());
	}

	@Test
	public void shouldCreateAlbum() throws Exception {
		final JsonFactory<Album> factory = new AlbumJsonFactory();
		final String json = TestUtil.readTestData("album.json");
		final Album album = factory.fromJson(json);
		assertEquals("https://api.spotify.com/v1/albums/4pox3k0CGuwwAknR9GtcoX", album.getHref());
	}

	@Test
	public void shouldCreateSeveralAlbums() throws Exception {
		final JsonFactory<List<Album>> factory = new ListJsonFactory<>("albums", new AlbumJsonFactory());
		final String json = TestUtil.readTestData("albums.json");
		final List<Album> albums = factory.fromJson(json);
		assertEquals(1, albums.size());
	}

	@Test
	public void shouldCreateTrack() throws Exception {
		final JsonFactory<Track> factory = new TrackJsonFactory();
		final String json = TestUtil.readTestData("track.json");
		final Track track = factory.fromJson(json);
		assertEquals("0eGsygTp906u18L0Oimnem", track.getId());
	}

	@Test
	public void shouldCreateSeveralTracks() throws Exception {
		final JsonFactory<List<Track>> factory = new ListJsonFactory<>("tracks", new TrackJsonFactory());
		final String json = TestUtil.readTestData("tracks.json");
		final List<Track> tracks = factory.fromJson(json);
		assertEquals(2, tracks.size());
	}

}
