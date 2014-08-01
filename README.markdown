# ssolution-commons

Utilities classes we use through various projects

# Install and Configuration

* Manually [add the jar file](http://repo1.maven.org/maven2/com/sifionsolution/ssolution-commons/) to your app 

* Or ... add our dependency to your maven build script:

```xml
<dependency>
    <groupId>com.sifionsolution</groupId>
    <artifactId>ssolution-commons</artifactId>
    <version>1.0</version> <!-- Check for the latest version -->
</dependency>
```

* Or ... try it out Gradle: 

```gradle
compile 'com.sifionsolution:ssolution-commons:1.0' //Check for the latest version
```


    
# Usage and Features

## Joiner

Fast and easy way to Join a Collection of your elements into a costumizable String. You can also manage your Collection within the join with possiblities like adding or removing new itens or new collections. 

#### Basic usage 

```java
String joined = Joiner.from(yourCollections).join("separator");

//Example
String joined = Joiner.from(owners).join(", ");

// Output:
// element1, element2, element3
``` 

##### Suffix and Preffix

```java
String joined = Joiner.from(yourCollections).join("separator", "preffix", "suffix");

//Example
String joined = Joiner.from(owners).join(", ", "{", "}");

// Output:
// {element1, element2, element3}
``` 

##### Add Before Element and Add After Element

```java
String joined = Joiner.from(owners).addBeforeEachElement(":").addAfterEachElement("!").join(", ");

// Output:
// :element1!, :element2!, :element3!;
```

##### Surround each Element
```java
String joined = Joiner.from(owners).surroundEachElementWith("'").join(", ");

// Output:
// 'element1', 'element2', 'element3'
```

#### Mapping the output

By default, we use your Object's toString method to define the String to be joined. You can change this behavior by adding your own map strategy. To do this, you´ll need to implement a `JoinerFunction<T>` and override the method `apply(T t)`.

Let's consider this class for the example:

```java
public class Person {
	private final String name;
	private final String lastName;
	
	//Getters and toString override	
} 
```

If we want to join a Collection of Person, but we want the output format to be: "Name LastName", this is how we should do:

```java
Person rafael = new Person("Rafael", "Guerreiro");
Person marco = new Person("Marco", "Noronha");

List<Person> owners = asList(rafael, marco);


String joined = Joiner.from(owners).map(new JoinerFunction<Person>() {
			@Override
			public String apply(Person t) {
				return t.getFullName(); 
			}
		}).join(", ");
		
// Output:
// Rafael Guerreiro, Marco Noronha
```

##String utilities

These are the possibilities while working with Strings:

* Trim and Capitalize, transform to Uppercase,  Lowercase
* NullSafe methods (returns am empty string if value is null).

Checkout this jUnit test's for a basic usage:

```java
//static imports:
import static com.sifionsolution.commons.StringAdapter.trimCapitalize;

//Trim Capitalize
assertEquals("", trimCapitalize(""));
assertNotNull(getNullSafeCapitalize(null));
assertEquals("Test here", trimCapitalize("             test here  "));
		
//Trim UpperCase
assertEquals("", trimUpperCase(""));
assertNotNull(getNullSafeUpperCase(null));
assertEquals("TESTE HERE", trimUpperCase("             test here  "));
		
//Trim LowerCase
assertEquals("", trimLowerCase(""));
assertNotNull(getNullSafeLowerCase(null));
assertEquals("test here", trimLowerCase("             TEST hERe  "));		
```

## Content utilities

Easy way to check whether or not a String, a collection, a map or an array is empty. 

* notEmpty(Map<?, ?> map)
* notEmpty(Collection<?> collection)
* notEmpty(Object[] objs)
* notEmpty(String str) 
* Same varsions of the methods above for "isEmpty"

More tests to demonstrate how this works:

```java
//Testing Strings

String str = ""; //false
assertFalse(ContentVerifyer.notEmpty(str));

str = null; //false
assertFalse(ContentVerifyer.notEmpty(str));

str = " "; //false
assertFalse(ContentVerifyer.notEmpty(str));

str = "Testing a string"; //true
assertTrue(ContentVerifyer.notEmpty(str));

//Testing Collections

Collection<Object> collection = null; //false
assertFalse(ContentVerifyer.notEmpty(collection));

collection = new ArrayList<Object>(); //false
assertFalse(ContentVerifyer.notEmpty(collection));

collection.add(null); //true
assertTrue(ContentVerifyer.notEmpty(collection));

collection = new ArrayList<Object>();
collection.add("Teste"); //true
assertTrue(ContentVerifyer.notEmpty(collection));
```

## Date and Calendar utilities

Easy way to transform String to Date or Calendar and vice-versa. This is how it works:

```java
String pattern = "dd/MM/yyyy";
Date date = fromStringToDate("10/11/2014", pattern);
assertEquals("10/11/2014", fromDateToString(date, pattern));

Calendar date = fromStringToCalendar("10/11/2014", pattern);
assertEquals("10/11/2014", fromCalendarToString(date, pattern));
```

