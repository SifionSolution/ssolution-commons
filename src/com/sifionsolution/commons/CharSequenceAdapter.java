package com.sifionsolution.commons;

import static com.sifionsolution.commons.ContentVerifyer.isEmpty;

public class CharSequenceAdapter {
	private CharSequenceAdapter() {
	}

	public static <T extends CharSequence> String getNullSafe(T value) {
		if (isEmpty(value))
			return "";

		return value.toString();
	}
}