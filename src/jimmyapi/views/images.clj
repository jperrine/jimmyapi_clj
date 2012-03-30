(ns jimmyapi.views.images
  (:use [hiccup.core :only [html]]
        [hiccup.page-helpers :only [doctype]]
        [hiccup.form-helpers :only [form-to label text-field submit-button]])
  (:require [jimmyapi.views.layout :as layout]))

(defn image-form []
  [:div {:id "image-form" :class ""}
    (form-to [:post "/images"]
             (label "url" "Url")
             (text-field "url")
             (submit-button "Save"))])

(defn display-image [image]
  [:a {:href "/random"} [:img {:src (image :url)}]])

(defn display-images [images]
  [:div {:id "images"}
    (map #'display-image images)])

(defn index [images]
  (layout/layout "Jimmy API"
                 (display-images)
                 [:div {:class "clear"}]))

(defn show [image]
  (layout/layout "Jimmy API"
                 (display-image image)))

(defn new []
  (layout/layout "Jimmy API" (image-form)))