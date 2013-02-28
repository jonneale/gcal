(ns gcal.auth
  (:require [clj-oauth2.client :as oauth2]))

(def login-uri "https://accounts.google.com")

(def calendar-scope "https://www.googleapis.com/auth/calendar")

(def calendar-read-only "https://www.googleapis.com/auth/calendar.readonly")

(def google-com-oauth2
  {:authorization-uri (str login-uri "/o/oauth2/auth")
   :access-token-uri (str login-uri "/o/oauth2/token")
   :redirect-uri "https://localhost/oauth2callback"
   :client-id ""
   :client-secret ""
   :access-query-param :access_token
   :scope [calendar-scope]
   :grant-type "authorization_code"
   :access-type "online"
   :approval_prompt ""})

(def auth-req
  (oauth2/make-auth-request google-com-oauth2))

(defn google-access-token [request]
  (oauth2/get-access-token google-com-oauth2 (:params request) auth-req))

