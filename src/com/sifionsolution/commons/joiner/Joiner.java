package com.sifionsolution.commons.joiner;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

import java.util.Collection;

import com.sifionsolution.commons.joiner.function.JoinerFunction;

public class Joiner<T> {
	private Collection<T> c;

	private CharSequence before = "";
	private CharSequence after = "";

	private JoinerFunction<T> fn = new JoinerFunction<T>() {
		@Override
		public String apply(T t) {
			return valueOf(t);
		}
	};

	private Joiner(Collection<T> c) {
		this.c = c;
	}

	public static <E extends Object> Joiner<E> from(E... c) {
		if (c == null)
			throw new NullPointerException("The array cannot be null. It can be empty, but not null.");

		return new Joiner<E>(asList(c));
	}

	public static <E extends Object> Joiner<E> from(Collection<E> c) {
		if (c == null)
			throw new NullPointerException("The collection cannot be null. It can be empty, but not null.");

		return new Joiner<E>(c);
	}

	public String join(CharSequence delimiter) {
		return join(delimiter, "", "");
	}

	public String join(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
		delimiter = getValid(delimiter);
		prefix = getValid(prefix);
		suffix = getValid(suffix);

		StringBuilder sb = new StringBuilder(prefix);

		CharSequence separator = "";

		for (T o : c) {
			sb.append(separator).append(before).append(fn.apply(o)).append(after);
			separator = delimiter;
		}

		return sb.append(suffix).toString();
	}

	public Joiner<T> map(JoinerFunction<T> fn) {
		if (fn == null)
			throw new NullPointerException("The function cannot be null.");

		this.fn = fn;
		return this;
	}

	public Joiner<T> addBeforeEachElement(CharSequence before) {
		this.before = getValid(before);
		return this;
	}

	public Joiner<T> addAfterEachElement(CharSequence after) {
		this.after = getValid(after);
		return this;
	}

	public Joiner<T> surroundEachElementWith(CharSequence s) {
		return addBeforeEachElement(s).addAfterEachElement(s);
	}

	private CharSequence getValid(CharSequence cs) {
		if (cs == null)
			return "";

		return cs;
	}
}