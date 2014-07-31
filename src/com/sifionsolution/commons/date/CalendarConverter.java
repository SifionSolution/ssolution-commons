package com.sifionsolution.commons.date;

import java.util.Calendar;

public final class CalendarConverter {
	private final String pattern;

	public CalendarConverter(String pattern) {
		this.pattern = pattern;
	}

	public Calendar convert(String date) {
		Calendar now = Calendar.getInstance();
		now.setTime(DateConverter.toDate(date, pattern));

		return now;
	}

	public String convert(Calendar date) {
		return DateConverter.toString(date.getTime(), pattern);
	}

	public static Calendar toCalendar(String date, String pattern) {
		return new CalendarConverter(pattern).convert(date);
	}

	public static String toString(Calendar date, String pattern) {
		return new CalendarConverter(pattern).convert(date);
	}
}