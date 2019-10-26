package com.omnicns.medicine.test.java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

//		ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//		LocalDate localDate = LocalDate.parse("20180901", formatter);
//		System.out.println(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
//		localDate = LocalDate.parse("20180902", formatter);
//		System.out.println(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
//		localDate = LocalDate.parse("20180903", formatter);
//		System.out.println(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
//		localDate = LocalDate.parse("20180904", formatter);
//		System.out.println(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
//		localDate = LocalDate.parse("20180905", formatter);
//		System.out.println(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
//		localDate = LocalDate.parse("20180906", formatter);
//		System.out.println(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
//		localDate = LocalDate.parse("20180907", formatter);
//		System.out.println(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
//		localDate = LocalDate.parse("20180908", formatter);
//		System.out.println(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
//		localDate = LocalDate.parse("20180909", formatter);
//		System.out.println(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
//		localDate = LocalDate.parse("20180910", formatter);
//		System.out.println(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));


		DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");

		//Convert String to LocalDateTime
		String date = "2013-01-01 14:30";
		LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		System.out.println("LocalDateTime : " + format.format(ldt));

		ZonedDateTime parisDateTime = ldt.atZone(ZoneId.of("Asia/Seoul"));
		System.out.println("Depart : " + format.format(parisDateTime));

		//hard code a zoneoffset like this, UTC-5
//		ZoneOffset nyOffSet = ZoneOffset.of("-05:00");
//		ZonedDateTime nyDateTime = parisDateTime.withZoneSameInstant(nyOffSet).plusHours(8).plusMinutes(10);
//		System.out.println("Arrive : " + format.format(nyDateTime));
		System.out.println("\n---Detail---");
		System.out.println("Depart : " + parisDateTime);
		System.out.println("Depart : " + parisDateTime.getDayOfWeek());
//		System.out.println(parisDateTime.toLocalDateTime().getW);


		Calendar cal1 = GregorianCalendar.from(parisDateTime);
		System.out.println(cal1.getWeekYear());
		System.out.println(cal1.get(Calendar.WEEK_OF_YEAR));
//		System.out.println("Arrive : " + nyDateTime);

		/*Date t = DateUtil.modifyDate(new Date(), Calendar.DATE, -1);
		SimpleDateFormat sDateformat = new SimpleDateFormat("yyyyMMdd");
		System.out.print(sDateformat.format(t));

		ZonedDateTime nowZoned = ZonedDateTime.now();
		nowZoned.minusDays(1);
		System.out.print(nowZoned);*/


		//Date -> String(UTC)
		/*Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		int weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		int monthly = localDate.getMonthValue();
		System.out.println("Date -> : \t\t"+weekly+", "+monthly);



		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString = "01-01-2018 10:20:56";
		date = sdf.parse(dateInString);
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		weekFields = WeekFields.of(Locale.getDefault());
		weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		monthly = localDate.getMonthValue();
		System.out.println("Date2 -> : \t\t"+weekly+", "+monthly+", "+localDate.getDayOfWeek());

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		dateInString = "01-01-2017 10:20:56";
		date = sdf.parse(dateInString);
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		weekFields = WeekFields.of(Locale.getDefault());
		weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		monthly = localDate.getMonthValue();
		System.out.println("Date2 -> : \t\t"+weekly+", "+monthly+", "+localDate.getDayOfWeek());

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		dateInString = "02-01-2022 10:20:56";
		date = sdf.parse(dateInString);
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		weekFields = WeekFields.of(Locale.getDefault());
		weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		monthly = localDate.getMonthValue();
		System.out.println("Date2 -> : \t\t"+weekly+", "+monthly+", "+localDate.getDayOfWeek());

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		dateInString = "14-08-2018 10:20:56";
		date = sdf.parse(dateInString);
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		weekFields = WeekFields.of(Locale.getDefault());
		weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		monthly = localDate.getMonthValue();
		System.out.println("Date2 -> : \t\t"+weekly+", "+monthly+", "+localDate.getDayOfWeek());

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		dateInString = "14-08-2018 10:20:56";
		date = sdf.parse(dateInString);
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		weekFields = WeekFields.of(Locale.getDefault());
		weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		monthly = localDate.getMonthValue();
		System.out.println("Date2 -> : \t\t"+weekly+", "+monthly+", "+localDate.getDayOfWeek());

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		dateInString = "15-08-2018 10:20:56";
		date = sdf.parse(dateInString);
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		weekFields = WeekFields.of(Locale.getDefault());
		weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		monthly = localDate.getMonthValue();
		System.out.println("Date2 -> : \t\t"+weekly+", "+monthly+", "+localDate.getDayOfWeek());

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		dateInString = "16-08-2018 10:20:56";
		date = sdf.parse(dateInString);
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		weekFields = WeekFields.of(Locale.getDefault());
		weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		monthly = localDate.getMonthValue();
		System.out.println("Date2 -> : \t\t"+weekly+", "+monthly+", "+localDate.getDayOfWeek());

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		dateInString = "17-08-2018 10:20:56";
		date = sdf.parse(dateInString);
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		weekFields = WeekFields.of(Locale.getDefault());
		weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		monthly = localDate.getMonthValue();
		System.out.println("Date2 -> : \t\t"+weekly+", "+monthly+", "+localDate.getDayOfWeek());

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		dateInString = "18-08-2018 10:20:56";
		date = sdf.parse(dateInString);
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		weekFields = WeekFields.of(Locale.getDefault());
		weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		monthly = localDate.getMonthValue();
		System.out.println("Date2 -> : \t\t"+weekly+", "+monthly+", "+localDate.getDayOfWeek());

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		dateInString = "19-08-2018 10:20:56";
		date = sdf.parse(dateInString);
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		weekFields = WeekFields.of(Locale.getDefault());
		weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		monthly = localDate.getMonthValue();
		System.out.println("Date2 -> : \t\t"+weekly+", "+monthly+", "+localDate.getDayOfWeek());

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		dateInString = "20-08-2018 10:20:56";
		date = sdf.parse(dateInString);
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		weekFields = WeekFields.of(Locale.getDefault());
		weekly = localDate.get(weekFields.weekOfWeekBasedYear());
		monthly = localDate.getMonthValue();
		System.out.println("Date2 -> : \t\t"+weekly+", "+monthly+", "+localDate.getDayOfWeek());*/

		/*final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'Z";
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
		System.out.println("UTC String -> Date :\t\t"+date);*/




		/*sdf.setTimeZone(utc);
		System.out.println("Date -> String(UTC) : \t\t"+sdf.format(date));*/









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
