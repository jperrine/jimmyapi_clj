(ns jimmyapi.core
  (:use [compojure.core :only [defroutes]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as ring]
            [jimmyapi.controllers.images]
            [jimmyapi.views.layout :as layout]))

(defroutes routes
  jimmyapi.controllers.images/routes
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (handler/site routes))

(defn start [port]
  (ring/run-jetty #'routes {:port (or port 3000) :join? false}))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (start port)))