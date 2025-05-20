# Time Utilities

This package provides helper classes for dealing with time strings, formatting and time zones.

## Key classes

- **TimeFormStd** – common fixed width formats such as `yyyyMMddHHmmss`.
- **TimeFormPretty** – user friendly formats like `yyyy-MM-dd hh:mm:ss.SSS`.
- **ITimeForm** – interface implemented by the format enums.
- **TimeForm** – wraps a time string with its format and provides conversion helpers.
- **TimeStringCal** – simple calculator that performs arithmetic on time strings.
- **TimeFieldType** – enum describing the field (year, month, second, ... ) to adjust.
- **TimeZoneType** – list of frequently used time zone identifiers.
- **Timezone** – wrapper class around a `ZoneId` value.

## Example

```java
// Create a TimeStringCal from a string
TimeStringCal time = new TimeStringCal("20240101123000");

// Add one day
TimeStringCal nextDay = time.next(TimeFieldType.DATE);
System.out.println(nextDay.getTime()); // 20240102123000

// Compare two times in minutes
TimeStringCal other = new TimeStringCal("20240101120000");
long diff = time.compareTime(other, TimeFieldType.MINUTE.getChronoUnit());
System.out.println(diff); // 30
```
