;Attempt 1
;(planner world-simple '(on prisoner H) planner-operations-prisoner-simple)
;Attempt 2
;(planner world-original-mk2 '(escaped prisoner true) planner-ops-rewrite)

(def world-simple
  '#{
     (connects A B) (connects B A) (watched A false)
     (connects B C) (connects C B) (watched B false)
     (connects C E) (connects E C) (watched C false)
     (connects E H) (connects H E) (watched D false)
     (connects H G) (connects G H) (watched E false)
     (connects G F) (connects F G) (watched F false)
     (connects F D) (connects D F) (watched G false)
     (connects D A) (connects A D) (watched H false)
     (on prisoner A)
     })

(def planner-operations-prisoner-simple

  '{
    :move-to-tile
    {:name move-to-tile
     :achieves (on prisoner ?desti)
     :when ((connects ?desti ?source) (on prisoner ?location) (watched ?source false)
             (watched ?desti false) (:guard (correction1  (? source) (? desti))))
     :post ((on prisoner ?source))
     :pre ((on prisoner ?source))
     :add ((on prisoner ?desti))
     :del ((on prisoner ?source))
     :txt (prisoner moved from ?source to ?desti)
     :cmds []
     }
    }
  )

;Comment out to run Tests
(def planner-operations-prisoner-fakes

  '{
    :fake1
    {:name fake1
     :achieves (some fake tuple)
     :when ((connects ?desti ?source) (connects ?source ?desti) (on prisoner ?location) (watched ?desti false) (:guard (correction  (? source) (? desti))))
     :post ((on prisoner ?source))
     :pre ((on prisoner ?source))
     :add ((on prisoner ?desti))
     :del ((on prisoner ?source))
     :txt (prisoner moved from ?source to ?desti)
     :cmds []
     }
    :fake2
    {:name fake2
     :achieves (some fake tuple)
     :when ((connects ?desti ?source) (connects ?source ?desti) (on prisoner ?location) (watched ?desti false) (:guard (correction  (? source) (? desti))))
     :post ((on prisoner ?source))
     :pre ((on prisoner ?source))
     :add ((on prisoner ?desti))
     :del ((on prisoner ?source))
     :txt (prisoner moved from ?source to ?desti)
     :cmds []
     }
    :fake3
    {:name fake3
     :achieves (some fake tuple)
     :when ((connects ?desti ?source) (connects ?source ?desti) (on prisoner ?location) (watched ?desti false) (:guard (correction  (? source) (? desti))))
     :post ((on prisoner ?source))
     :pre ((on prisoner ?source))
     :add ((on prisoner ?desti))
     :del ((on prisoner ?source))
     :txt (prisoner moved from ?source to ?desti)
     :cmds []
     }
    :fake4
    {:name fake4
     :achieves (some fake tuple)
     :when ((connects ?desti ?source) (connects ?source ?desti) (on prisoner ?location) (watched ?desti false) (:guard (correction  (? source) (? desti))))
     :post ((on prisoner ?source))
     :pre ((on prisoner ?source))
     :add ((on prisoner ?desti))
     :del ((on prisoner ?source))
     :txt (prisoner moved from ?source to ?desti)
     :cmds []
     }
    :fake5
    {:name fake5
     :achieves (some fake tuple)
     :when ((connects ?desti ?source) (connects ?source ?desti) (on prisoner ?location) (watched ?desti false) (:guard (correction  (? source) (? desti))))
     :post ((on prisoner ?source))
     :pre ((on prisoner ?source))
     :add ((on prisoner ?desti))
     :del ((on prisoner ?source))
     :txt (prisoner moved from ?source to ?desti)
     :cmds []
     }
    :move-to-tile
    {:name move-to-tile
     :achieves (on prisoner ?desti)
     :when ((connects ?desti ?source) (connects ?source ?desti) (on prisoner ?location) (watched ?desti false) (:guard (correction  (? source) (? desti))))
     :post ((on prisoner ?source))
     :pre ((on prisoner ?source))
     :add ((on prisoner ?desti))
     :del ((on prisoner ?source))
     :txt (prisoner moved from ?source to ?desti)
     :cmds []
     }
    }
  )

(def ops-search-simple-operators-fakes
  '{
    fake1 {
           :pre ((connects ?tilea ?tileb))
           :add ((some fake tuple))
           :del ((on prisoner ?tileb))
           :txt (fake tuple)
           :cmd []
           }
    fake2 {
           :pre ((connects ?tilea ?tileb))
           :add ((some fake tuple))
           :del ((on prisoner ?tileb))
           :txt (fake tuple)
           :cmd []
           }
    fake3 {
           :pre ((connects ?tilea ?tileb))
           :add ((some fake tuple))
           :del ((on prisoner ?tileb))
           :txt (fake tuple)
           :cmd []
           }
    fake4 {
           :pre ((connects ?tilea ?tileb))
           :add ((some fake tuple))
           :del ((on prisoner ?tileb))
           :txt (fake tuple)
           :cmd []
           }
    fake5 {
           :pre ((connects ?tilea ?tileb))
           :add ((some fake tuple))
           :del ((on prisoner ?tileb))
           :txt (fake tuple)
           :cmd []
           }
    move-to-tile {:pre ((on prisoner ?tileb) (connects ?tilea ?tileb))
                  :add ((on prisoner ?tilea))
                  :del ((on prisoner ?tileb))
                  :txt (prisoner moved from ?tileb to ?tilea)
                  :cmd []
                  }
    }
  )