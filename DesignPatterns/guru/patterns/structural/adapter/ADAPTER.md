# Adapter Design pattern

This design patterns allows different objects of incompatible interfaces collaborate.

## Problem

We have a source of data with specific format like XML, The destination of the data is a library which only understands
Data in JSON format. Here we create an interface called Adapter which makes the XML data understandable to second
library. This is like power plug adapters base on countries.

## Solution

We can use composition principle to implement adapter pattern:

* Make sure that you have at least two classes with incompatible interfaces:
    * A useful service class, which you can’t change (often 3rd- party, legacy or with lots of existing dependencies).
    * One or several client classes that would benefit from using the service class.
* Declare the client interface and describe how clients communicate with the service.
* Create the adapter class and make it follow the client interface. Leave all the methods empty for now.
* Add a field to the adapter class to store a reference to the service object. The common practice is to initialize this
  field via the constructor, but sometimes it’s more convenient to pass it to the adapter when calling its methods.
* One by one, implement all methods of the client interface in the adapter class. The adapter should delegate most of
  the real work to the service object, handling only the interface or data format conversion.
* Clients should use the adapter via the client interface. This will let you change or extend the adapters without
  affecting the client code.

## Implementation

### Client

```java
public class IranAirLine {
    private final TimeLineAdapter timeLineAdapter;

    public IranAirLine(TimeLineAdapter timeLineAdapter) {
        this.timeLineAdapter = timeLineAdapter;
    }

    public void showAmericanTakeOffTime() {
        System.out.println(timeLineAdapter.getTakeOffTime());
    }
}
```

### Adapter

```java
import java.time.ZonedDateTime;

public interface TimeLineAdapter {
    ZonedDateTime getTakeOffTime();
}
```

### Adapter Implementation

```java
import java.time.ZonedDateTime;

public class AmericanTimeLineAdapter implements TimeLineAdapter {
    private final AmericanAirLine americanAirLine;

    public AmericanTimeLineAdapter(AmericanAirLine americanAirLine) {
        this.americanAirLine = americanAirLine;
    }

    @Override
    public ZonedDateTime getTakeOffTime() {
        return convertTimeLine();
    }

    private ZonedDateTime convertTimeLine() {
        var americanTime = Instant.from(americanAirLine.takeOffTime());
        return ZonedDateTime.ofInstant(americanTime, ZoneId.systemDefault());
    }
}

```

### Service(Adaptee) class

```java
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AmericanAirLine {
    // This clas can be 3rd party class
    public ZonedDateTime takeOffTime() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(now);
        return now;
    }
}
```

### Test

```java
import org.junit.jupiter.api.Test;

class IranAirLineTest {

    @Test
    void showAmericanTakeOffTime() {
        IranAirLine iranAirLine = new IranAirLine(new AmericanTimeLineAdapter(new AmericanAirLine()));
        iranAirLine.showAmericanTakeOffTime();
    }
}
```
