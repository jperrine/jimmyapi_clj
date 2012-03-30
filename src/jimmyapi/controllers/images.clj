(ns jimmyapi.controllers.images
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [clojure.string :as str]
            [ring.util.response :as ring]
            [jimmyapi.views.images :as view]
            [jimmyapi.models.image :as model]))

(defn index []
  (view/index (model/all)))

(defn new []
  (view/new))

(defn create [params]
  (let [image (:image params)]
    (when-not (str/blank? (:url image))
      (model/create image)))
  (ring/redirect "/"))

(defn show [id]
  (view/show (model/find-by-id id)))

(defn random []
  (view/show (model/random)))

(defroutes routes
  (GET "/" [] (random))
  (GET  "/images" [] (index))
  (GET  "/images/new" [] (new))
  (POST "/images" {params :params} (create params))
  (GET  "/images/:id" [id] (show id)))