package com.sifionsolution.commons.joiner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.Assert;
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

		String joined = Joiner.from(rafael, nullPerson, marco).allowNulls().join(", ", "{", "}");

		assertEquals("{Rafael, null, Marco}", joined);
	}

	@Test
	public void shouldAddBeforeAndAfterWhenAllowNulls() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");
		Person nullPerson = null;

		List<Person> owners = asList(rafael, nullPerson, marco);

		String joined = Joiner.from(owners).allowNulls().addBeforeEachElement(":").addAfterEachElement(";").join(", ",
				"{", "}");

		assertEquals("{:Rafael;, :null;, :Marco;}", joined);
	}

	@Test
	public void shouldSurroundEachElementWhenAllowNulls() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");
		Person nullPerson = null;

		List<Person> owners = asList(rafael, nullPerson, marco);

		String joined = Joiner.from(owners).surroundEachElementWith("'").allowNulls().join(", ", "{", "}");

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
	public void theMapFunctionCanReturnNullWhenAllowNulls() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		List<Person> owners = asList(rafael, marco);

		String joined = Joiner.from(owners).map(new JoinerFunction<Person>() {
			@Override
			public String apply(Person t) {
				return null;
			}
		}).allowNulls().surroundEachElementWith("'").join(", ");

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
		}).allowNulls().surroundEachElementWith(null).join(null, null, null);

		assertEquals("nullnull", joined);
	}

	@Test
	public void joinerCanBeIterated() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		List<Person> owners = asList(rafael, marco);

		Joiner<Person> joiner = Joiner.from(owners);

		for (Person p : joiner)
			Assert.assertTrue("Rafael".equals(p.getName()) || "Marco".equals(p.getName()));
	}

	@Test
	public void joinerCanReceiveMoreInstances() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		Joiner<Person> joiner = new Joiner<Person>();
		String joined = joiner.add(rafael).add(marco).map(new JoinerFunction<Person>() {
			@Override
			public String apply(Person t) {
				return t.getFullName();
			}
		}).join(", ");

		assertEquals("Rafael Guerreiro, Marco Noronha", joined);
	}

	@Test
	public void joinerCanBeCleared() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		Joiner<Person> joiner = Joiner.from(rafael, marco);
		String joined = joiner.map(new JoinerFunction<Person>() {
			@Override
			public String apply(Person t) {
				return t.getFullName();
			}
		}).join(", ");

		assertEquals("Rafael Guerreiro, Marco Noronha", joined);

		joined = joiner.clear().join(", ");
		assertEquals("", joined);
	}

	@Test
	public void joinerCanBeHaveSomeObjectRemoved() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		Joiner<Person> joiner = Joiner.from(rafael, marco);
		String joined = joiner.map(new JoinerFunction<Person>() {
			@Override
			public String apply(Person t) {
				return t.getFullName();
			}
		}).join(", ");

		assertEquals("Rafael Guerreiro, Marco Noronha", joined);

		joined = joiner.remove(rafael).join(", ");
		assertEquals("Marco Noronha", joined);
	}

	@Test
	public void joinerCanBeCompared() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		Joiner<Person> j1 = Joiner.from(rafael, marco).surroundEachElementWith("'").map(new JoinerFunction<Person>() {
			@Override
			public String apply(Person t) {
				return t.getFullName();
			}
		});

		Joiner<Person> j2 = j1.clone().surroundEachElementWith("\"");

		assertFalse(j1.equals(j2));

		j2 = j2.surroundEachElementWith("'");

		assertEquals(j1, j2);
	}

	@Test
	public void tellsYouWhenIsEmpty() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		Joiner<Person> joiner = Joiner.from(rafael, marco);

		assertFalse(joiner.isEmpty());

		joiner.clear();

		assertTrue(joiner.isEmpty());
	}

	@Test
	public void checkWhenAnObjectIsPresentInCollection() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		Joiner<Person> joiner = Joiner.from(rafael, marco);

		assertTrue(joiner.contains(new Person("Rafael", "Guerreiro")));
	}

	@Test
	public void addUniqueMustPreventMoreThanOneOfTheSameObject() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		Joiner<Person> joiner = Joiner.from(rafael, marco);

		joiner.addUnique(new Person("Rafael", "Guerreiro")).surroundEachElementWith("'").map(
				new JoinerFunction<Person>() {
					@Override
					public String apply(Person t) {
						return t.getFullName();
					}
				});

		assertEquals("'Rafael Guerreiro', 'Marco Noronha'", joiner.join(", "));
	}

	@Test
	public void withoutNullsShouldRemoveAllNullObjects() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");

		Joiner<Person> joiner = Joiner.from(rafael, null, marco, null).withoutNulls().add(null)
				.surroundEachElementWith("'").map(new JoinerFunction<Person>() {
					@Override
					public String apply(Person t) {
						return t.getFullName();
					}
				});

		assertEquals("'Rafael Guerreiro', 'Marco Noronha'", joiner.join(", "));
	}

	@Test
	public void withoutEmptiesShouldBlockEmptyEntries() {
		Person rafael = new Person("Rafael", "Guerreiro");
		Person marco = new Person("Marco", "Noronha");
		Person empty = new Person("", "");

		Joiner<Person> joiner = Joiner.from(rafael, null, marco, null, empty).surroundEachElementWith("'").map(
				new JoinerFunction<Person>() {
					@Override
					public String apply(Person t) {
						if (t == null)
							return "";

						return t.getFullName();
					}
				}).withoutEmpties();

		assertEquals("'Rafael Guerreiro', 'Marco Noronha'", joiner.join(", "));
	}

	@Test
	public void shouldBlockNullAndEmptyEntriesWhenBothConfigurationsAreActive() {
		Joiner<String> joiner = Joiner.from("Rafael Guerreiro", null, "", "Marco Noronha", null, "")
				.surroundEachElementWith("'").withoutEmpties().withoutNulls();

		assertEquals("'Rafael Guerreiro', 'Marco Noronha'", joiner.join(", "));
	}

	@Test
	public void whenOnlyAllowEmptiesShouldBeWithoutNulls() {
		Joiner<String> joiner = Joiner.from("Rafael Guerreiro", null, "", "Marco Noronha", null, "")
				.surroundEachElementWith("'").allowEmpties();

		assertEquals("'Rafael Guerreiro', '', 'Marco Noronha', ''", joiner.join(", "));
	}

	@Test
	public void whenOnlyAllowNullsShouldBeWithoutEmpties() {
		Joiner<String> joiner = Joiner.from("Rafael Guerreiro", null, "", "Marco Noronha", null, "")
				.surroundEachElementWith("'").allowNulls();

		assertEquals("'Rafael Guerreiro', 'null', 'Marco Noronha', 'null'", joiner.join(", "));
	}

	@Test
	public void nullAsStringIsValidAndShouldBeAppended() {
		Joiner<String> joiner = Joiner.from("null", null, "", "Marco Noronha", null, "").surroundEachElementWith("'")
				.withoutNulls().withoutEmpties();

		assertEquals("'null', 'Marco Noronha'", joiner.join(", "));
	}

}