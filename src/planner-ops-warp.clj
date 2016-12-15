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
(def planner-operations-prisoner-warp

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
     :txt      (| unlocked cell)
     :cmd      (cell-unlocked -)
     }

    :leave-cell
    {:name     leave-cell
     :achieves (at prisoner ?p1)
     :when     ((at prisoner ?p1)
                 (connects c ?p1))
     :post     ((is c unlocked))
     :pre      ()
     :add      ((at prisoner ?p1))
     :del      ((at prisoner c))
     :txt      (| leave cell)
     :cmd      (leave cell -)
     }

    ;:move-x   ;; a handy multi-move operator
    ;{ :name move-x
    ; :achieves (on prisoner ?p2)
    ; :when ((isa ?x ?_) (at ?x ?sx) (at ?y ?sy) )
    ; :post ((protected ?sx [on ?x ?y]) (protected ?sy [on ?x ?y])
    ;         (cleartop ?x) (cleartop ?y) (hand empty) )
    ; :pre ((on ?x ?ox) )
    ; :del ((at ?x ?sx)  (on ?x ?ox) (cleartop ?y)
    ;        (protected ?sx [on ?x ?y]) (protected ?sy [on ?x ?y]) )
    ; :add ((at ?x ?sy) (on ?x ?y) (cleartop ?ox))
    ; :cmd ((pick-from ?sx) (drop-at ?sy) )
    ; :txt ((mv-pick ?x off ?ox at ?sx)
    ;        (mv-put ?x on ?y at ?sy) )
    ; }

    :move-any
    {
     :name     move-any
     :achieves (at prisoner ?p2)
     :when     ((at prisoner ?p)
                 (isa ?p1 loc)
                 (isa ?p2 loc)
                 (:guard (not= (? p1) (? p2)))
                 )
     :post     ()
     :pre      ()
     :del      ((at prisoner ?p))
     :add      ((at prisoner ?p2))
     :cmd      ()
     :txt      (| moved from ?p to ?p2)
     }

    :get-key
    {:name     get-key
     :achieves (has prisoner key)
     :when     ((holds ?guard key)
                 (positioned ?guard ?p)
                 (isa ?p loc)
                 )
     :post     ((at prisoner ?p))
     :pre      ()
     :add      ((has prisoner key))
     :del      ((holds ?guard key))
     :txt      (| found key at ?p)
     :cmd      (key at ?p1 -)
     }

    ;;i think this is working because if its false escape
    :exit
    {:name     exit
     :achieves (escaped prisoner true)
     :when     ((is ?p1 exit)
                 (isa ?p1 loc))
     :post     ((has prisoner key)
                 (at prisoner ?p1))
     :pre      ((escaped prisoner false))
     :add      ((escaped prisoner true))
     :del      ((escaped prisoner false))
     :txt      (| prisoner escaped)
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



