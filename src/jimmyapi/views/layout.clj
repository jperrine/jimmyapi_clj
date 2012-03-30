(ns jimmyapi.views.layout
  (:use [hiccup.core]
        [hiccup.page-helpers])
  (:require [jimmyapi.views.utils :as utils]))

(defn layout-head [title]
  [:head
   [:title title]
   (utils/include-less "/css/lib/bootstrap.less")
   (utils/include-js "/js/less-1.2.0.min.js")
   (utils/inline-css "body { padding-top: 60px; }")])

(defn layout [title body]
  (html
    (layout-head title)
    [:body
     [:div.container body]
     [:footer.footer
      [:div.container
       [:p.pull-right
        [:a {:href "#"} "Back to top"]]
       [:p "Built with Twitter Bootstrap"]]]]))