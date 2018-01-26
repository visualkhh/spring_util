package com.visualkhh.cms.test.java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTest {

	public static void main(String[] args) throws ParseException {
//		DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//		String timestamp = "2011-04-15T20:08:18Z";
//		DateTime dateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(timestamp);
//		new Date();
//		String string = "2011-04-15T20:08:18Z";
//		String string = "2018-01-12T01:16:40Z";
//		DateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//		Date date = iso8601.parse(string);
//		System.out.printf("date"+date);


//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		//Date -> String(UTC)
		Date date = new Date();
		final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'Z";
		final SimpleDateFormat sdf = new SimpleDateFormat(ISO_FORMAT);
//		final TimeZone utc = TimeZone.getTimeZone("UTC");
//		final TimeZone utc = TimeZone.getTimeZone("Asia/Seoul");
		final TimeZone utc = TimeZone.getTimeZone("Greenwich");
		sdf.setTimeZone(utc);
		String dateStr = sdf.format(date);
		System.out.println("Date -> String(UTC) :\t\t"+dateStr);




		//UTC String -> Date
		DateFormat iso8601 = new SimpleDateFormat(ISO_FORMAT);
		date = iso8601.parse(dateStr);
		System.out.println("UTC String -> Date :\t\t"+date);




		sdf.setTimeZone(utc);
		System.out.println("Date -> String(UTC) : \t\t"+sdf.format(date));









//		TimeZone tz;
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss (z Z)");
//
//
//		tz = TimeZone.getTimeZone("Asia/Seoul"); df.setTimeZone(tz);
//		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));
//
//
//		tz = TimeZone.getTimeZone("Greenwich"); df.setTimeZone(tz);
//		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));
//
//
//		tz = TimeZone.getTimeZone("America/Los_Angeles"); df.setTimeZone(tz);
//		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));
//
//
//		tz = TimeZone.getTimeZone("America/New_York"); df.setTimeZone(tz);
//		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));
//
//
//		tz = TimeZone.getTimeZone("Pacific/Honolulu"); df.setTimeZone(tz);
//		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));
//
//
//		tz = TimeZone.getTimeZone("Asia/Shanghai"); df.setTimeZone(tz);
//		System.out.format("%s%n%s%n%n", tz.getDisplayName(), df.format(date));




	}
}
