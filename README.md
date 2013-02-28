# GCal

Clojure interface to Google Calendar API

IN PROGRESS

## Usage

### Auth 

TODO

Google Calendar uses OAuth2. 

### Calendars

A list of all available calendars 

```clojure
(ns myapp.core
  (:use [gcal.core :as gcal]))
  
;; A list of all calendars 
(gcal/calendar-list "AUTH_TOKEN")

```

### Events 

A list of all events for a given calendar (TODO date range queries)

```clojure
(ns myapp.core
  (:use [gcal.events :as events]))

;; all events for a calendar with id 6nasdg9sebcv9oqt6qu8hcdgi8@group.calendar.google.com

(events/all "6nasdg9sebcv9oqt6qu8hcdgi8@group.calendar.google.com")

```

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.


