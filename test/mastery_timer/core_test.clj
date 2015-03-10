(ns mastery-timer.core-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as t]
            [clj-time.format :as tf]
            [mastery-timer.core :refer :all]))

(deftest date-computation-test
  (testing "that the computed date is correct"
    (is (= 10000 (mastery-date (t/today-at-midnight) 1)
                 (t/plus (t/today-at-midnight) (t/days 10000))))))

(deftest edn-datastore-test
  (testing "that the EDN datastore"
    (testing "is receiving legible input" nil)
    (testing "provides legible output"
      (is (let [{:keys} (read-string (slurp "testdata.edn"))]
            (and (= 17 (:hours testuserid))
                 (= "January 25, 2015" 
                    (tf/unparse date-formatter 
                      (t/date-midnight 
                        (:start-date testuserid))))))))))