# GCal

Clojure interface to Google Calendar API

IN PROGRESS

## Getting started

### Auth

GCal requires an OAuth Token

### Calendars

```clojure
(ns myapp.core
  (:use [gcal.calendars :as calendars]))
```

A list of all available calendars

```clojure
(calendars/calendars "OAUTH TOKEN")
```

Show a single calendar. Your calendar id will look something like "6nasdg9sebcv9oqt6qu8hcdgi8@group.calendar.google.com"

```clojure
(calendars/calendars "OAUTH TOKEN" "CALENDAR_ID")
```

### Events

```clojure
(ns myapp.core
  (:use [gcal.events :as events]))
```

A list of all events for a given calendar

```clojure
(events/all "OAUTH TOKEN" "CALENDAR_ID")
```

## License

Copyright © 2013 Google Owain Lewis

Distributed under the Eclipse Public License, the same as Clojure.


