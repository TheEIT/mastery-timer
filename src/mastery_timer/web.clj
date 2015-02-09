(ns mastery-timer.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
	    [clj-time.core :as t]
            [clj-time.format :as tf]))
  
(def date-formatter (tf/formatters :rfc822))
  
(def my-start (t/date-midnight 2015 1 25))

(def my-hours 6)

(defn mastery-date [start-date hours-logged]
  (let [days-elapsed (t/in-days (t/interval start-date (t/today-at-midnight)))]
    (t/plus start-date (t/days (/ 10000 (/ hours-logged days-elapsed))))))

(defn splash []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (pr-str "At this rate, you will log your 10,000th hour on " 
                 (tf/unparse date-formatter (mastery-date my-start my-hours)))})

(defroutes app
  (GET "/" []
       (splash))
  (ANY "*" []
       (route/not-found (slurp (io/resource "404.html")))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
