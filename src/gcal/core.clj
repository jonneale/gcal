(ns gcal.core
  (:import [com.google.api.client.json JsonFactory]
           [com.google.api.client.json.jackson2 JacksonFactory]
           [com.google.api.services.calendar Calendar]))
  
(def GOOGLE_API_KEY "KEY")

(def net-transport (NetHttpTransport.))
(def json-factory (JacksonFactory.))

(def cal (Calendar. net-transport json-factory nil))



