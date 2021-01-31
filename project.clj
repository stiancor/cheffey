(defproject cheffey "0.1.0-SNAPSHOT"
  :description "Cheffey REST API"
  :url "http://api.learnreitit.com"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.2"]
                 [ring "1.8.1"]
                 [integrant "0.8.0"]
                 [environ "1.2.0"]
                 [metosin/reitit "0.5.5"]
                 [seancorfield/next.jdbc "1.1.582"]
                 [org.postgresql/postgresql "42.2.14"]
                 [clj-http "3.10.0"]
                 [ovotech/ring-jwt "1.3.0"]]
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["dev/src"]
                   :resource-paths ["dev/resources"]
                   :dependencies [[ring/ring-mock "0.4.0"]
                                  [integrant/repl "0.3.1"]]}}
  :uberjar-name "cheffey.jar")
