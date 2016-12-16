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
(def planner-operations-prisoner

  '{
    :unlock
    {:name     unlock
     :achieves (is c unlocked)
     :when     ((is c locked))
     :post     ()
     :pre      ((on prisoner c)
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
     :when     ((on prisoner c)
                 (connects c ?p1))
     :post     ((is c unlocked))
     :pre      ((on prisoner c)
                 (is c unlocked)
                 (connects c ?p1)
                 )
     :add      ((on prisoner ?p1))
     :del      ((on prisoner c))
     :txt      (leave cell)
     :cmd      (leave cell -)
     }

    ;:move                                                   ;; multi move operator
    ;{:name     move
    ; :achieves (on prisoner ?p2)
    ; :when     ((connects ?p1 ?p2))
    ; :post     ((on prisoner ?p1))
    ; :pre      ((on prisoner ?p1)
    ;             (connects ?p1 ?p2))
    ; :del      ((on prisoner ?p1))
    ; :add      ((on prisoner ?p2))
    ; :cmd      (move-to ?p2 -)
    ; :txt      ((prisoner moved from ?p1 to ?p2))
    ; }

    :move-x                                                 ;; a handy multi-move operator
    {:name     move-x
     :achieves (on prisoner ?p2)
     :when     ((on prisoner ?x)
                 (connects ?p1 ?p2)
                 (watched ?p2 false)
                 (:not (tried ?p1 ?p2))
                 (:not (protected ?x [on prisoner ?p1]))
                 )
     :post     ((tried ?p1 ?p2)
                 (protected ?x [on prisoner ?p2])
                 (on prisoner ?p1))
     :pre      ()
     :del      ((on prisoner ?x)
                 (tried ?p1 ?p2)
                 (protected ?x [on prisoner ?p2]))
     :add      ((on prisoner ?p2))
     :cmd      ()
     :txt      (| prisoner moved from ?p1 to ?p2)
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
     :when     ((is ?p1 exit))
     :post     ((has prisoner key)(on prisoner ?p1))
     :pre      ((escaped prisoner false))
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




