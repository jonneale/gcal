(ns gcal.events)

;; https://www.googleapis.com/calendar/v3/calendars/calendarId/events

(defn all
  "Returns a list of all events"
 [id]
 (let [url (make-url (format "/users/me/calendars/%s/events" id))]
   (json/parse-string (get-request))))

(def purl "https://www.googleapis.com/calendar/v3/calendars/6nasdg9sebcv9oqt6qu8hcdgi8%40group.calendar.google.com/events/quickAdd?text=Appointment+at+Somewhere+on+Februray+29th+11am-11%3A25am")

(defn quick-add [calendar]
  ;; https://www.googleapis.com/calendar/v3/calendars/calendarId/events/quickAdd
  (let [t "Appointment at Somewhere on Februray 29th 10am-10:25am"]
    (post-request purl)))