(ns cheffey.server
  (:require [reitit.ring :as ring]
            [ring.adapter.jetty :as jetty]
            [integrant.core :as ig]
            [environ.core :as environ]))

(defn app [env] 
  (ring/ring-handler
    (ring/router
      [["/" {:get {:handler (fn [req] {:status 200
                                       :body "Hello Reitit"})}}]])))

(defmethod ig/init-key :server/jetty
  [_ {:keys [handler port]}]
  (jetty/run-jetty handler {:port port :join? false})
  (prn (str "\nServer running on port " port)))

(defmethod ig/prep-key :server/jetty
  [_ config]
  (merge config {:port (Integer/parseInt (environ/env :port))}))

(defmethod ig/init-key :cheffey/app
  [_ config]
  (prn "Started app")
  (app config))

(defmethod ig/init-key :db/postgres
  [_ config]
  (prn "Configured db")
  (:jdbc-url config))

(defmethod ig/halt-key! :server/jetty
  [_ jetty]
  (.stop jetty))

(defn -main [config-file]
  (let [config (-> config-file slurp ig/read-string)]
    (-> config ig/prep ig/init)))

(comment
  (app {:request-method :get
        :uri "/"})
  (-main))

