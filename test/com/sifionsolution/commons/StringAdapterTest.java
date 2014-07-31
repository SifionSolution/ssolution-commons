package com.sifionsolution.commons;

import static com.sifionsolution.commons.StringAdapter.getNullSafe;
import static com.sifionsolution.commons.StringAdapter.getNullSafeCapitalize;
import static com.sifionsolution.commons.StringAdapter.getNullSafeLowerCase;
import static com.sifionsolution.commons.StringAdapter.getNullSafeUpperCase;
import static com.sifionsolution.commons.StringAdapter.trimCapitalize;
import static com.sifionsolution.commons.StringAdapter.trimLowerCase;
import static com.sifionsolution.commons.StringAdapter.trimUpperCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

	@Test
	public void getNullSafeTest() {
		assertNotNull(getNullSafe(null));
		assertEquals("test", getNullSafe("test"));
		assertEquals("", getNullSafe(""));

		assertNotNull(getNullSafeCapitalize(null));
		assertNotNull(getNullSafeLowerCase(null));
		assertNotNull(getNullSafeUpperCase(null));
	}

}