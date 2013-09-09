(ns gcal.gcal
  (:refer-clojure :exclude [extend])
  (:use [gcal.core :as api]
        [clj-time.core :as t])
  (:require [gcal.auth :as auth]
            [cheshire.core :as json]
            [http.async.client :as http]))

;; IN PROGRESS REFACTOR

;; Requests
;; ****************************************************

(def base-url "https://www.googleapis.com/calendar/v3")

(def auth-token "ya29.AHES6ZQUKUdPdSXPGlBkgJydB5Puqglb8rP4jfDxfmrby4wPG0hnrA")

(defn decode [url]
  (java.net.URLDecoder/decode url))

(defn merge-auth [headers token]
  (assoc headers :Authorization (str "Bearer " token)))

(defn make-url [url]
  (apply format "%s%s" [base-url url]))

(defn with-keywords [m]
  (if (map? m)
    (into {}
      (for [[k v] m]
        [(keyword k)
         (if (and (vector? v)
                  (map? (first v)))
           (conj [] (with-keywords (first v)))
           v)]))
     m))

(defn get-> [url]
  (let [http-client (http/create-client :follow-redirects true)]
    (with-open [client http-client]
      (-> (http/GET client url) http/await http/string))))

(defn get-request [url token & params]
  (let [http-client (http/create-client)]
    (with-open [client http-client]
      (let [default-headers {}
            response (http/GET client url
                      :body params
                      :headers (merge-auth default-headers token))]
              (-> response
                  http/await
                  http/string)))))

(defn post-request [url token params]
  (with-open [client (http/create-client)]
    (let [default-headers {}
          response (http/POST client url
            :body params
            :headers (merge-auth default-headers token))]
     (-> response http/await http/string))))

(defn as-json [response]
  (json/parse-string response))

;; Abstractions for simplicity

(defn simple-request
  "Performs a simple get request
   returning a repsonse as JSON with
   keywords as keys"
  [url token & params]
  (let [full-url (make-url url)
        response (get-request full-url token (or (first params) {}))]
    (->> response
         as-json
         with-keywords)))

(defn simple-post [url token params]
  (let [full-url (make-url url)]
    (post-request full-url token params)))

;; List of all calendars
;; ****************************************************

(defn calendar
  "Fetch calendar information"
  ([token] (simple-request "/users/me/calendarList" token))
  ([token id] (simple-request (str "/calendars/" id) token)))

(defn all [token] (calendar token))

(defn show [token id] (calendar token id))

(defn calendar-list
  "Returns only the name and id"
  [token]
  (into {}
    (map #((juxt :description :id) %)
      (:items (calendar token)))))

(defn create-calendar
  [token summary]
  (simple-post "/calendar/v3/calendars" token {:summary summary}))

(def cal "6nasdg9sebcv9oqt6qu8hcdgi8@group.calendar.google.com")

(defn all
  "Returns a list of all events"
  [token cal]
  (simple-request
    (format "/calendars/%s/events"
      (decode cal)) token))

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
  (simple-request
    (format "/calendars/%s/events"
      (decode cal)) token {:timeMax (t/now)}))

(defn show
  [token cal event]
  (simple-request
    (format "/calendars/%s/events/%s"
      (decode cal) event) token))

;; Todays events

;; This weeks events
;; ****************************************************

(defn quick-add
  "Quick add an event to the calendar"
  [token cal text]
  (simple-post
    (format "/calendars/%s/events/quickAdd" cal) token {:text text}))

