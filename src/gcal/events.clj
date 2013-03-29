(ns gcal.events
  (:refer-clojure :exclude [extend])
  (:use [gcal.core :as api]
        [clj-time.core :as t]))

(def cal "6nasdg9sebcv9oqt6qu8hcdgi8@group.calendar.google.com")

(defn all
  "Returns a list of all events"
  [token cal]
  (api/simple-request
    (format "/calendars/%s/events"
      (api/decode cal)) token))

(defn items [result]
  (:items result))

(defn fetch-summary [items]
  (map :summary items))

(defn summary-of-events [token cal]
  ((comp fetch-summary items) (all token cal)))

(defn datetime-today-at
  "Create a datetime for today at a given hour and minute"
  [hour minute]
  (let [now (t/now)]
    ((juxt t/year t/month t/day) now)))

(defn todays-events [token cal]
  "Returns a list of all events"
  [token cal]
  (api/simple-request
    (format "/calendars/%s/events"
      (api/decode cal)) token {:timeMax (t/now)}))

(defn show
  [token cal event]
  (api/simple-request
    (format "/calendars/%s/events/%s"
      (api/decode cal) event) token))

;; Todays events

;; This weeks events


(defn quick-add
  "Quick add an event to the calendar"
  [token cal text]
  (api/simple-post
    (format "/calendars/%s/events/quickAdd" cal)
    token {:text text}))