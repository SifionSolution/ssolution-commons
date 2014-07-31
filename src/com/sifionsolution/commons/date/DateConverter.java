package com.sifionsolution.commons.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateConverter {
	private final String pattern;

	public DateConverter(String pattern) {
		this.pattern = pattern;
	}

	public Date convert(String date) {
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Unable to parse from String to Date.", e);
		}
	}

	public String convert(Date date) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static Date toDate(String date, String pattern) {
		return new DateConverter(pattern).convert(date);
	}

	public static String toString(Date date, String pattern) {
		return new DateConverter(pattern).convert(date);
	}
}