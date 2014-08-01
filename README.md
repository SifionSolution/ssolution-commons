ssolution-commons
=======

Utilities classes we use through various projects

String utilities
=======

These are the possibilities while working with Strings:

* Trim and Capitalize, transform to Uppercase,  Lowercase
* NullSafe methods (returns am empty string if value is null).

Checkout this jUnit test's for a basic usage:

```
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

Content utilities
=======

Easy way to check whether or not a String, a collection, a map or an array is empty. 

* notEmpty(Map<?, ?> map)
* notEmpty(Collection<?> collection)
* notEmpty(Object[] objs)
* notEmpty(String str) 
* Same varsions of the methods above for "isEmpty"

More tests to demonstrate how this works:

```
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


