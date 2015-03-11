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
  (testing "The EDN datastore is to"
    (testing "receive legible input"
	  (is false))
    (testing "provide legible output"
      (let [{:keys [testuserid]} 
	            (read-string (slurp "test/mastery_timer/testdata.edn"))]
        (is (= 18 (:hours testuserid)))
        (is (= "Sunday, January 25, 2015" 
               (tf/unparse date-formatter 
                 (apply t/date-midnight (:start-date testuserid)))))))))