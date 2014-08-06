package com.sifionsolution.commons.date;

import java.util.Calendar;
import java.util.Date;

public final class TemporalConverter {
	private TemporalConverter() {
	}

	public static Date fromStringToDate(String date, String pattern) {
		return DateConverter.toDate(date, pattern);
	}

	public static String fromDateToString(Date date, String pattern) {
		return DateConverter.toString(date, pattern);
	}

	public static Calendar fromStringToCalendar(String date, String pattern) {
		return CalendarConverter.toCalendar(date, pattern);
	}

	public static String fromCalendarToString(Calendar date, String pattern) {
		return CalendarConverter.toString(date, pattern);
	}
}