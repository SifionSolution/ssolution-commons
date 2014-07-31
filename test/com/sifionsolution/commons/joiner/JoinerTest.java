package com.sifionsolution.commons.joiner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.sifionsolution.commons.joiner.function.JoinerFunction;

public class JoinerTest {

	@Test
	public void whenMappingUsingPrefixAndSuffix() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		List<Person> owners = asList(rafael, marco);

		String joined = Joiner.from(owners).map(new JoinerFunction<Person>() {
			@Override
			public String apply(Person t) {
				return t.getFullName();
			}
		}).join(", ", "{", "}");

		assertEquals("{Rafael Guerreiro, Marco Noronha}", joined);
	}

	@Test
	public void notMappingShouldUseNullSafeToString() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");
		Person nullPerson = null;

		List<Person> owners = asList(rafael, nullPerson, marco);

		String joined = Joiner.from(owners).join(", ", "{", "}");

		assertEquals("{Rafael, null, Marco}", joined);
	}

	@Test
	public void shouldAddBeforeAndAfter() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");
		Person nullPerson = null;

		List<Person> owners = asList(rafael, nullPerson, marco);

		String joined = Joiner.from(owners).addBeforeEachElement(":").addAfterEachElement(";").join(", ", "{", "}");

		assertEquals("{:Rafael;, :null;, :Marco;}", joined);
	}

	@Test
	public void shouldSurroundEachElement() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");
		Person nullPerson = null;

		List<Person> owners = asList(rafael, nullPerson, marco);

		String joined = Joiner.from(owners).surroundEachElementWith("'").join(", ", "{", "}");

		assertEquals("{'Rafael', 'null', 'Marco'}", joined);
	}

	@Test
	public void whenMappingAndSurroundingEachElement() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		List<Person> owners = asList(rafael, marco);

		String joined = Joiner.from(owners).map(new JoinerFunction<Person>() {
			@Override
			public String apply(Person t) {
				return t.getFullName();
			}
		}).surroundEachElementWith("'").join(", ");

		assertEquals("'Rafael Guerreiro', 'Marco Noronha'", joined);
	}

	@Test(expected = NullPointerException.class)
	public void theCollectionCannotBeNull() {
		Joiner.from((Collection<Object>) null);
	}

	@Test(expected = NullPointerException.class)
	public void theArrayCannotBeNull() {
		Joiner.from((Object[]) null);
	}

	@Test(expected = NullPointerException.class)
	public void theMapFunctionCannotBeNull() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		List<Person> owners = asList(rafael, marco);

		Joiner.from(owners).map(null);
	}

	@Test
	public void theMapFunctionCanReturnNull() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		List<Person> owners = asList(rafael, marco);

		String joined = Joiner.from(owners).map(new JoinerFunction<Person>() {
			@Override
			public String apply(Person t) {
				return null;
			}
		}).surroundEachElementWith("'").join(", ");

		assertEquals("'null', 'null'", joined);
	}

	@Test
	public void theDelimiterPrefixSuffixBeforeAndAfterCanBeNull() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		List<Person> owners = asList(rafael, marco);

		String joined = Joiner.from(owners).map(new JoinerFunction<Person>() {
			@Override
			public String apply(Person t) {
				return null;
			}
		}).surroundEachElementWith(null).join(null, null, null);

		assertEquals("nullnull", joined);
	}
}
