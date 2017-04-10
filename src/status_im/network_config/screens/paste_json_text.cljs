(ns status-im.network-config.screens.paste-json-text
  (:require-macros [status-im.utils.views :refer [defview]])
  (:require
    [re-frame.core :refer [dispatch]]
    [status-im.network-config.screen :refer [network-badge]]
    [status-im.components.react :refer [view text text-input icon]]
    [status-im.components.sticky-button :refer [sticky-button]]
    [status-im.i18n :as i18n]
    [clojure.string :as str]))

(defview paste-json-text []
  [network-json [:network-json]
   error [:network-json-processing-error]]
  [view
   [network-badge]
   [view {:margin-top 16
          :flex 1}
    (when error
      [text {:color :red}
       (i18n/label :t/error-processing-json)])
    [text-input {:multiline true}]
    (when (not (str/blank? network-json))
      [sticky-button (i18n/label :t/process-json) #()])]])
