(ns gcal.auth
  (:require [gcal.core :as core]
            [clj-oauth2.client :as oauth2]))

(def login-uri "https://accounts.google.com")

(def calendar-scope "https://www.googleapis.com/auth/calendar")

(def calendar-read-only "https://www.googleapis.com/auth/calendar.readonly")

(def google-oauth2
  {:authorization-uri (str login-uri "/o/oauth2/auth")
   :access-token-uri (str login-uri "/o/oauth2/token")
   :redirect-uri "https://localhost/oauth2callback"
   :client-id "645463994659.apps.googleusercontent.com"
   :client-secret "yn4R7dz9PrPvRYHFzN84Lg81"
   :access-query-param :access_token
   :scope [calendar-scope]
   :grant-type "authorization_code"})

(def auth-req (oauth2/make-auth-request google-oauth2))

(defn auth-uri []
  (:uri auth-req))

(defn offline-access-uri []
  (str (auth-uri) "&access_type=offline&approval_prompt=force"))

(def auth-resp {:code "4/B_Q4tydr2mWUmqOzy2IO69lKiQ7e.osPq936dhw4fOl05ti8ZT3YxH_ZrewI"})

(defn access-token []
  (:access-token
    (oauth2/get-access-token google-oauth2 auth-resp auth-req)))

