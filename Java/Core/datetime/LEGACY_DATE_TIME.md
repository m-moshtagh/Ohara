# Date and time using java:

Java has various classes for date and time. some are now old because java 8 provided a simple API for Date and Time in
java.time package which is really fantastic.

> Java reserves time in milliseconds. this long number represents 1 jan 1970(epoch day) till today. Also, java follows
> the starting calendar as 1900. We can see it by System.currentTimeMillis();

## Conversion from milliseconds

* toSecond -> /1000
* toMinute -> /60
* toHour -> /60
* toDay -> /24
* toYear -> /365

## Old java.util.Date class:

> Date date = new Date();

This object contains both date and time. because this works on the milliseconds. We can pass String strings in date
format to the constructor.
This class also has multiple get methods like getDate(), getDay() which returns day of the week.(it starts from 0).

> to make getYear() work we need to sum it with 1900.

## Calendar class:

This is an abstract class in java because we have lots of calendars around the world.
We use Gregorian Calendar which implements Calendar. We have several methods:

* isLeapYear()
* get(): this method accepts some const parameters from Calendar class like MONTH and ... in here enumerations starts
  from 1

> There are also a bunch of set methods. this class is mutable.

## Time zones:

We have TimeZone class but, we can't initialize it, but we can get it from gregorian Calendar. We can set timeZone with
a Timezone ID.

## Joda Date and Time API:

The reason this API was introduced was that the legacy class was mutable, contained both date and time and was in millis
but the new one contains LocalDate, LocalTime and, LocalDateTime which are in seconds and nanoseconds.

> LocalDate date = LocalDate.now();

* we also have a Clock class which we can pass to now() method.
  We can also pass zone id to now() method.

*: there is of() method which accepts Enums and integer values.

*: we have parse() method which we can pass String formatted like "year-day-month"

## Date & Time classes:

Timezone information is not available in LocalDateTime class. so, for including time zone we have a class called

* ZonedDateTime: this class has timezone information.
* OffsetDateTime: This class won't have zoneId information and, will only contain offset information.
* OffsetTime: This class will only contain offset + time information.
* ZoneId: We can specify the ID of a particular zone.
* MonthDay: this class contains only information of Month & Day.
* YearMonth: Contains Year and Month information.
* Year: only contains Year information.
* Period: This represents a period of time in terms of number of dates. so we can say the difference of two dates using
  this class.
* Duration: This represents a duration of time in terms of number of times.
* Instant: This class stores information of instant of time. this is for capturing a moment. It will be in GMT time. So
  it means if we create two instant objects in the same time in two different places, they will have same values.

## DateFormatter:

This class is here to specify format of Date & Time.

* y -> year
* M -> month
* d -> day
* h -> hour 0 - 12
* H -> hour 0 - 23
* m -> minute
* s -> second
* S -> millisecond's
* z -> time zone
* Z -> offset
  *: This class has some ISO_FORMATS which come handy but, we can define our own format using ofPattern() method which
  accepts a String.
  *: For applying format we need to call format() method and pass the LocalDateTime class.

## ChronoField:

LocalDateTime has a get method which we can pass ChronoField.ANY_CONSTANT and get the value we want. this can't be used
for timezone information.