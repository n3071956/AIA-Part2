;Attempt 1
;(planner world-simple '(on prisoner H) planner-operations-prisoner-simple)
;Attempt 2
;(planner world-original-mk2 '(escaped prisoner true) planner-ops-rewrite)

(def world-original-mk2
  '#{
     (connects j7 c2)
     (connects c1 j2)
     (connects c2 j7)
     (connects c2 j1)
     (connects j1 c2)
     (connects j7 c1)
     (connects c1 j7)
     (connects j1 c4)
     (connects c4 j1)
     (connects c4 j3)
     (connects c3 j2)
     (connects j2 c3)
     (connects j2 c1)
     (connects c3 j3)
     (connects j3 c3)
     (connects j3 c4)
     (connects j3 c5)
     (connects j3 c6)
     (connects c5 j3)
     (connects c5 j4)
     (connects j4 c5)
     (connects j4 c7)
     (connects c7 j4)
     (connects c7 j6)
     (connects j6 c7)
     (connects j6 c8)
     (connects c8 j6)
     (connects c8 j5)
     (connects j5 c8)
     (connects j5 c6)
     (connects c6 j5)
     (connects c6 j3)

     (connects j6 c)
     (connects c j6)

     (is j7 exit)
     (is c locked)

     (at guard1 j1)
     (facing guard1 c4)
     (has guard1 key)

     (watched c1 false)
     (watched c2 false)
     (watched c3 false)
     (watched c4 false)
     (watched c5 false)
     (watched c6 false)
     (watched c7 false)
     (watched c8 false)

     (watched j1 false)
     (watched j2 false)
     (watched j3 false)
     (watched j4 false)
     (watched j5 false)
     (watched j6 false)
     (watched j7 false)

     (at guard2 j4)
     (facing guard2 c5)

     (locked door false)
     (on prisoner c)
     ;(caged prisoner true)
     (escaped prisoner false)


     })

(def world-simple
  '#{
     (connects A B) (connects B A) (watched A false)
     (connects B C) (connects C B) (watched B true)
     (connects C E) (connects E C) (watched C false)
     (connects E H) (connects H E) (watched D false)
     (connects H G) (connects G H) (watched E false)
     (connects G F) (connects F G) (watched F false)
     (connects F D) (connects D F) (watched G false)
     (connects D A) (connects A D) (watched H false)
     (on prisoner H)
     })


(defn in?
  [collection element]
  (some #(= element %) collection))

(def previous (atom (java.util.concurrent.LinkedBlockingDeque.)))

(defn adder [source]
  (.add @previous source) true)

(defn correction [source desti]
  (println "Checking : " source " to " desti)
  (if (nil? (in? (into '() @previous) (list desti source)) )
    (do (.push @previous (list source desti)) true)
    (do () false)))

(def planner-operations-prisoner-simple

  '{
    :move-to-tile
    {:name move-to-tile
     :achieves (on prisoner ?desti)
     :when ((connects ?desti ?source) (on prisoner ?location) (watched ?source false) (watched ?desti false) (:guard (correction  (? source) (? desti))))
     :post ((on prisoner ?source))
     :pre ((on prisoner ?source))
     :add ((on prisoner ?desti))
     :del ((on prisoner ?source))
     :txt (prisoner moved from ?source to ?desti)
     :cmds []
     }
    }
  )

(def planner-ops-rewrite
  '{
    :leave-cell
    {:name     leave-cell
     :achieves (on prisoner ?desti)
     :when ((connects ?desti c))
     :post ((is c unlocked))
     ;;Same as ops
     :pre ((on prisoner c)
            (is c unlocked)
            (connects ?desti c)
            )
     :add ((on prisoner ?desti))
     :del ((at prisoner c))
     :txt (leave cell)
     :cmds [leave-cell]
     }

    :move-to-tile
    {:name move-to-tile
     :achieves (on prisoner ?desti)
     :when ((connects ?desti ?source) (on prisoner ?location) (watched ?source false) (watched ?desti false) (:guard (correction  (? source) (? desti))))
     :post ((on prisoner ?source))
     :pre ((on prisoner ?source))
     :add ((on prisoner ?desti))
     :del ((on prisoner ?source))
     :txt (prisoner moved from ?source to ?desti)
     :cmds []
     }

    :unlock
    {:name     unlock
     :achieves (is c unlocked)                              ;unlocked cell
     :when ((is c locked))
     :post ()
     ;;Same as ops
     :pre ((at prisoner c)
            (is c locked)
            )
     :add ((is c unlocked))
     :del ((is c locked))
     :txt (unlocked cell)
     :cmds [unlock]
     }

    :get-key
    {:name     get-key
     :achieves (has prisoner key)                           ;failed
     :when ((at ?guard ?tile)
             (has ?guard key))
     :post ((on prisoner ?tile) (on prisoner ?tile))
     ;;Same as ops
     :pre ((on prisoner ?tile)
            (at ?guard ?tile)
            (has ?guard key)
            )
     :add ((has prisoner key))
     :del ((has ?guard key))
     :txt (found key at ?p1)
     :cmd [get-key]
     }

    ;:protect-key
    ;{ :name protect-key
    ; :achieves (has prisoner key)
    ; :add  ((has prisoner key)  )
    ; }

    :exit
    {:name     exit
     :achieves (escaped prisoner true)                      ;unlocks cell leave cell
     :when ((escaped prisoner false) (is ?tilea exit))
     :post ((on prisoner ?tilea) (has prisoner key))
     ;;Same as ops
     :pre (                                                 ;;add key
            (on prisoner ?tilea)
            (is ?tilea exit)
            (escaped prisoner false)
            )
     :add ((escaped prisoner true))
     :del ((escaped prisoner false))
     :txt (get plannered m8)
     :cmd [exit]
     }
    }


  )