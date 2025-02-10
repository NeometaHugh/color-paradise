(ns color-paradise.core
  (:import (javax.swing JFrame JPanel JButton JLabel Box BoxLayout Timer)
           (java.awt.event ActionListener)
           (java.awt Color GridLayout BorderLayout))
  (:require [color-paradise.game :as game]
            [color-paradise.views :as v])
  (:gen-class))
(declare welcome-view)
#_(
(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
)
#_(#(+ %1 %2))
(defn game-view
  "create game view"
  [frame]
  (let [difficulty (atom 0.8)
        score-label (JLabel. "Score:0")
        time-label (JLabel. "Time:15s")
        bts-panel (JPanel.)
        buttons (for [i (range 16)] 
                  (for [j (range 16)]
                  (JButton. "")))
        score (atom 0)
        time (ref 15)]
    (v/decorate score-label time-label)
    #_(def timer (Timer. 1000
                  (reify ActionListener
                    (actionPerformed [this e]
                      (if (> @time 0)
                        (do
                          #_(swap! time #(- %1 1))
                          (dosync
                           (ref-set time (- @time 1)))
                          (.setText time-label (str "Time:" @time "s")))
                        (if (v/msg "would u like to restart" "Time Out!" frame)
                          (game-view frame)
                          (do (.removeAll (.getContentPane frame))
                              (.revalidate (.getContentPane frame))
                              (.repaint (.getContentPane frame))
                              (.stop timer)
                              (.setVisible frame false)
                              (welcome-view frame))))))))
    #_(.start timer)
    (do
      (def color-ch (fn []
                      (let
                       [colors (game/color-block @difficulty)
                        [r g b a nr ng nb na x y] colors
                        common (reify ActionListener
                                 (actionPerformed [this e]
                                   (if (not (v/msg "would u like to restart?" "Wrong Color!" frame))
                                     (do (.removeAll (.getContentPane frame))
                                         (.revalidate (.getContentPane frame))
                                         (.repaint (.getContentPane frame))
                                         #_(.stop timer)
                                         (.setVisible frame false)
                                         (welcome-view frame))
                                     (game-view frame))))
                        special-bt (nth (nth buttons x) y)]
                        (do
                          #_(mapv (fn [button]
                                  (doto (button)
                                    (.setBackground (.Color (nth colors 0) (nth colors 1) (nth colors 2) (nth colors 3)))
                                    (.setFocusPainted false)
                                    (.setBorderPainted false)
                                    (.addActionListener common))
                                  button)
                                (flatten buttons))
                          (def special (reify ActionListener
                                    (actionPerformed [this e]
                                      (do (swap! score #(+ %1 1))
                                          (.setText score-label (str "Score:" @score))
                                          #_(swap! time #(do (+ %1 (- 15 %1))))
                                          (dosync
                                           (ref-set time (+ @time 15)))
                                          (doseq [row buttons
                                                  button row]
                                            (.removeActionListener button common))
                                          (.removeActionListener (nth (nth buttons x) y) special)
                                          (if (< (+ @difficulty 0.01) 1.0)
                                            (do (swap! difficulty #(+ %1 0.01))
                                                (color-ch))
                                            (if (v/msg "would u like to restart?" "SUCCESS!" frame)
                                              (game-view frame)
                                              (do (.removeAll (.getContentPane frame))
                                                  (.revalidate (.getContentPane frame))
                                                  (.repaint (.getContentPane frame))
                                                  #_(.stop timer)
                                                  (.setVisible frame false)
                                                  (welcome-view frame))))))))
                          #_(println (int r) (int g) (int b) (int a) (int nr) (int ng) (int nb) (int na))
                          (doseq [row buttons
                                  button row]
                            (.setContentAreaFilled button true)
                            (.setOpaque button true)
                            (.setFocusable button true)
                            (.setFocusTraversalKeysEnabled button false)
                            (.setBackground button (Color. (int r) (int g) (int b) (int a)))
                            (.setFocusPainted button false)
                            (.setBorderPainted button false)
                            (.addActionListener button common))
                          (.setBackground special-bt (Color. (int nr) (int ng) (int nb) (int na) ))
                          (.removeActionListener special-bt common)
                          (.addActionListener special-bt special)))))
      (color-ch)
      (.setLayout bts-panel (GridLayout. 16 16 5 5))
      (doseq [row buttons
              button row]
        (.add bts-panel button))
      (doto (.getContentPane frame)
        (.removeAll)
        (.setLayout (BorderLayout.))
        (.add score-label BorderLayout/NORTH)
        (.add bts-panel BorderLayout/CENTER)
        (.add time-label BorderLayout/SOUTH))
      (.setVisible frame true)
      )))

(defn welcome-view
  "create welcome-view"
  [frame]
  (let [label (JLabel. "COLOR PARADISE")
        start-button (JButton. "Start")
        exit-button (JButton. "Exit")]
    (do
      (v/decorate label start-button exit-button)
      (v/decorate-bts start-button exit-button)
      (.addActionListener start-button
                          (reify ActionListener
                            (actionPerformed [this e]
                              (.setVisible frame false)
                              (game-view frame))))
      (.addActionListener exit-button 
                          (reify ActionListener 
                            (actionPerformed [this e]
                              (System/exit 0))))
      (.setDefaultCloseOperation frame JFrame/EXIT_ON_CLOSE)
      (.setLayout frame (BoxLayout. (.getContentPane frame) BoxLayout/Y_AXIS))
      (v/expand-add (.getContentPane frame) label start-button exit-button)
      (.setBackground frame Color/black)
      (.setBackground (.getContentPane frame) Color/black)
      (.setSize frame 600 600)
      #_(.setLocation frame 300 300)
      (.setLocationRelativeTo frame nil)
      (.setVisible frame true)
      )))

(defn -main 
  "main"
  []
  (welcome-view (JFrame. "Color Paradise v0.1.1")))