package com.sifionsolution.commons;

public class CharSequenceAdapter {
	private CharSequenceAdapter() {
	}

	public static <T extends CharSequence> String getNullSafe(T value) {
		if (value == null)
			return "";

		return value.toString();
	}
}