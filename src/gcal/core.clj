(ns gcal.core
  (:require [cheshire.core :as json]
            [http.async.client :as http]))

(def base-url "https://www.googleapis.com/calendar/v3")

;; TODO get this from oauth2

(def auth-token "ya29.AHES6ZTpWdjyp4uci_XtFyjaGrq96a1h_R2XNOLXRFYuvHU")

(defn merge-auth [headers token]
  (assoc headers :Authorization (str "Bearer " token)))

(defn make-url [url] 
  (apply format "%s%s" [base-url url]))

;; "https://www.googleapis.com/calendar/v3/users/me/calendarList"

;; Abstract the duplication
(defmacro do-request [url & params]
  `(let [http-client# (http/create-client)]
   ))

(defn get-request [url token & params]
  (let [http-client (http/create-client)]
    (with-open [client http-client]
      (let [default-headers {}
            response (http/GET client url :headers (merge-auth default-headers auth-token))]
    (-> response
        http/await
        http/string)))))

(defn post-request [url token params]
  (with-open [client (http/create-client)]
    (let [default-headers {}
          response (http/POST client url 
            :body params 
            :headers (merge-auth default-headers auth-token))]
   (-> response
       http/await
       http/string))))

(defn as-json [response]
  (json/parse-string response))

;; Abstractions for simplicity 

(defn simple-request [url token]
  (let [full-url (make-url url)]
    (as-json (get-request url token))))

(defn simple-post [url token params]
  (let [full-url (make-url url)]
    (post-request full-url token params)))


