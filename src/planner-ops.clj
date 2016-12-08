;;----------------Planner operaotrs
;;----------------
;;Guard
;;----------------
;;--face
(def planner-operations-guard
  '{
    :face
    {:name     face
     :achieves ()
     :when     ()
     :post     ()
     :pre      ()
     :add      ()
     :del      ()
     :txt      ()
     :cmd      ()
     }
    }
  )

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
    ;:move-to-junction
    ;{:name     move-to-junction
    ; :achieves (on prisoner j5)
    ; :when ((isjunction ?junction))
    ; :post ((moving prisoner ?corridor))
    ; :pre ((moving prisoner ?corridor)
    ;        (connects ?corridor ?junction)
    ;        )
    ; :add ((on prisoner ?junction))
    ; :del ((moving prisoner ?corridor))
    ; :txt (| prisoner moved from ?corridor to ?junction)
    ; :cmd ()
    ; }
    ;
    ;:move-to-corridoor
    ;{:name     move-to-corridoor
    ; :achieves (moving prisoner ?corridor)
    ; :when ((watched ?corridor false))
    ; :post ((on prisoner ?junction))
    ; :pre ((on prisoner ?junction)
    ;        (connects ?junction ?corridor)
    ;        (watched ?corridor false)
    ;        )
    ; :add ((moving prisoner ?corridor))
    ; :del ((on prisoner ?junction))
    ; :txt (| prisoner moved from ?junction to ?corridor)
    ; :cmd ()
    ; }
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
     :cmd      ()
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
     :cmd      ()
     }

    :move
    {:name     move
     :achieves (on prisoner ?p2)
     :when     ((connects ?p1 ?p2))
     :post     ((on prisoner ?p1))
     :pre      ((on prisoner ?p1)
                 (connects ?p1 ?p2))
     :del      ((on prisoner ?p1))
     :add      ((on prisoner ?p2))
     :cmd      ()
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
     :txt      (prisoner found key)
     :cmd      ()
     }

    ;;i think this is working because if its false escape
    :exit
    {:name     exit
     :achieves (escaped prisoner true)
     :when     ((is ?p1 exit))
     :post     ((on prisoner ?p1)
                 (has prisoner key))
     :pre      (())
     :add      ((escaped prisoner true))
     :del      ((escaped prisoner false))
     :txt      (prisoner has escaped)
     :cmd      ()
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




