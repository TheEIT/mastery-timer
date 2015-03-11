(ns mastery-timer.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
	        [clj-time.core :as t]
            [clj-time.format :as tf]
			[mastery-timer.core :refer :all]
            [mastery-timer.data :as data]))
  
(defn splash []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (pr-str 
           (str "At this rate, you will log your 10,000th hour on " 
                (tf/unparse date-formatter 
				  (mastery-date data/my-start (:hours data/my-hours)))))})

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
