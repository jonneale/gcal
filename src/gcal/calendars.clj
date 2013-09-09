(ns gcal.calendars
  (:use [gcal.core :as api]))

;; List of all calendars

(defn calendar
  "Fetch calendar information"
  ([token] (api/simple-request "/users/me/calendarList" token))
  ([token id] (api/simple-request (str "/calendars/" id) token)))

(defn all [token] (calendars token))

(defn show [token id] (calendars token id))

(defn calendar-list
  "Returns only the name and id"
  [token]
  (into {}
    (map #((juxt :description :id) %)
      (:items (calendar token)))))

(defn create-calendar
  [token summary]
  (api/simple-post "/calendar/v3/calendars" token {:summary summary}))

