(ns gcal.core
  (:require [cheshire.core :as json]
            [http.async.client :as http]))

(def base-url "https://www.googleapis.com/calendar/v3")

(def auth-token "")

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

