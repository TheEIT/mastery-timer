(ns mastery-timer.core
  (:require [clj-time.core :as t]
            [clj-time.format :as tf]
            [mastery-timer.data :as data])
  (:gen-class))
  
(def date-formatter (tf/formatter "EEEE, MMMM dd, YYYY"))
  
(defn mastery-date 
      [start-date hours-logged]
	  (let [days-elapsed (t/in-days (t/interval start-date 
	                                            (t/today-at-midnight)))]
        (t/plus start-date (t/days (/ 10000 (/ hours-logged days-elapsed))))))

(defn -main
  "How far have we come?"
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (println "At this rate, you will log your 10,000th hour on"
    (tf/unparse date-formatter (mastery-date data/my-start (:hours data/my-hours)))))
