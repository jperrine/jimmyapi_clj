(ns jimmyapi.views.layout
  (:use [hiccup.core]
        [hiccup.page-helpers])
  (:require [jimmyapi.views.utils :as utils]))

(defn layout-head [title]
  [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1"}]
    [:title title]
    (include-css "/stylesheets/scaffold.css")])

(defn layout [title & body]
  (html
    (layout-head title)
    [:body
     [:div.container body]]))