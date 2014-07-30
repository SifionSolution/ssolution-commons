package com.sifionsolution.commons.util;

import static com.sifionsolution.commons.util.StringAdapter.getValid;
import static com.sifionsolution.commons.util.StringAdapter.getValidCapitalize;
import static com.sifionsolution.commons.util.StringAdapter.getValidLowerCase;
import static com.sifionsolution.commons.util.StringAdapter.getValidUpperCase;
import static com.sifionsolution.commons.util.StringAdapter.trimCapitalize;
import static com.sifionsolution.commons.util.StringAdapter.trimLowerCase;
import static com.sifionsolution.commons.util.StringAdapter.trimUpperCase;
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
	public void getValidTest() {
		assertNotNull(getValid(null));
		assertEquals("test", getValid("test"));
		assertEquals("", getValid(""));

		assertNotNull(getValidCapitalize(null));
		assertNotNull(getValidLowerCase(null));
		assertNotNull(getValidUpperCase(null));
	}

}