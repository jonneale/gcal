(ns gcal.core
  (:require [cheshire.core :as json]
            [http.async.client :as http]))

(def base-url "https://www.googleapis.com/calendar/v3")

;; TODO get this from oauth2

(def auth-token "ya29.AHES6ZRc3lAIpVhwrA4skfT8p66khzqicayRXDj-2ICwNFtbOIXr9w")

(defn make-url [url] 
  (apply format "%s%s" base-url url))

(defn get-request []
  (with-open [client (http/create-client)]
    (let [response (http/GET client "https://www.googleapis.com/calendar/v3/users/me/calendarList"
                   :headers
                     {:Authorization (str "Bearer " auth-token)})]
    (-> response
      http/await
      http/string))))

;; List of all calendars 

(defn calendar-list []
  (let [url (make-url "/users/me/calendarList")]
    (json/parse-string
      (get-request))))

;; Get a single calendar 

;; Test cal
(def idx "6nasdg9sebcv9oqt6qu8hcdgi8@group.calendar.google.com")

;; Events 

;; https://www.googleapis.com/calendar/v3/calendars/calendarId/events

(defn events
  "Returns a list of all events"
 [id]
  (let [url (make-url (format "/users/me/calendars/%s/events" id))]
    (json/parse-string
      (get-request))))

    



