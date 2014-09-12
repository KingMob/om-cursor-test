(ns om-cursor-test.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]))

(enable-console-print!)

(def app-state
  (atom {
         :question "What is placed between the boat and the dock to cushion the hull?"
         :image nil
         :answers [
                   {:text "Tiller"
                    :correct false
                    :status :unchosen}
                   {:text "Boomvang"
                    :correct false
                    :status :unchosen}
                   {:text "Fender"
                    :correct true
                    :status :unchosen}
                   {:text "Gooseneck"
                    :correct false
                    :status :unchosen}
                   ]}))


(defn answer-view [answer owner]
  (reify
    om/IRenderState
    (render-state [_ {:keys [answer-chan]}]
                   (dom/button
                    #js{:onClick (fn [e] (put! answer-chan answer))}
                    (:text answer)))))


(defn question-view [quiz-question owner]
  (reify
    om/IInitState
    (init-state [_]
                {:answer-chan (chan)})

    om/IWillMount
    (will-mount [_]
                (let [answer-chan (om/get-state owner :answer-chan)]
                  (go (loop []
                        (let [answer-chosen (<! answer-chan)]
                          (.log js/console (str "Chose " (:text @answer-chosen)))
;;                           (.log js/console (:correct @answer-chosen))
                          (om/transact! answer-chosen [:text] #(str % " *"))
                          (recur))))))

    om/IRenderState
    (render-state [_ {:keys [answer-chan]}]
                  (apply dom/div nil
                         (om/build-all answer-view (:answers quiz-question)
                                       {:init-state {:answer-chan answer-chan}})))))




(om/root
 question-view
 app-state
 {:target (. js/document (getElementById "app"))})
