(ns gcal.events)

;; https://www.googleapis.com/calendar/v3/calendars/calendarId/events

(defn all
  "Returns a list of all events"
 [id]
 (let [url (make-url (format "/users/me/calendars/%s/events" id))]
   (json/parse-string (get-request))))