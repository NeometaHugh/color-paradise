(ns color-paradise.game
  (:gen-class))

(defn color-block
  "create new color view"
  [difficulty]
  (let [r (rand-int 255.0)
        g (rand-int 255.0)
        b (rand-int 255.0)
        a (rand-int 255.0)
        x (rand-int 16.0)
        y (rand-int 16.0)
        delta (* (- 1.0 difficulty) 255.0)
        f (fn [color]
            (if (> (+ color delta) 255.0)
              (- color delta)
              (+ color delta)))]
    [r g b a (f r) (f g) (f b) (f a) x y]))