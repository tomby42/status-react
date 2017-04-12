(ns status-im.network-settings.screens.network-details
  (:require-macros [status-im.utils.views :refer [defview]])
  (:require
    [re-frame.core :refer [dispatch]]
    [status-im.components.context-menu :refer [context-menu]]
    [status-im.components.text-input-with-label.view :refer [text-input-with-label]]
    [status-im.network-settings.screen :refer [network-badge]]
    [status-im.components.react :refer [view text text-input icon]]
    [status-im.components.sticky-button :refer [sticky-button]]
    [status-im.i18n :as i18n]
    [clojure.string :as str]))

(def options
  [{:text (i18n/label :t/add-json-file)      :value #(dispatch [:network-add-json-file])}
   {:text (i18n/label :t/paste-json-as-text) :value #(dispatch [:network-paste-json-as-text])}
   {:text (i18n/label :t/:edit-rpc-url)      :value #(dispatch [:network-edit-rpc-url])}
   {:text (i18n/label :t/:remove-network)    :value #(dispatch [:network-remove])}])

(defview network-details []
  [{:keys [name connected?] :as network} [:current-network]]
  [view
   [network-badge {:name       name
                   :connected? connected?
                   :options    options}]
   (when-not connected?
     [view
      [view
       [text (i18n/label :t/connect)]]
      [text "description"]])
   [view {:height 160}
    [text "json"]]
   [context-menu
    [view
     [text (i18n/label :t/edit-network-config)]]
    options]
   [text "description"]])