package com.sifionsolution.commons;

import java.util.Collection;
import java.util.Map;

public class ContentVerifyer {
	private ContentVerifyer() {
	}

	public static boolean notEmpty(Map<?, ?> map) {
		if ((map != null) && (!map.isEmpty()))
			return true;

		return false;
	}

	public static boolean notEmpty(Collection<?> collection) {
		if ((collection != null) && (!collection.isEmpty()))
			return true;

		return false;
	}

	public static boolean notEmpty(Object[] objs) {
		if ((objs != null) && (objs.length > 0))
			return true;

		return false;
	}

	public static boolean notEmpty(String str) {
		if (str == null || str.trim().isEmpty())
			return false;

		return true;
	}

	public static boolean notEmpty(CharSequence seq) {
		if (seq == null || isEmpty(seq.toString()))
			return false;

		return true;
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return !notEmpty(map);
	}

	public static boolean isEmpty(Collection<?> collection) {
		return !notEmpty(collection);
	}

	public static boolean isEmpty(Object[] objs) {
		return !notEmpty(objs);
	}

	public static boolean isEmpty(String str) {
		return !notEmpty(str);
	}

	public static boolean isEmpty(CharSequence seq) {
		return !notEmpty(seq);
	}
}