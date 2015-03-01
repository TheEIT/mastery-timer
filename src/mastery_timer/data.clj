(ns mastery-timer.data
  (:require [clj-time.core :as t]
            [clj-time.format :as tf]))

(def my-start (t/date-midnight 2015 1 25))

(def my-hours {:hours 16})
