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