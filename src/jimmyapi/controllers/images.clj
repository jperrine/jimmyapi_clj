(ns jimmyapi.controllers.images
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [clojure.string :as str]
            [ring.util.response :as ring]
            [jimmyapi.views.shouts :as view]
            [jimmyapi.models.shout :as model]))