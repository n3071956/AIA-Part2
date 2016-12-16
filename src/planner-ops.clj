;; TEST CASES
;;(planner world2 '(escaped prisoner true) planner-operations-prisoner)
;;(planner world2 '(on prisoner exit) planner-operations-prisoner)
;;(planner world2 '(on prisoner j5) planner-operations-prisoner)
;;(planner world2 '(on prisoner c8) planner-operations-prisoner)
;;(planner world2 '(on prisoner c6) planner-operations-prisoner)
;;(planner world2 '(on prisoner c7) planner-operations-prisoner)

;;LOOPS!!!!!
;;(planner world2 '(on prisoner j4) planner-operations-prisoner)
;;(planner world2 '(on prisoner c5) planner-operations-prisoner)


;;----------------
;;Prisoner
;;----------------
;;--unlock
;;--leave-cell
;;--move
;;--hide
;;--got-key
;;--exit

(def planner-operations-prisoner-2

  '{

    ;:move-to-tile
    ;{:name move-to-tile
    ; :achieves (on prisoner ?tilea)     ;;Guard function defined above. - use https://www.scm.tees.ac.uk/isg/aia/student_papers/Healey_Milner_Percival-2.pdf (page 4-5)
    ; :when ( (:not (tried ?tilea ?tileb))(connects ?tilea ?tileb))  ;;
    ; :post ((tried ?tilea ?tileb)(on prisoner ?tileb) )                          ;
    ; ;;Same as ops
    ; :pre ((on prisoner ?tileb))
    ; :add ( (on prisoner ?tilea)(tried ?tileb ?tilea))
    ; :del ((on prisoner ?tileb))
    ; :txt (prisoner moved from ?tileb to ?tilea)
    ; :cmd []
    ; }

    :leave-cell
    {:name     leave-cell
     :achieves (on prisoner ?tilea)                      ;unlocked cell
     :when ((connects ?tilea c))
     :post ((is c unlocked))
     ;;Same as ops
     :pre ((on prisoner c)
            (is c unlocked)
            (connects ?tilea c)
            )
     :add ((on prisoner ?tilea))
     :del ((at prisoner c))
     :txt (leave cell)
     :cmd [leave-cell]
     }

    :move-to-tile
    {:name move-to-tile
     :achieves (on prisoner ?tilea)
     :when ((connects ?tilea ?tileb) (:not (tried ?tileb ?tilea)))
     :post ((tried ?tilea ?tileb)(on prisoner ?tileb))
     ;;Same as ops
     :pre ((on prisoner ?tileb))
     :add ((on prisoner ?tilea) )
     :del ((on prisoner ?tileb) (tried ?tilea ?tileb) )
     :txt (prisoner moved from ?tileb to ?tilea)
     :cmd [move-to-tile]
     }

    :attempted
    {:name attempted
     :achieves (tried ?tilea ?tileb)
     :when ((:not (tried ?tileb ?tilea)))
     :add ((tried ?tilea ?tileb))
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
     :cmd [unlock]
     }



    :get-key
    {:name     get-key
     :achieves (has prisoner key)                           ;failed
     :when ((on prisoner ?tile)
             (at ?guard ?tile)
             (has ?guard key))
     :post ((on prisoner ?tile))
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

    :protect-key
    { :name protect-key
     :achieves (has prisoner key)
     :add  ((has prisoner key)  )
     }

    :exit
    {:name     exit
     :achieves (escaped prisoner true)                      ;unlocks cell leave cell
     :when ((escaped prisoner false) (is ?tilea exit))
     :post ((on prisoner ?tilea))
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
    })

(def planner-operations-prisoner

  '{
    :unlock
    {:name     unlock
     :achieves (is c unlocked)
     :when     ((is c locked))
     :post     ()
     :pre      ((at prisoner c)
                 (is c locked)
                 )
     :add      ((is c unlocked))
     :del      ((is c locked))
     :txt      (unlocked cell)
     :cmd      (cell-unlocked -)
     }

    :leave-cell
    {:name     leave-cell
     :achieves (on prisoner ?p1)
     :when     ((at prisoner c)
                 (connects c ?p1))
     :post     ((is c unlocked))
     :pre      ((at prisoner c)
                 (is c unlocked)
                 (connects c ?p1)
                 )
     :add      ((on prisoner ?p1))
     :del      ((at prisoner c))
     :txt      (leave cell)
     :cmd      (leave cell -)
     }

    :move                                                   ;; multi move operator
    {:name     move
     :achieves (on prisoner ?p2)
     :when     ((connects ?p1 ?p2))
     :post     ((on prisoner ?p1))
     :pre      ((on prisoner ?p1)
                 (connects ?p1 ?p2))
     :del      ((on prisoner ?p1))
     :add      ((on prisoner ?p2))
     :cmd      (move-to ?p2 -)
     :txt      ((prisoner moved from ?p1 to ?p2))
     }

    :get-key
    {:name     get-key
     :achieves (has prisoner key)
     :when     ((at ?guard ?p1)
                 (has ?guard key))
     :post     ((on prisoner ?p1))
     :pre      ((on prisoner ?p1)
                 (at ?guard ?p1)
                 (has ?guard key)
                 )
     :add      ((has prisoner key))
     :del      ((has ?guard key))
     :txt      (found key at ?p1)
     :cmd      (key at ?p1 -)
     }

    ;;i think this is working because if its false escape
    :exit
    {:name     exit
     :achieves (escaped prisoner true)
     :when     ((is exit exit))
     :post     ((on prisoner ?p1)
                 (has prisoner key))
     :pre      ((has prisoner key)
                 (on prisoner ?p1)
                 (is exit exit)
                 (escaped prisoner false))
     :add      ((escaped prisoner true))
     :del      ((escaped prisoner false))
     :txt      (prisoner escaped)
     :cmd      (escaped to exit)
     }
    }
  )

; based on: strips-search-1a.clj from SHRDLU model
; naming changes only
;===================================================


;these operators can have all of these slots...
;{ :name put-on
;  :achieves (on ?x ?y)
;  :when   ( (at ?x ?sx) (at ?y ?sy) (:guard (not= (? sx) (? sy))) )
;  :post   ( (protected ?sx) (protected ?sy)
;            (cleartop ?x)
;            (cleartop ?y)
;            (hand empty) )
;  :pre ()
;  :del ( (at ?x ?sx)
;         (cleartop ?y)
;         (protected ?sx)
;         (protected ?sy) )
;  :add ( (at ?x ?sy)
;         (on ?x ?y) )
;  :cmd ( (pick-from ?sx)
;         (drop-at ?sy) )
;  :txt (put ?x on ?y)
;  }
;
;NB: in this example the ops have unique :achieves + :when
;
;They are processed as follows...
;
;goal <- (pop goal-stack)
;match (:achieves op) goal
;  match (:when op) BD
;    push( expand op , goal-stack )
;    push-all( expand (:post op), goal-stack )




