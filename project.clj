(defproject gcal "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                [com.google.api-client/google-api-client "1.12.0-beta"]
                [com.google.http-client/google-http-client-jackson2 "1.12.0-beta"]
                [com.google.apis/google-api-services-calendar "v3-rev26-1.13.2-beta"]]
  :repositories {"google api services" "http://google-api-client-libraries.appspot.com/mavenrepo"})