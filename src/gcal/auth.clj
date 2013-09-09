(ns gcal.auth
  (:require [gcal.core :as core]
            [clj-oauth2.client :as oauth2]))

;; OAuth authentication logic
;; ****************************************************

(def login-uri "https://accounts.google.com")

(def calendar-scope "https://www.googleapis.com/auth/calendar")

(def calendar-read-only "https://www.googleapis.com/auth/calendar.readonly")

;; ****************************************************

(def google-oauth2
  {:authorization-uri (str login-uri "/o/oauth2/auth")
   :access-token-uri (str login-uri "/o/oauth2/token")
   :redirect-uri "https://localhost/oauth2callback"
   :client-id "645463994659.apps.googleusercontent.com"
   :client-secret "yn4R7dz9PrPvRYHFzN84Lg81"
   :access-query-param :access_token
   :scope [calendar-scope]
   :grant-type "authorization_code"})

(def auth-req 
  (oauth2/make-auth-request google-oauth2))

(defn auth-uri [] (:uri auth-req))

(defn offline-access-uri []
  (let [extra-params "&access_type=offline&approval_prompt=force"]
    (str (auth-uri) extra-params)))

;; ****************************************************

;; Code is the long oauth return code obtained
;; from the redirect URL
;; e.g "4/A-VS2g35TU0DXsZEWZcO0VDkmMdj.IvpIekMQSO0fOl05ti8ZT3a9xKgEggI"
(defn generate-access-token [code]
  (let [token (oauth2/get-access-token 
                 google-oauth2 {:code code} auth-req)]
    (->> token 
        :access-token)))

