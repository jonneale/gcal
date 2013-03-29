(ns gcal.events
  (:use [gcal.core :as api]))

(defn all
  "Returns a list of all events"
  [token id]
  (api/simple-request
    (format "/calendars/%s/events"
      (java.net.URLDecoder/decode id)) token))

