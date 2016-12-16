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
     :pre      ((at prisoner c)(is c locked))
     :add      ((is c unlocked))
     :del      ((is c locked))
     :txt      (| unlocked cell)
     :cmd      (cell-unlocked -)
     }

    :leave-cell
    {:name     leave-cell
     :achieves (left prisoner c)
     :when     ((at prisoner c) (connects c ?p1))
     :post     ((is c unlocked))
     :pre      ()
     :add      ((at prisoner ?p1))
     :del      ((at prisoner c))
     :txt      (| leave cell)
     :cmd      (leave cell -)
     }

    :move
    {:name     move
     :achieves (at prisoner ?p2)
     :when     ((at prisoner ?p) (isa ?p1 location) (isa ?p2 location)
                 (isa ?p location) (:guard (not= (? p1) (? p2))))
     :post     ()
     :pre      ()
     :del      ((at prisoner ?p))
     :add      ((at prisoner ?p2))
     :cmd      ()
     :txt      (| moved from ?p1 to ?p2)
     }

    :get-key
    {:name     get-key
     :achieves (has prisoner key)
     :when     ((holds ?guard key) (positioned ?guard ?p) (isa ?p location))
     :post     ((left prisoner c) (at prisoner ?p))
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
     :when     ((is ?p1 exit) (isa ?p1 location))
     :post     ((has prisoner key) (at prisoner ?p1))
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




