(ns jimmyapi.views.images
  (:use [hiccup.core :only [html]]
        [hiccup.page-helpers :only [doctype]]
        [hiccup.form-helpers :only [form-to label text-field submit-button]])
  (:require [jimmyapi.views.layout :as layout]))

(defn image-form []
  [:div {:id "image-form" :class ""}
    (form-to [:post "/images"]
             (label "image[url]" "Url")
             (text-field "image[url]")
             (submit-button "Save"))])

(defn display-image [image]
  [:a {:href "/"} [:img {:src (image :url)}]])

(defn image-row [image]
  [:tr
    [:td (:url image)]
    [:td (:uses image)]
    [:td [:a {:href (str "/images/" (:id image))} "Show"]]])

(defn images-table [images]
  [:table
    [:tr
      [:th "Url"]
      [:th "Uses"]
      [:th]]
    (map #'image-row images)])

(defn index [images]
  (layout/layout "Jimmy API"
                 [:h1 "Listing Images"]
                 (images-table images)))

(defn show [image]
  (layout/layout "Jimmy API"
                 (display-image image)))

(defn new-image []
  (layout/layout "Jimmy API" (image-form)))