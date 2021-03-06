package com.sifionsolution.commons.joiner;

import static com.sifionsolution.commons.CharSequenceAdapter.getNullSafe;
import static com.sifionsolution.commons.ContentVerifyer.notEmpty;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.sifionsolution.commons.joiner.function.JoinerFunction;

public final class Joiner<T> implements Iterable<T>, Cloneable {
	private Collection<T> c;

	private CharSequence before = "";
	private CharSequence after = "";

	private boolean allowEmpties = false;
	private boolean allowNulls = false;

	private JoinerFunction<T> fn = new JoinerFunction<T>() {
		@Override
		public CharSequence apply(T t) {
			return valueOf(t);
		}
	};

	public Joiner() {
		this(new ArrayList<T>());
	}

	private Joiner(Collection<T> c) {
		if (c == null)
			throw new NullPointerException("The collection cannot be null. It can be empty, but not null.");

		this.c = c;
	}

	public static <E extends Object> Joiner<E> from(E... c) {
		if (c == null)
			throw new NullPointerException("The array cannot be null. It can be empty, but not null.");

		return new Joiner<E>(new ArrayList<E>(asList(c)));
	}

	public static <E extends Object> Joiner<E> from(Collection<E> c) {
		if (c == null)
			throw new NullPointerException("The collection cannot be null. It can be empty, but not null.");

		return new Joiner<E>(c);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((after == null) ? 0 : after.hashCode());
		result = prime * result + ((before == null) ? 0 : before.hashCode());
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joiner<?> other = (Joiner<?>) obj;
		if (after == null) {
			if (other.after != null)
				return false;
		} else if (!after.equals(other.after))
			return false;
		if (before == null) {
			if (other.before != null)
				return false;
		} else if (!before.equals(other.before))
			return false;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Joiner " + join(", ", "[", "]");
	}

	@Override
	public Iterator<T> iterator() {
		return c.iterator();
	}

	@Override
	public Joiner<T> clone() {
		Joiner<T> j = new Joiner<T>(c);

		j.after = after;
		j.before = before;
		j.fn = fn;

		return j;
	}

	public String join(CharSequence delimiter) {
		return join(delimiter, "", "");
	}

	public String join(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
		delimiter = getNullSafe(delimiter);
		prefix = getNullSafe(prefix);
		suffix = getNullSafe(suffix);

		StringBuilder sb = new StringBuilder(prefix);

		CharSequence separator = "";

		for (T o : c) {
			if (isNullAllowed(o)) {
				CharSequence toApply = fn.apply(o);

				if (allowNulls && toApply == null)
					toApply = valueOf(toApply);

				if (isEmptyAllowed(toApply)) {
					sb.append(separator).append(before).append(toApply).append(after);
					separator = delimiter;
				}
			}
		}

		return sb.append(suffix).toString();
	}

	private boolean isNullAllowed(T o) {
		return allowNulls || o != null;
	}

	private boolean isEmptyAllowed(CharSequence toApply) {
		return allowEmpties || notEmpty(getNullSafe(toApply));
	}

	public Joiner<T> map(JoinerFunction<T> fn) {
		if (fn == null)
			throw new NullPointerException("The function cannot be null.");

		this.fn = fn;
		return this;
	}

	public Joiner<T> addBeforeEachElement(CharSequence before) {
		this.before = getNullSafe(before);
		return this;
	}

	public Joiner<T> addAfterEachElement(CharSequence after) {
		this.after = getNullSafe(after);
		return this;
	}

	public Joiner<T> surroundEachElementWith(CharSequence s) {
		return addBeforeEachElement(s).addAfterEachElement(s);
	}

	public Joiner<T> withoutEmpties() {
		allowEmpties = false;
		return this;
	}

	public Joiner<T> withoutNulls() {
		allowNulls = false;
		return this;
	}

	public Joiner<T> allowEmpties() {
		allowEmpties = true;
		return this;
	}

	public Joiner<T> allowNulls() {
		allowNulls = true;
		return this;
	}

	public Joiner<T> add(T e) {
		c.add(e);
		return this;
	}

	public Joiner<T> addUnique(T e) {
		if (!contains(e))
			c.add(e);

		return this;
	}

	public Joiner<T> addAll(Collection<T> es) {
		c.addAll(es);
		return this;
	}

	public boolean isEmpty() {
		return c.isEmpty();
	}

	public boolean contains(T o) {
		return c.contains(o);
	}

	public Joiner<T> remove(T o) {
		c.remove(o);
		return this;
	}

	public boolean containsAll(Collection<T> c) {
		return c.containsAll(c);
	}

	public Joiner<T> removeAll(Collection<T> c) {
		c.removeAll(c);
		return this;
	}

	public Joiner<T> clear() {
		c.clear();
		return this;
	}
}