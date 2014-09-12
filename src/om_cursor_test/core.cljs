(ns om-cursor-test.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

; Vector of item-groups
(def app-state
  (atom [{
          :items [
                  {:text "Alice"}
                  {:text "Bob"}
                  {:text "Cindy"}
                  {:text "Dave"}
                  ]}
         {
          :items [
                  {:text "Eve"}
                  {:text "Fred"}
                  {:text "Gina"}
                  {:text "Howard"}
                  ]}]))

(defn item-view [item owner]
  (reify
    om/IRender
    (render [_]
            (dom/button nil
                        (:text item)))))


; Somehow, by going through this function, it displays
(defn item-list-view [items owner]
  (reify
    om/IRender
    (render [_]
            (apply dom/div nil
                   (om/build-all item-view items)))))


(defn items-view [item-group owner]
  (reify
    om/IRender
    (render [_]
            (dom/div nil

                     (dom/h3 nil "This one doesn't render a cursor involving nil. Instead we get some sort of OID.")
                     (om/build-all item-view (:items item-group)) ; seems like it should be identical to call in item-list-view

                     (dom/h3 nil "Yet this one renders with what seems like an identical code path!")
                     (om/build item-list-view (:items item-group))))))


(defn root-view [app-state owner]
  (reify
    om/IRender
    (render [_]
            (dom/div nil
                     (om/build items-view (get app-state nil)) ; here, we index into the vector of item-groups incorrectly, yet sometimes it renders
                     ))))


(om/root
 root-view
 app-state
 {:target (. js/document (getElementById "app"))})
