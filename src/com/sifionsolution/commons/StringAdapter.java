package com.sifionsolution.commons;

import static com.sifionsolution.commons.ContentVerifyer.isEmpty;

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

	public static String getNullSafe(String value) {
		return getNullSafe(value);
	}

	public static String getNullSafeLowerCase(String value) {
		return getNullSafe(trimLowerCase(value));
	}

	public static String getNullSafeUpperCase(String value) {
		return getNullSafe(trimUpperCase(value));
	}

	public static String getNullSafeCapitalize(String value) {
		return getNullSafe(trimCapitalize(value));
	}
}