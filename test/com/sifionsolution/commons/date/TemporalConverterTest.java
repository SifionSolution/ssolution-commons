package com.sifionsolution.commons.date;

import static com.sifionsolution.commons.date.TemporalConverter.fromCalendarToString;
import static com.sifionsolution.commons.date.TemporalConverter.fromDateToString;
import static com.sifionsolution.commons.date.TemporalConverter.fromStringToCalendar;
import static com.sifionsolution.commons.date.TemporalConverter.fromStringToDate;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TemporalConverterTest {

	@Test
	public void shouldGetCorrectDate() {
		String pattern = "dd/MM/yyyy";

		Date date = fromStringToDate("10/11/2014", pattern);

		assertEquals("10/11/2014", fromDateToString(date, pattern));
	}

	@Test
	public void shouldGetCorrectCalendar() {
		String pattern = "dd/MM/yyyy";

		Calendar date = fromStringToCalendar("10/11/2014", pattern);

		assertEquals("10/11/2014", fromCalendarToString(date, pattern));
	}

}
