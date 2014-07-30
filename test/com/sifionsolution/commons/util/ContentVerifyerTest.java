package com.sifionsolution.commons.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ContentVerifyerTest {

	@Test
	public void testString() {
		String str = "";

		assertFalse(ContentVerifyer.notEmpty(str));

		str = null;
		assertFalse(ContentVerifyer.notEmpty(str));

		str = "Testing a string";
		assertTrue(ContentVerifyer.notEmpty(str));

		str = " ";
		assertFalse(ContentVerifyer.notEmpty(str));
	}

	@Test
	public void testCollection() {
		Collection<Object> collection = null;
		assertFalse(ContentVerifyer.notEmpty(collection));

		collection = new ArrayList<Object>();
		assertFalse(ContentVerifyer.notEmpty(collection));

		collection.add(null);
		assertTrue(ContentVerifyer.notEmpty(collection));

		collection = new ArrayList<Object>();
		collection.add("Teste");
		assertTrue(ContentVerifyer.notEmpty(collection));
	}

	@Test
	public void testMap() {
		Map<Object, Object> map = null;
		assertFalse(ContentVerifyer.notEmpty(map));

		map = new HashMap<Object, Object>();
		assertFalse(ContentVerifyer.notEmpty(map));

		map.put(null, null);
		assertTrue(ContentVerifyer.notEmpty(map));

		map = new HashMap<Object, Object>();
		map.put("Teste", null);
		assertTrue(ContentVerifyer.notEmpty(map));
	}

	@Test
	public void testArray() {
		Object[] objs = null;
		assertFalse(ContentVerifyer.notEmpty(objs));

		objs = new Object[] {};
		assertFalse(ContentVerifyer.notEmpty(objs));

		objs = new Object[1];
		assertTrue(ContentVerifyer.notEmpty(objs));

		objs[0] = null;
		assertTrue(ContentVerifyer.notEmpty(objs));

		objs[0] = "Test";
		assertTrue(ContentVerifyer.notEmpty(objs));
	}
}