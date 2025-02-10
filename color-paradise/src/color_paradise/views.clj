(ns color-paradise.views
  (:import (javax.swing JOptionPane)
           (java.awt Font Color))
  (:gen-class))

(defn decorate
  "decorate component"
  [& components]
  (doseq [component components]
    (.setFont component (Font. "Monospace" Font/BOLD 24))
    (.setBackground component (Color. 100 100 100 100))
    (.setForeground component (Color. 255 255 255 255))))

(defn decorate-bts
  "decorate buttons"
  [& components]
  (doseq [component components]
    (.setFocusPainted component false)
    (.setBorderPainted component false)))

(defn expand-add
  "add more component"
  [father & components]
  (doseq [component components]
    (.add father component)))
(defn msg
  "send a msg"
  [title msg frame]
  #_(JOptionPane/showMessageDialog
   frame
   msg
   title
   JOptionPane/INFO_MESSAGE)
  (let [result (JOptionPane/showConfirmDialog frame msg title JOptionPane/YES_NO_OPTION)]
    (= result JOptionPane/YES_OPTION)))

