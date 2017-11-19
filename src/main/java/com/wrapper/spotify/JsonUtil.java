package com.wrapper.spotify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SuppressWarnings("javadoc")
public class JsonUtil {

	public static Date createDate(String dateString) throws ParseException {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		return formatter.parse(dateString);
	}

}