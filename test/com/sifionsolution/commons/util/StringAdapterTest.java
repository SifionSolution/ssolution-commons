package com.sifionsolution.commons.util;

import static com.sifionsolution.commons.util.StringAdapter.trimCapitalize;
import static com.sifionsolution.commons.util.StringAdapter.trimLowerCase;
import static com.sifionsolution.commons.util.StringAdapter.trimUpperCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class StringAdapterTest {

	@Test
	public void trimTest() {
		assertEquals("", trimCapitalize(""));
		assertNull(trimCapitalize(null));
		assertEquals("Test here", trimCapitalize("test here"));
		assertEquals("Test here", trimCapitalize("             test here  "));
		assertEquals("Test", trimCapitalize("test"));

		assertEquals("", trimUpperCase(""));
		assertNull(trimUpperCase(null));
		assertEquals("TEST HERE", trimUpperCase("test here"));
		assertEquals("TEST HERE", trimUpperCase("             test here  "));
		assertEquals("TEST", trimUpperCase("test"));

		assertEquals("", trimLowerCase(""));
		assertNull(trimLowerCase(null));
		assertEquals("test here", trimLowerCase("TEST HERE"));
		assertEquals("test here", trimLowerCase("             TEST HERE  "));
		assertEquals("test", trimLowerCase("test"));
	}

	// public static String getValid(String value) {
	// if (notEmpty(value))
	// return value;
	//
	// return "";
	// }
	//
	// public static String getValidLowerCase(String value) {
	// return getValid(trimLowerCase(value));
	// }
	//
	// public static String getValidUpperCase(String value) {
	// return getValid(trimUpperCase(value));
	// }
	//
	// public static String getValidCapitalize(String value) {
	// return getValid(trimCapitalize(value));
	// }
}