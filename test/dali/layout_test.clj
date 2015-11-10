(ns dali.layout-test
  (:require [dali.layout :refer :all]
            [clojure.test :refer :all]))

(def index-tree @#'dali.layout/index-tree)
(def assoc-in-tree @#'dali.layout/assoc-in-tree)
(def get-in-tree @#'dali.layout/get-in-tree)
(def update-in-tree @#'dali.layout/update-in-tree)

(deftest test-index-tree
  (is (= {:tag :svg
          :attrs {:dali/path [0]}
          :content
          [{:tag :g
            :attrs {:dali/path [0 0]}
            :content
            [{:tag :circle :attrs {:cx 1 :cy 30 :r 20 :dali/path [0 0 0]}}
             {:tag :circle :attrs {:cx 2 :cy 30 :r 20 :dali/path [0 0 1]}}
             {:tag :circle :attrs {:cx 3 :cy 30 :r 20 :dali/path [0 0 2]}}
             {:tag :circle :attrs {:cx 4 :cy 30 :r 20 :dali/path [0 0 3]}}]}
           {:tag :g
            :attrs {:dali/path [0 1]}
            :content
            [{:tag :circle :attrs {:cx 5 :cy 30 :r 20 :dali/path [0 1 0]}}
             {:tag :circle :attrs {:cx 6 :cy 30 :r 20 :dali/path [0 1 1]}}
             {:tag :circle :attrs {:cx 7 :cy 30 :r 20 :dali/path [0 1 2]}}
             {:tag :circle :attrs {:cx 8 :cy 30 :r 20 :dali/path [0 1 3]}}]}]}
         (index-tree
          {:tag :svg
           :content
           [{:tag :g
             :content
             [{:tag :circle :attrs {:cx 1 :cy 30 :r 20}}
              {:tag :circle :attrs {:cx 2 :cy 30 :r 20}}
              {:tag :circle :attrs {:cx 3 :cy 30 :r 20}}
              {:tag :circle :attrs {:cx 4 :cy 30 :r 20}}]}
            {:tag :g
             :content
             [{:tag :circle :attrs {:cx 5 :cy 30 :r 20}}
              {:tag :circle :attrs {:cx 6 :cy 30 :r 20}}
              {:tag :circle :attrs {:cx 7 :cy 30 :r 20}}
              {:tag :circle :attrs {:cx 8 :cy 30 :r 20}}]}]}))))

(let [tree
      {:tag :svg
       :attrs {:dali/path [0]}
       :content
       [{:tag :g
         :attrs {:dali/path [0 0]}
         :content
         [{:tag :circle :attrs {:cx 1 :cy 30 :r 20 :dali/path [0 0 0]}}
          {:tag :circle :attrs {:cx 2 :cy 30 :r 20 :dali/path [0 0 1]}}
          {:tag :circle :attrs {:cx 3 :cy 30 :r 20 :dali/path [0 0 2]}}
          {:tag :circle :attrs {:cx 4 :cy 30 :r 20 :dali/path [0 0 3]}}]}
        {:tag :g
         :attrs {:dali/path [0 1]}
         :content
         [{:tag :circle :attrs {:cx 5 :cy 30 :r 20 :dali/path [0 1 0]}}
          {:tag :circle :attrs {:cx 6 :cy 30 :r 20 :dali/path [0 1 1]}}
          {:tag :circle :attrs {:cx 7 :cy 30 :r 20 :dali/path [0 1 2]}}
          {:tag :circle :attrs {:cx 8 :cy 30 :r 20 :dali/path [0 1 3]}}]}]}]

  (deftest test-get-in-tree
    (is (= {:tag :circle :attrs {:cx 1 :cy 30 :r 20 :dali/path [0 0 0]}}
           (get-in-tree tree [0 0 0])))
    (is (= {:tag :circle :attrs {:cx 4 :cy 30 :r 20 :dali/path [0 0 3]}}
           (get-in-tree tree [0 0 3])))
    (is (= {:tag :circle :attrs {:cx 7 :cy 30 :r 20 :dali/path [0 1 2]}}
           (get-in-tree tree [0 1 2]))))
  
  (deftest test-assoc-in-tree
    (is (= {:tag :svg
            :attrs {:dali/path [0]}
            :content
            [{:tag :g
              :attrs {:dali/path [0 0]}
              :content
              [{:tag :circle :attrs {:cx 1 :cy 30 :r 20 :dali/path [0 0 0]}}
               {:tag :circle :attrs {:cx 2 :cy 30 :r 20 :dali/path [0 0 1]}}
               :HERE
               {:tag :circle :attrs {:cx 4 :cy 30 :r 20 :dali/path [0 0 3]}}]}
             {:tag :g
              :attrs {:dali/path [0 1]}
              :content
              [{:tag :circle :attrs {:cx 5 :cy 30 :r 20 :dali/path [0 1 0]}}
               {:tag :circle :attrs {:cx 6 :cy 30 :r 20 :dali/path [0 1 1]}}
               {:tag :circle :attrs {:cx 7 :cy 30 :r 20 :dali/path [0 1 2]}}
               {:tag :circle :attrs {:cx 8 :cy 30 :r 20 :dali/path [0 1 3]}}]}]}
           (assoc-in-tree tree [0 0 2] :HERE))))

  (deftest test-update-in-tree
    (is (= {:tag :svg
            :attrs {:dali/path [0]}
            :content
            [{:tag :g
              :attrs {:dali/path [0 0]}
              :content
              [{:tag :circle :attrs {:cx 1 :cy 30 :r 20 :dali/path [0 0 0]}}
               {:tag :circle :attrs {:cx 2 :cy 30 :r 20 :dali/path [0 0 1]}}
               {:tag :circle :attrs {:cx 3 :cy 30 :r 20 :dali/path [0 0 2]}}
               {:tag :circle :attrs {:cx 4 :cy 30 :r 20 :dali/path [0 0 3]}}]}
             {:tag :g
              :attrs {:dali/path [0 1]}
              :content
              [{:tag :circle :attrs {:cx 5 :cy 30 :r 20 :dali/path [0 1 0]}}
               {:tag :circle}
               {:tag :circle :attrs {:cx 7 :cy 30 :r 20 :dali/path [0 1 2]}}
               {:tag :circle :attrs {:cx 8 :cy 30 :r 20 :dali/path [0 1 3]}}]}]}
           (update-in-tree tree [0 1 1] dissoc :attrs)))))