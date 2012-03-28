(ns jimmyapi.models.migration
  (:require [clojure.java.jdbc :as sql]))

(defn create-images []
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/create-table :images
                      [:id :serial "PRIMARY KEY"]
                      [:url :varchar "NOT NULL"]
                      [:uses :integer "NULL" "DEFAULT 0"]
                      [:updated_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]
                      [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])))

(defn -main []
  (print "Creating database structure...") (flush)
  (create-images)
  (println " done"))