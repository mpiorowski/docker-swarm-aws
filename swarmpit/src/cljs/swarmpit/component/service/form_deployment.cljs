(ns swarmpit.component.service.form-deployment
  (:require [material.icon :as icon]
            [material.components :as comp]
            [material.component.form :as form]
            [swarmpit.component.state :as state]
            [swarmpit.component.service.form-deployment-placement :as placement]
            [swarmpit.component.parser :refer [parse-int]]
            [sablono.core :refer-macros [html]]
            [rum.core :as rum]))

(enable-console-print!)

(def form-value-cursor (conj state/form-value-cursor :deployment))

(defn- form-restart-policy-attempts [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Attempts"
     :type            "number"
     :key             "pattempts"
     :min             0
     :defaultValue    value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:restartPolicy :attempts] (parse-int (-> % .-target .-value)) form-value-cursor)}))

(defn- form-restart-policy-delay [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Delay"
     :type            "number"
     :key             "pdelay"
     :min             0
     :defaultValue    value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:restartPolicy :delay] (parse-int (-> % .-target .-value)) form-value-cursor)}))

(defn- form-restart-policy-window [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Window"
     :type            "number"
     :key             "pwindow"
     :min             0
     :defaultValue    value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:restartPolicy :window] (parse-int (-> % .-target .-value)) form-value-cursor)}))

(defn- form-restart-policy-condition [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Condition"
     :key             "pcond"
     :select          true
     :value           value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:restartPolicy :condition] (-> % .-target .-value) form-value-cursor)}
    (comp/menu-item
      {:key   "p-any"
       :value "any"} "any")
    (comp/menu-item
      {:key   "p-on-failure"
       :value "on-failure"} "on-failure")
    (comp/menu-item
      {:key   "p-none"
       :value "none"} "none")))

(defn- form-update-parallelism [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Parallelism"
     :type            "number"
     :key             "udparallel"
     :min             0
     :defaultValue    value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:update :parallelism] (parse-int (-> % .-target .-value)) form-value-cursor)}))

(defn- form-update-delay [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Delay"
     :type            "number"
     :key             "udelay"
     :min             0
     :defaultValue    value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:update :delay] (parse-int (-> % .-target .-value)) form-value-cursor)}))

(defn- form-update-order [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Order"
     :select          true
     :key             "uorder"
     :value           value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:update :order] (-> % .-target .-value) form-value-cursor)}
    (comp/menu-item
      {:key   "u-start-first"
       :value "start-first"} "start-first")
    (comp/menu-item
      {:key   "u-stop-first"
       :value "stop-first"} "stop-first")))

(defn- form-update-failure-action [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Failure action"
     :key             "ufailaction"
     :select          true
     :value           value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:update :failureAction] (-> % .-target .-value) form-value-cursor)}
    (comp/menu-item
      {:key   "upause"
       :value "pause"} "pause")
    (comp/menu-item
      {:key   "ucontinue"
       :value "continue"} "continue")
    (comp/menu-item
      {:key   "urollback"
       :value "rollback"} "rollback")))

(defn- form-rollback-parallelism [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Parallelism"
     :type            "number"
     :key             "rparallel"
     :min             0
     :defaultValue    value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:rollback :parallelism] (parse-int (-> % .-target .-value)) form-value-cursor)}))

(defn- form-rollback-delay [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Delay"
     :type            "number"
     :key             "rdelay"
     :min             0
     :defaultValue    value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:rollback :delay] (parse-int (-> % .-target .-value)) form-value-cursor)}))

(defn- form-rollback-order [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Order"
     :select          true
     :key             "rorder"
     :value           value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:rollback :order] (-> % .-target .-value) form-value-cursor)}
    (comp/menu-item
      {:key   "r-stop-first"
       :value "stop-first"} "stop-first")
    (comp/menu-item
      {:key   "r-start-first"
       :value "start-first"} "start-first")))

(defn- form-rollback-failure-action [value]
  (comp/text-field
    {:fullWidth       true
     :label           "Failure action"
     :key             "raction"
     :select          true
     :value           value
     :variant         "outlined"
     :margin          "normal"
     :style           {:maxWidth "150px"}
     :InputLabelProps {:shrink true}
     :onChange        #(state/update-value [:rollback :failureAction] (-> % .-target .-value) form-value-cursor)}
    (comp/menu-item
      {:key   "rpause"
       :value "pause"} "pause")
    (comp/menu-item
      {:key   "rcontinue"
       :value "continue"} "continue")))

(defn- form-autoredeploy [value]
  (comp/switch
    {:name     "autoredeploy"
     :key      "autoredeploy"
     :label    "Autoredeploy"
     :color    "primary"
     :value    (str value)
     :checked  value
     :onChange #(state/update-value [:autoredeploy] (-> % .-target .-checked) form-value-cursor)}))

(rum/defc form < rum/reactive []
  (let [{:keys [autoredeploy
                update
                rollback
                restartPolicy]} (state/react form-value-cursor)]
    (comp/grid
      {:container true
       :key       "sfdcg"
       :spacing   24}
      (comp/grid
        {:item true
         :key  "sfdcgig"
         :xs   12}
        (comp/form-control
          {:component "fieldset"
           :key       "sfdcgigc"}
          (comp/form-group
            {:key "sfdcgigcg"}
            (comp/form-control-label
              {:control (form-autoredeploy autoredeploy)
               :key     "sfdcgigcga"
               :label   "Autoredeploy"})))
        (placement/form))
      (comp/grid
        {:item true
         :key  "sfdcgip"
         :xs   12
         :sm   4}
        (form/subsection "Restart Policy")
        (comp/grid
          {:container true
           :key       "sfdcgipcg"
           :direction "column"}
          (comp/grid
            {:item true
             :key  "sfdcgipcgic"}
            (form-restart-policy-condition (:condition restartPolicy)))
          (comp/grid
            {:item true
             :key  "sfdcgipcgid"}
            (form-restart-policy-delay (:delay restartPolicy)))
          (comp/grid
            {:item true
             :key  "sfdcgipcgiw"}
            (form-restart-policy-window (:window restartPolicy)))
          (comp/grid
            {:item true
             :key  "sfdcgipcgia"}
            (form-restart-policy-attempts (:attempts restartPolicy)))))
      (comp/grid
        {:item true
         :key  "sfdcgiu"
         :xs   12
         :sm   4}
        (form/subsection "Update Config")
        (comp/grid
          {:container true
           :key       "sfdcgiucg"
           :direction "column"}
          (comp/grid
            {:item true
             :key  "sfdcgiucgip"}
            (form-update-parallelism (:parallelism update)))
          (comp/grid
            {:item true
             :key  "sfdcgiucgid"}
            (form-update-delay (:delay update)))
          (comp/grid
            {:item true
             :key  "sfdcgiucgio"}
            (form-update-order (:order update)))
          (comp/grid
            {:item true
             :key  "sfdcgiucgfa"}
            (form-update-failure-action (:failureAction update)))))
      (when (= "rollback" (:failureAction update))
        (comp/grid
          {:item true
           :key  "sfdcgir"
           :xs   12
           :sm   4}
          (form/subsection "Rollback Config")
          (comp/grid
            {:container true
             :key       "sfdcgircg"
             :direction "column"}
            (comp/grid
              {:item true
               :key  "sfdcgircgip"}
              (form-rollback-parallelism (:parallelism rollback)))
            (comp/grid
              {:item true
               :key  "sfdcgircgid"}
              (form-rollback-delay (:delay rollback)))
            (comp/grid
              {:item true
               :key  "sfdcgircgio"}
              (form-rollback-order (:order rollback)))
            (comp/grid
              {:item true
               :key  "sfdcgircgifa"}
              (form-rollback-failure-action (:failureAction rollback)))))))))