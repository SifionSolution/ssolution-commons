package com.sifionsolution.commons.util;

import static com.sifionsolution.commons.util.ContentVerifyer.isEmpty;
import static com.sifionsolution.commons.util.ContentVerifyer.notEmpty;

public class StringAdapter {

	private StringAdapter() {
	}

	public static String trimCapitalize(String value) {
		if (isEmpty(value))
			return value;

		value = value.trim();

		return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
	}

	public static String trimUpperCase(String value) {
		if (isEmpty(value))
			return value;

		return value.toUpperCase().trim();
	}

	public static String trimLowerCase(String value) {
		if (isEmpty(value))
			return value;

		return value.toLowerCase().trim();
	}

	public static String getValid(String value) {
		if (notEmpty(value))
			return value;

		return "";
	}

	public static String getValidLowerCase(String value) {
		return getValid(trimLowerCase(value));
	}

	public static String getValidUpperCase(String value) {
		return getValid(trimUpperCase(value));
	}

	public static String getValidCapitalize(String value) {
		return getValid(trimCapitalize(value));
	}
}