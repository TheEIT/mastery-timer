(defproject mastery-timer "1.0.0-SNAPSHOT"
  :description "Mastery requires 10,000 hours of deliberate practice. When will your hour strike?"
  :url "https://immense-oasis-9808.herokuapp.com"
  :license {:name "Eclipse Public License v1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [environ "0.5.0"]
                 [joda-time "2.6"]
                 [clj-time "0.9.0"]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.2.1"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "clojure-getting-started-standalone.jar"
  :profiles {:production {:env {:production true}}})
  :main mastery-timer.core)
