package com.wrapper.spotify;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.http.Header;

import net.sf.json.JSONObject;

@SuppressWarnings("javadoc")
public class Assertions {
	
	private static Map<String, String> toMap(String url) {
		return Arrays.stream(url.split("&"))
				.map(part -> part.split("="))
				.collect(Collectors.toMap(part -> part[0], part -> part[1]));
	}

	public static void assertHasParameter(URL url, String name, String value) throws UnsupportedEncodingException {
		final Map<String, String> query = toMap(url.getQuery());
		assertTrue(String.format("Actual URL %s does not contain parameter %s", url.toString(), name), 
				query.containsKey(name));
		assertEquals(query.get(name), URLEncoder.encode(value, StandardCharsets.UTF_8.name()));
	}

	public static void assertNoParameter(URL url, String name) {
		assertFalse(String.format("Actual URL %s contains parameter %s", url, name), 
				toMap(url.getQuery()).containsKey(name));
	}

	@SuppressWarnings("unchecked")
	public static void assertHasBodyParameter(String body, String name, String value) {
		final Map<String, String> query = JSONObject.fromObject(body);
		assertTrue(String.format("Actual URL %s does not contain body parameter %s", body, name), 
				query.containsKey(name) && query.get(name).equals(value));
	}


	public static void assertHasHeader(List<Header> header, String name, String value) {
		final Set<String> keys = header.stream()
				.map(Header::getName)
				.collect(Collectors.toSet());
		final List<String> values;
		assertTrue(String.format("Actual URL %s does not contain header %s", header, name), 
				keys.contains(name));
		values = header.stream()
				.filter(h -> h.getName().equals(name))
				.map(Header::getValue)
				.collect(Collectors.toList());
		assertTrue(values.contains(value));
	}

}
