(ns status-im.network-config.screens.add-rpc-url
  (:require-macros [status-im.utils.views :refer [defview]])
  (:require
    [re-frame.core :refer [dispatch]]
    [status-im.components.text-input-with-label.view :refer [text-input-with-label]]
    [status-im.network-config.screen :refer [network-badge]]
    [status-im.components.react :refer [view text text-input icon]]
    [status-im.components.sticky-button :refer [sticky-button]]
    [status-im.i18n :as i18n]
    [clojure.string :as str]))

(defview add-rpc-url []
  [rpc-url [:network-rpc-url]]
  [view
   [network-badge]
   [text-input-with-label {:label (i18n/label :t/rpc-url)}]
   [view {:flex 1}]
   (when (not (str/blank? rpc-url))
     [sticky-button (i18n/label :t/add-network) #()])])
