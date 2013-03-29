# GCal

Clojure interface to Google Calendar API

IN PROGRESS

## Getting started

### Auth

GCal requires an OAuth Token

### Calendars

All calendar related code is found in the gcal.calendars namespace

```clojure
(ns myapp.core (:use [gcal.core :as gcal]))
```

A list of all available calendars

```clojure
(calendars "OAUTH TOKEN")
```

Show a single calendar. Your calendar id will look something like "6nasdg9sebcv9oqt6qu8hcdgi8@group.calendar.google.com"

```clojure
(calendars "OAUTH TOKEN" "CALENDAR_ID")
```

### Events

```clojure
(ns myapp.core
  (:use [gcal.events :as events]))
```

A list of all events for a given calendar (TODO date range queries)

```clojure
(events/all "OAUTH TOKEN" "CALENDAR_ID")
```

## License

Copyright © 2013 Google Owain Lewis

Distributed under the Eclipse Public License, the same as Clojure.


