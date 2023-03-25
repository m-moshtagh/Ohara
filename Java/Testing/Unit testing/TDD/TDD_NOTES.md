# TDD

Most of the TDD development PDF files are located in Coding utilities.
In this approach we create a test class.

* We Write a test to demonstrate how the code should work
* We Make it run
* We refactor the code to make it a better code with great quality

## Junit5

It has three modules:

* Junit platform
* Junit Jupiter
* Junit vintage

Setting up the POM:
we need junit-jupiter-engine & junit-jupiter-api dependencies.
We also need maven surefire plugin for maven to run tests.

We can create test classes and write methods and mark them with @Test
to be recognised as test cases.
Junit offers quite a bunch of useful annotations. Also, some lifeCycle hooks:

* BeforeAll
* AfterAll
* BeforeEach
* AfterEach
    * BeforeAll & AfterAll will be called only once

### Assertion methods

There are lots of assertions methods provided by Junit or other frameworks like, assertEquals which we pass the expected
and the return type, and it will evaluate if the test is passed or not.

**BEST PRACTICE**

> always pass a message to the assertion method.
> In Junit5 we can also pass lambda expression in case we have a complex heavy message.

* assertEquals(): evaluates the equality
* assertTrue(): evaluates if the expression returns true.
* assertAll(): this is for testing grouped assertions. the first parameter is a string, and then we can pass lambda
  expressions(assertions). This is called GroupedAssertions.
    * we can also nest some assertAll() to test dependant tests, for example, testing a derived class, and it's super
      class properties.
* @Disabled: we can ignore and disable some tests or whole test class. Also, we can pass a value for the reason we
  disabled the test.
* @DisplayName: we can specify a test with a meaningful name which makes it more readable for debugging.
* assertThrow: we pass the exception we assume we'll get and, then we pass a lambda which calls the method.
* assertTimeout: We have two methods assertTimeout and assertTimeoutPreemptively which are used to evaluate if a method
  takes longer than a certain time.
* Assumptions.*: Sometimes our tests rely on another statement which is they are not ok, there is no point to go along
  with the rest of the test. for example checking if any resource is available then check something on it. Here we can
  use
  Assumption on the first condition. the difference is that if, we use assertion on the first one, the whole test fails
  but
  using assumptions it just gets ignored.
* Conditional testing annotations: there a bunch of annotations that will run when some conditions are met like,
  OS, JRE and environment properties are met.

## AssertJ

is a separate Library alongside Junit, so we can combine them both. it has a assertThat() method which can be
chained with other methods.

## Hamcrest

It's also an assertion library which is older than assertj.

### Tags

We can specify our test classes with @Tag and give it a value. Then we can use our IDE>run/debug setting and add
a JUnit configuration, and then we can specify tags so when we run only tests concerned with that tag can be executed.

### Nested tests

We can use @Nested to create nested classes which have nested tests.
We can also create interfaces for test classes to implement them. So this enables using default methods in our test
classes. we can use LifeCycle hooks on them and annotate interface with @TestInstance and tell if it should create it
per class or per method.

**BEST PRACTICE**

> we can abstract common functionality using this method.

> We can repeat test with @RepeatedTest

> There are also, predefined Objects like TestInfo & RepetitionInfo that we can use to get some values from them.

### Parameterized test

We can use this way to create tests that get use the values we provide as the arguments of the method. This can be
achieved in several ways like, hardcode, csv files and enums. at this moment we need to add a different dependency to
access the annotations. junit-jupiter-params

* @ParameterizedTest
* @ValueSource
    * We can also pass name parameter to @ParameterizedTest to display some values like name, index, argument and ...

> We can also add @EnumSource(ENUM_CLASS_NAME.class) and test using that enum.

> We can also add @CsvSource and pass csv values in a string form. We can use @CsvFileSource and give it the path of
> a csv file in resources.

> There is also a way named methodProvider @MethodSource which we pass a method name that has a Stream<Arguments> return
> type. We can also, create our custom provider and use @ArgumentsSource

> There is way to create custom classes like timer class and extend it in Junit.

## Mockito:

This useful Lib helps us inject some dependency or as we call mock some objects in our unit testing which is
quite helpful. So we don't use Real development heavy objects. We need mockito-core and mockito-junit-jupiter for
dependency.
For create a mock object we simply call static mock method. for example, we want to create a mock for Map interface:

`Map mapMock = mock(Map.class);`

We can also create mocks using annotations. We create a local instance of the object we want and, then we annotate with
@Mock. Then in @BeforeEach we instantiate this mock by MockitoAnnotations.initMocks(this) and we pass our test class.

```java
public class Test {
    @Mock
    Map<String, String> mapMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock() {
        mapMock.put("key", "value");
    }
}
```

> We can also use it with MockitoExtension class. The setup is like below:

```java

@ExtendedWith(MockitoExtension.class)
public class TestClass {
    @Mock
    Map mapMock;

    @Test
    void testMethod() {
        mapMock.method();
    }
}
```

#### Create a testClass using mockito:

```java

@ExtendedWith(MockitoExtension.class)
public class TestClass {
    @Mock
    DependantObject dependentObject;

    @InjectMock
    TestObject testingObject;

    @Test
    public void testMethod() {
        testObject.method();
    }

}
```

> We can use verify() method to test if the mocked object is verified and, we can also pass times argument to test how
> many times it has been called. it has various methods.

> For return type mockito also provides two static methods: when() and then() which we can test our return type.
> along with verify() method we can have any() method which we specify the type of argument.

### BDD using mockito

We mostly use Given, When and Then for our Behaviour Driven Development. 