package com.sifionsolution.commons;

import static com.sifionsolution.commons.CharSequenceAdapter.getNullSafe;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CharSequenceAdapterTest {

	@Test
	public void getNullSafeTest() {
		assertNotNull(getNullSafe(null));
		assertEquals("test", getNullSafe("test"));
		assertEquals("", getNullSafe(""));
		assertEquals(" ", getNullSafe(" "));
		assertEquals("", getNullSafe(new StringBuilder()));
	}
}