(defproject om-cursor-test "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
;;                  [org.clojure/clojurescript "0.0-2322"]
                 [org.clojure/clojurescript "0.0-2311"]
                 [org.clojure/core.async "0.1.338.0-5c5012-alpha"]
;;                  [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
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
