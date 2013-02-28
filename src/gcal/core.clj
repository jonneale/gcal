(ns gcal.core
  (:require [cheshire.core :as json]
            [http.async.client :as http]))

(def base-url "https://www.googleapis.com/calendar/v3")

;; TODO get this from oauth2

(def auth-token "ya29.AHES6ZTCzwafmaxC5yRpjXU17ZNC7mWizs-Hkc0gIj7Fa_D6DjLGhA")

(defn merge-auth [headers token]
  (assoc headers :Authorization (str "Bearer " token)))

(defn make-url [url] 
  (apply format "%s%s" [base-url url]))

;; "https://www.googleapis.com/calendar/v3/users/me/calendarList"

(defn get-request [url & params]
  (let [http-client (http/create-client)]
    (with-open [client http-client]
      (let [default-headers {}
            response (http/GET client url :headers (merge-auth default-headers auth-token))]
    (-> response
        http/await
        http/string)))))

;; List of all calendars 

(defn calendar-list [token]
  (let [url (make-url "/users/me/calendarList")]
    (json/parse-string
      (get-request url {:token token}))))

;; Get a single calendar 

(def idx "6nasdg9sebcv9oqt6qu8hcdgi8@group.calendar.google.com")

(defn events
  "Returns a list of all events"
 [id]
  (let [url (make-url (format "/users/me/calendars/%s/events" id))]
    (json/parse-string
      (get-request))))

    



