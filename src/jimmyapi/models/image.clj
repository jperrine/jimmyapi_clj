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
      ["select * from images order by random() limit 1"]
      (first (into [] results)))))

(defn add-usage [image]
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/update-values :images
                       ["id = ?" (:id image)]
                       {:uses (+ (:uses image) 1)})))

(defn find-by-id [id]
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      ["select * from images where id = ?" id]
      (first (into [] results)))))

(defn destroy [id]
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      ["delete from images where id = ?" id])))