(ns status-im.network-settings.styles
  (:require-macros [status-im.utils.styles :refer [defstyle]])
  (:require [status-im.components.styles :as common]))

(def networks-list
  {:background-color common/color-light-gray})

(defstyle badge-name-text
  {:ios {:font-size 17
         :letter-spacing -0.2}})

(defstyle paste-json-text-input
  {:ios {:font-size 17
         :line-height 24
         :letter-spacing -0.2}})