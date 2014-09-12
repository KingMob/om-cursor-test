(defproject om-cursor-test "0.1.0-SNAPSHOT"
  :description "Om cursor test"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2322"]
;;                  [org.clojure/clojurescript "0.0-2311"]
                 [om "0.7.1"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]]

  :source-paths ["src"]

  :cljsbuild {
    :builds [{:id "om-cursor-test"
              :source-paths ["src"]
              :compiler {
                :output-to "om_cursor_test.js"
                :output-dir "out"
                :optimizations :none
                :source-map true}}]})
