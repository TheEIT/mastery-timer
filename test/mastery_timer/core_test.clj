(ns mastery-timer.core-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as t]
            [clj-time.format :as tf]
            [mastery-timer.core :refer :all]))

(deftest date-computation-test
  (testing "that the computed date is correct"
    (is (= 10000 (mastery-date (t/today-at-midnight) 1)
                 (t/plus (t/today-at-midnight) (t/days 10000))))))
