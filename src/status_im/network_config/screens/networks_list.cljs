(ns status-im.network-config.screens.networks-list
  (:require-macros [status-im.utils.views :refer [defview]])
  (:require [status-im.utils.listview :as lw]
            [re-frame.core :refer [dispatch]]
            [status-im.components.action-button.action-button :refer [action-button
                                                                      action-separator]]
            [status-im.components.action-button.styles :refer [actions-list]]
            [status-im.components.react :refer [view text icon
                                                list-view
                                                list-item]]
            [status-im.components.context-menu :refer [context-menu]]
            [status-im.components.common.common :as common]
            [status-im.components.renderers.renderers :as renderers]
            [status-im.network-config.styles :as st]
            [status-im.i18n :as i18n]))

(defn actions-view []
  [view actions-list
   [context-menu
    [action-button (i18n/label :t/add-new-network)
     :add_blue
     #()]
    [{:text (i18n/label :t/add-json-file)      :value #(dispatch [:network-add-json-file])}
     {:text (i18n/label :t/paste-json-as-text) :value #(dispatch [:network-paste-json-as-text])}
     {:text (i18n/label :t/specify-rpc-url)    :value #(dispatch [:network-specify-rpc-url])}]]])

(defn render-row [{:keys [name connected?] :as row} _ _]
  (list-item
    ^{:key row}
    [view {:height 64
           :flex-direction :row
           :justify-content :center
           :padding-horizontal 16}
     [view {:width 40 :height 40}]
     [view {:padding-horizontal 16}
      [text name]
      (when connected?
        [text (i18n/label :t/connected)])]
     [icon :right_arrow]]))

(defview networks-list []
  [networks [:get-networks]]
  [view {:flex 1}
   [list-view {:dataSource                (lw/to-datasource networks)
               :renderRow                 render-row
               :keyboardShouldPersistTaps true
               :renderHeader              #(list-item
                                             [view
                                              [actions-view]
                                              [common/bottom-shaddow]
                                              [common/form-title (i18n/label :t/existing-networks)
                                               {:count-value (count networks)}]
                                              [common/list-header]])
               :renderFooter              #(list-item [view
                                                       [common/list-footer]
                                                       [common/bottom-shaddow]])
               :renderSeparator           renderers/list-separator-renderer
               :style                     st/networks-list}]]
  [view
   [text "white sticky Done button"]])

