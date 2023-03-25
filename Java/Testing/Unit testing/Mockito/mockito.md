# Mockito

Mockito is a testing framework which aids us by creating mocks from dependency objects so, we don't bother creating
exact objects from collaborator objects.

## What is Mockito?

![mockito](../../../pics/mockito1.png)
![mockito](../../../pics/mockito2.png)
![mockito](../../../pics/mockito3.png)
![mockito](../../../pics/mockito4.png)

For installation, we need mockito-core & mockito-jupiter.

### Test Doubles

* Dummy: Objects not use in test, just fills the holes and keeps the compiler happy.
* Fake: lightweight example of object which is not suitable for production, like in memory db and fake web service
* Stub: provides canned answers, not intelligent enough to respond with anything, These can be hardcoded or configurable
* Spy: More intelligent stub, keeps track of how it was used, also helps with verification.
* Mocks: Use expectations, can fail the test if unexpected calls are made, The focus is on behavior verification.

## Usage

### Create inline mocks

We can use static method `mock(dpendant.class)` to initialize Dependencies in our test class and then pass them to
the main class in setup method.

### Create mocks with annotation

First we write the signature of the CLass we want then, we annotate it with `@Mock`. We create a setup method and write:
`MockitoAnnotations.initMocks(this)`
Then we can correctly use the mock.

Instead of this setup method we can use `@ExtendWIth(MockitoExtension.class)` which is way cleaner.

### Inject Mocks using Mockito

Our class has some dependencies like repositories, We do as below:

* Create Mock of the dependency
* Create The current Class instance and annotate it with `@InjectMocks`.

This will inject all mocks into the current class instance.

#### mocking example

```java
package com.dogigiri.onepiece.model.service;

import com.dogigiri.onepiece.model.entity.Crew;
import com.dogigiri.onepiece.model.entity.Pirate;
import com.dogigiri.onepiece.model.entity.enumeration.Gender;
import com.dogigiri.onepiece.model.entity.enumeration.Race;
import com.dogigiri.onepiece.model.entity.enumeration.Rank;
import com.dogigiri.onepiece.model.repository.PirateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class PirateRegularServiceTest {
    Pirate pirate;
    @Mock
    PirateRepository repository;

    @Mock
    Crew crew;

    @InjectMocks
    PirateRegularService service;

    @BeforeEach
    void setUp() {
        pirate = new Pirate().setFullName("Monkey D Luffy").setNickname("Straw hat").setAge(24).setGender(Gender.MALE)
                .setRace(Race.HUMAN).setRank(Rank.CAPTAIN).setBounty(1_500_000_000L)
                .setOccupation("Captain of Straw hat pirates").setCrew(crew);
    }

    @Test
    @DisplayName("store pirate entity test")
    public void storeTest() {
        Mockito.when(repository.save(pirate)).thenReturn((Pirate) pirate.setId(1L).setUuid(UUID.randomUUID()));
        service.store(pirate);
        assertThat(pirate.getId()).isEqualTo(1L);
    }
}
```

### Verify mocks Execution

Sometimes our functions have some functionality like calling a certain method, We can verify this behavior by mockito
verify() method. Verify is usually redundant but, it is necessary on some purposes.

verify() method accepts two arguments:

* mock object
* time

Then we chain the verify method with the function call. this will make sure how many times the method is invoked.

`verify(mockObject, times(2)).delete()`

instead of times we also can call methods:

* atLeastOnce()
* atMost()
* never()

### Returning values from Mocks

In order to do that we call when() and pass the mock object with the proper method call and chain then() and give the
type it should return.

`when(repository.findById(1L)).thenReturn(ReturnType)`
Then we call the function above, and we can assert test the return type.

### Argument Matcher

This feature increases flexibility for method stubbing. It's useful when we are not interested in the values of
arguments or when we want to define a return value for range of arguments.
There are some methods like any(), anyLong(), etc. That we can use in our verify statement.

`Mockito.when(repository.save(any(Pirate.class))).thenReturn((Pirate) pirate.setId(1L).setUuid(UUID.randomUUID()));`

`verify(mockObject, times(2)).delete(any(InputType.class))`

> When we are mocking an object which takes several arguments we can use argument matchers for all of them, It is
> essential to use argument matcher for all arguments or if we want to specify one of them precisely we can use
> `eq()` and pass the value to it otherwise test will fail.

## Behavior Driven Mockito

![BDD_MOCKITO](../../../pics/bddmock1.png)
![BDD_MOCKITO](../../../pics/bddmock2.png)
![BDD_MOCKITO](../../../pics/bddmock3.png)

We just replace (when thenReturn) with (given willReturn) and verify methods with (then should).

```text
// given
Model model=new Model();
        given(Repository.function()).willReturn(Optional.of(Model));

// when
        var foo=service.function();

// then
// assertion test
        then(repository).should().function();
        then(repository).shouldHaveNoMoreInteractions();
```

## Advanced Mockito

### Throw Exception

in order to assert that the object handles exception correctly we can use mockito `thenThrow` & `willThrow()`
& `doThrow()` functions.
in regular or BDD way.

> We use thenThrow when we have return type and method explicitly throws exception. For void, we use doThrow() method.

### Filter using lambda

In our given statement, we can use `argThat()` function and pass lambda which can filter the input argument of the
function we are trying to call.

### Argument Capture

In order to use argument capture we can create a field annotated with `@Captor` or create a local variable.
`final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class)` and we pass it as an argument then
we can assert it. Argument capture is a kind of special argument matcher. If we have multiple times of invocation on
mock we can use capture.getAllValues() to get a list of captures and assert them.

```java

@ExtendWith(MockitoExtension.class)
class PirateRegularServiceTest {
    Pirate pirate;
    @Mock
    PirateRepository repository;

    @Mock
    Crew crew;

    @InjectMocks
    PirateRegularService service;

    @BeforeEach
    void setUp() {
        pirate = new Pirate().setFullName("Monkey D Luffy").setNickname("Straw hat").setAge(24).setGender(Gender.MALE)
                .setRace(Race.HUMAN).setRank(Rank.CAPTAIN).setBounty(1_500_000_000L)
                .setOccupation("Captain of Straw hat pirates").setCrew(crew);
    }

    @Test
    @DisplayName("store pirate entity test")
    public void storeTest() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(pirate).setFullName(captor.capture());
    }
}
```

### Partial Mocks(Spies)

We only use spies on classes which we know they will never change. We just annotate them with `@Spy`. Stubbing
spies with when() doesn't work. We should use doReturn() method instead.

### Testing time

First mockito can not help us test static methods we can use powermock framework. in case of time we can use
Clock class and pass it to our LocalDateTime.now() cases. Then we can test it successfully.

### Mockito Answers

> NOt GOOD EXPLANATION, GOTTA LEARN Later!
