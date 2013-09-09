# GCal

Clojure interface to Google Calendar API

IN PROGRESS

## Getting started

### Auth

GCal requires an OAuth Token. The library provides some helpers for this.

Generate the OAuth URL

```clojure
(require [gcal.auth :as auth])

;; Get the URL to visit
(auth/offline-access-uri)

;; Visit the URL and grab the token from the callback URL

(generate-access-token YOURTOKEN)

;; => your access token

```

### Calendars

```clojure
(ns myapp.core
  (:use [gcal.calendars :as calendars]))
```

A list of all available calendars

```clojure
(calendars/all "OAUTH TOKEN")
```

Show a single calendar. Your calendar id will look something like "6nasdg9sebcv9oqt6qu8hcdgi8@group.calendar.google.com"

```clojure
(calendars/show "OAUTH TOKEN" "CALENDAR_ID")
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

Create a new quick add event

```clojure
(events/quick-add "OAUTH TOKEN" "CALENDAR_ID" "my awesome event")
```

## License

Copyright © 2013 Google Owain Lewis

Distributed under the Eclipse Public License, the same as Clojure.


