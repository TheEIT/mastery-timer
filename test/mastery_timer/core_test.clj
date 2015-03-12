(ns mastery-timer.core-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as t]
            [clj-time.format :as tf]
            [mastery-timer.core :refer :all]))

(deftest date-computation-test
  (testing "The computed date is to be correct"
    (is (= 10000 (mastery-date (t/today-at-midnight) 1)
                 (t/plus (t/today-at-midnight) (t/days 10000))))))

(deftest edn-datastore-test
  (testing "Data added to the datastore should be accurately retrievable"
    (spit "test/mastery_timer/testdata.edn" {})
	(let [test-params 
	      {:testuserid {:name "Test User" :hours 18 :start-date [2015 1 25]}}]
	  (spit "test/mastery_timer/testdata.edn" (core-input test-params)))
    (let [{:keys [testuserid]} 
	      (read-string (slurp "test/mastery_timer/testdata.edn"))]
      (is (= 18 (:hours testuserid)))
      (is (= "Sunday, January 25, 2015" 
             (tf/unparse date-formatter 
			             (apply t/date-midnight (:start-date testuserid))))))))