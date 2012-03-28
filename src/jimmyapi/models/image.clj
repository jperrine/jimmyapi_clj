(ns jimmyapi.models.image
  (:require [clojure.java.jdbc :as sql]))

(defn all []
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      ["select * from images order by id desc"]
      (into [] results))))

(defn create [image]
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/insert-values :images (keys image) (vals image))))

(defn random []
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      ["select * from images order by random limit 1"]
      (into [] results))))

; (defn find-by-id [id]
;   (sql/with-connection (System/getenv "DATABASE_URL")
;     ))

; (defn add-usage [id]
;   (sql/with-connection (System/getenv "DATABASE_URL")
;     (let [image])
;     (sql/update-values :images
;                        ["id=?" id]
;                        {:usage }))