(ns gcal.events
  (:use [gcal.core :as api]))

(defn all
  "Returns a list of all events"
  [token id]
  (api/simple-request (format "/calendars/%s/events" (java.net.URLDecoder/decode id)) token))

;; (defn quick-add [calendar]
;;   ;; https://www.googleapis.com/calendar/v3/calendars/calendarId/events/quickAdd
;;   (let [t "Appointment at Somewhere on Februray 29th 10am-10:25am"]
;;     (post-request purl)))