(ns status-im.network-settings.screen
  (:require-macros [status-im.utils.views :refer [defview]])
  (:require [status-im.utils.listview :as lw]
            [re-frame.core :refer [dispatch]]
            [status-im.components.status-bar :refer [status-bar]]
            [status-im.components.toolbar-new.view :refer [toolbar]]
            [status-im.components.action-button.action-button :refer [action-button-view]]
            [status-im.components.action-button.styles :refer [actions-list]]
            [status-im.components.react :refer [view text icon
                                                list-view
                                                list-item]]
            [status-im.components.context-menu :refer [context-menu]]
            [status-im.components.common.common :as common]
            [status-im.components.renderers.renderers :as renderers]
            [status-im.network-settings.styles :as st]
            [status-im.i18n :as i18n]))

(defn network-icon [connected? size]
  [view {:width size :height size :border-radius (/ size 2) :background-color (if connected? "#729ae6" "#eef2f5")
         :align-items :center :justify-content :center}
   [icon (if connected? :network_white :network_gray)]])

(defn network-badge [& [{:keys [name connected? options]}]]
  [view {:height 88
         :padding-left 16
         :flex-direction :row
         :align-items :center}
   [network-icon connected? 56]
   [view {:padding-left 16}
    [text {:style st/badge-name-text}
     (or name (i18n/label :t/new-network))]]])

(defn actions-view []
  [view actions-list
   [context-menu
    [action-button-view (i18n/label :t/add-new-network) :add_blue]
    [{:text (i18n/label :t/add-json-file)      :value #(dispatch [:navigate-to :paste-json-text])}
     {:text (i18n/label :t/paste-json-as-text) :value #(dispatch [:navigate-to :paste-json-text])}
     {:text (i18n/label :t/specify-rpc-url)    :value #(dispatch [:navigate-to :add-rpc-url])}]]])

(defn render-row [{:keys [name connected?] :as row} _ _]
  (list-item
    ^{:key row}
    [view {:height 64
           :flex-direction :row
           :background-color :white
           :align-items :center
           :padding-horizontal 16}
     [network-icon connected? 40]
     [view {:padding-horizontal 16}
      [text {:style {:font-size 17 :letter-spacing -0.2 :line-height 20}} name]
      (when connected?
        [text {:margin-top 6
               :style {:font-size 14 :letter-spacing -0.2 :color "#939ba1"}} (i18n/label :t/connected)])]
     [icon :right_arrow]]))

(defview network-settings []
  (let [networks [{:name "Mainnet" :connected? false} {:name "Ropsten" :connected? true}]]
    [view {:flex 1}
     [status-bar]
     [toolbar {:title (i18n/label :t/network-settings)}]
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
                  :style                     st/networks-list}]]]))