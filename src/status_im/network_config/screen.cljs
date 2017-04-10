(ns status-im.network-config.screen
  (:require-macros [status-im.utils.views :refer [defview]])
  (:require [status-im.i18n :as i18n]
            [status-im.components.react :refer [view text icon]]))

(defn network-badge [& [{:keys [name connected? options]}]]
  [view {:height 88
         :padding-left 16
         :flex-direction :row
         :justify-content :center}
   [view {:width 56 :height 56}]
   [view {:padding-left 16}
    [text (or name (i18n/label :t/new-network))]]])

(defview network-config []
  [current-screen [:get-in [:network-config :current-screen]]]
  [])