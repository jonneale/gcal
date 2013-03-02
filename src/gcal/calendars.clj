(ns gcal.calendars
  (:use [gcal.core :as api]))

;; List of all calendars 

(defn calendar-list 
  "Return a list of calendars"
  [token]
  (api/simple-request "/users/me/calendarList" token))

;; Get a single calendar 
;; GET https://www.googleapis.com/calendar/v3/calendars/calendarId

(def idx "6nasdg9sebcv9oqt6qu8hcdgi8@group.calendar.google.com")

(defn get-calendar 
  "Returns metadata for a calendar"
  [id token]
  (api/simple-request (str "/calendars/" id) token))

(defn create-calendar
  [summary token]
  (api/simple-post "/calendar/v3/calendars" token {:summary summary}))
  
