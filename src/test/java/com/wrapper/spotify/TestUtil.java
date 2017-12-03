package com.wrapper.spotify;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("javadoc")
public class TestUtil {

	private static final String TEST_DATA_DIR = "src/test/fixtures/";

	private static final int MAX_TEST_DATA_FILE_SIZE = 65536;

	public static String readTestData(String fileName) throws IOException {
		return readFromFile(new File(TEST_DATA_DIR, fileName));
	}

	private static String readFromFile(File file) throws IOException {
		CharBuffer charBuffer = CharBuffer.allocate(MAX_TEST_DATA_FILE_SIZE);
		try(Reader reader = new FileReader(file)) {
			reader.read(charBuffer);
		}
		charBuffer.position(0);
		return charBuffer.toString();
	}

	public static class MockedHttpManager {

		public static HttpClient returningJson(String jsonFixture) throws Exception {
			final String fixture = readTestData(jsonFixture);

			return returningString(fixture);
		}

		public static HttpClient returningString(String returnedString) throws IOException {
			// Mocked HTTP Manager to get predictable responses
			final HttpClient mockedHttpManager = mock(HttpClient.class);
			final HttpEntity mockedHttpEntity = mock(HttpEntity.class);
			final HttpResponse mockedResponse = mock(HttpResponse.class);
			final StatusLine mockedStatusLine = mock(StatusLine.class);
			final BasicHeader mockedHeader = new BasicHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
			when(mockedStatusLine.getStatusCode()).thenReturn(200);
			when(mockedHttpEntity.getContentType()).thenReturn(mockedHeader);
			when(mockedHttpEntity.getContentLength()).thenReturn(returnedString.length() + 0L);
			when(mockedHttpEntity.getContent()).thenReturn(new ByteArrayInputStream(returnedString.getBytes(StandardCharsets.UTF_8)));
			when(mockedResponse.getEntity()).thenReturn(mockedHttpEntity);
			when(mockedResponse.getStatusLine()).thenReturn(mockedStatusLine);
			when(mockedHttpManager.execute(any())).thenReturn(mockedResponse);
			return mockedHttpManager;
		}
	}

}
