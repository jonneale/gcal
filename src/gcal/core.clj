(ns gcal.core
  (:require [cheshire.core :as json]
            [http.async.client :as http]))

(def base-url "https://www.googleapis.com/calendar/v3")

;; TODO get this from oauth2

(def auth-token "ya29.AHES6ZRc3lAIpVhwrA4skfT8p66khzqicayRXDj-2ICwNFtbOIXr9w")

(defn make-url [url] 
  (apply format "%s%s" [base-url url]))

;; "https://www.googleapis.com/calendar/v3/users/me/calendarList"

(defn get-request [url & params]
  (let [http-client (http/create-client)]
    (with-open [client http-client]
      (let [response (http/GET client url
                       :headers 
                         {:Authorization (str "Bearer " auth-token)})]
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

    



