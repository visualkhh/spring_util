package com.omnicns.medicine.test.java;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ZonedDateTest {

	public static void main(String[] args) throws ParseException {
		String startDt = "2019-10-07 00:00:00.0";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		ZonedDateTime startDt_z = ZonedDateTime.parse(startDt, formatter.withZone(ZoneId.systemDefault()));
		System.out.println(startDt_z.getDayOfWeek().getValue());
//		ZonedDateTime endDt_z = ZonedDateTime.parse(endDt, formatter.withZone(ZoneId.systemDefault()));

//		System.out.println(startDt_z);
//		LocalDateTime localDateTime = new LocalDateTime();
		LocalTime localTime = LocalTime.of(0, 0, 0, 0);
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
//		zonedDateTime = zonedDateTime.minusDays(1);
//		zonedDateTime = zonedDateTime.withHour(0);
//		zonedDateTime = zonedDateTime.withMinute(0);
//		zonedDateTime = zonedDateTime.withSecond(0);
//		zonedDateTime = zonedDateTime.withNano(0);
		zonedDateTime = zonedDateTime.with(localTime);
		zonedDateTime = zonedDateTime.minusWeeks(1);
		//1월요일 ~ 7일요일
		zonedDateTime = zonedDateTime.minusDays(zonedDateTime.getDayOfWeek().getValue() - 1);
		System.out.println(zonedDateTime.getDayOfWeek().getValue());
		System.out.println(zonedDateTime);

		ZonedDateTime wzonedDateTime = zonedDateTime.plusWeeks(1);
		System.out.println(zonedDateTime);
		System.out.println(wzonedDateTime);

//		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
//		ZonedDateTime startDt_z2 = ZonedDateTime.parse(startDt, formatter2.withZone(ZoneId.systemDefault()));
//		ZonedDateTime.

	}
}
