package com.sifionsolution.commons.joiner.function;

public interface JoinerFunction<T> {
	CharSequence apply(T t);
}