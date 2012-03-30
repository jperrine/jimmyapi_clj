(ns jimmyapi.controllers.images
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [clojure.string :as str]
            [ring.util.response :as ring]
            [jimmyapi.views.images :as view]
            [jimmyapi.models.image :as model]))

(defn index []
  (view/index (model/all)))

(defn new-image []
  (view/new-image))

(defn create [params]
  (let [image (:image params)]
    (when-not (str/blank? (:url image))
      (model/create image)))
  (ring/redirect "/"))

(defn show [id]
  (let [image (model/find-by-id id)]
    (model/add-usage image)
    (view/show image)))

(defn random []
  (let [image (model/random)]
    (model/add-usage image)
    (view/show image)))

(defn destroy [id]
  (model/destroy id)
  (ring/redirect "/images"))

(defroutes routes
  (GET  "/" [] (random))
  (GET  "/images" [] (index))
  (GET  "/images/new" [] (new-image))
  (POST "/images" {params :params} (create params))
  (GET  "/images/:id" [id] (show id))
  (POST "/images/:id/destroy" [id] (destroy id)))