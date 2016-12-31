;;----------------
;;Prisoner
;;----------------
;;--unlock
;;--leave-cell
;;--move
;;--hide
;;--got-key
;;--exit

;(def planner-operations-prisoner-2
;
;  '{
;
;    ;:move-to-tile
;    ;{:name move-to-tile
;    ; :achieves (on prisoner ?tilea)     ;;Guard function defined above. - use https://www.scm.tees.ac.uk/isg/aia/student_papers/Healey_Milner_Percival-2.pdf (page 4-5)
;    ; :when ( (:not (tried ?tilea ?tileb))(connects ?tilea ?tileb))  ;;
;    ; :post ((tried ?tilea ?tileb)(on prisoner ?tileb) )                          ;
;    ; ;;Same as ops
;    ; :pre ((on prisoner ?tileb))
;    ; :add ( (on prisoner ?tilea)(tried ?tileb ?tilea))
;    ; :del ((on prisoner ?tileb))
;    ; :txt (prisoner moved from ?tileb to ?tilea)
;    ; :cmd []
;    ; }
;
;    :leave-cell
;    {:name     leave-cell
;     :achieves (on prisoner ?tilea)                      ;unlocked cell
;     :when ((connects ?tilea c))
;     :post ((is c unlocked))
;     ;;Same as ops
;     :pre ((on prisoner c)
;            (is c unlocked)
;            (connects ?tilea c)
;            )
;     :add ((on prisoner ?tilea))
;     :del ((at prisoner c))
;     :txt (leave cell)
;     :cmd [leave-cell]
;     }
;
;    :move-to-tile
;    {:name move-to-tile
;     :achieves (on prisoner ?tilea)
;     :when ((connects ?tilea ?tileb) (:not (tried ?tileb ?tilea)))
;     :post ((tried ?tilea ?tileb)(on prisoner ?tileb))
;     ;;Same as ops
;     :pre ((on prisoner ?tileb))
;     :add ((on prisoner ?tilea) )
;     :del ((on prisoner ?tileb) (tried ?tilea ?tileb) )
;     :txt (prisoner moved from ?tileb to ?tilea)
;     :cmd [move-to-tile]
;     }
;
;    :attempted
;    {:name attempted
;     :achieves (tried ?tilea ?tileb)
;     :when ((:not (tried ?tileb ?tilea)))
;     :add ((tried ?tilea ?tileb))
;     }
;
;    :unlock
;    {:name     unlock
;     :achieves (is c unlocked)                              ;unlocked cell
;     :when ((is c locked))
;     :post ()
;     ;;Same as ops
;     :pre ((at prisoner c)
;            (is c locked)
;            )
;     :add ((is c unlocked))
;     :del ((is c locked))
;     :txt (unlocked cell)
;     :cmd [unlock]
;     }
;
;
;
;    :get-key
;    {:name     get-key
;     :achieves (has prisoner key)                           ;failed
;     :when ((on prisoner ?tile)
;             (at ?guard ?tile)
;             (has ?guard key))
;     :post ((on prisoner ?tile))
;     ;;Same as ops
;     :pre ((on prisoner ?tile)
;            (at ?guard ?tile)
;            (has ?guard key)
;            )
;     :add ((has prisoner key))
;     :del ((has ?guard key))
;     :txt (found key at ?p1)
;     :cmd [get-key]
;     }
;
;    :protect-key
;    { :name protect-key
;     :achieves (has prisoner key)
;     :add  ((has prisoner key)  )
;     }
;
;    :exit
;    {:name     exit
;     :achieves (escaped prisoner true)                      ;unlocks cell leave cell
;     :when ((escaped prisoner false) (is ?tilea exit))
;     :post ((on prisoner ?tilea))
;     ;;Same as ops
;     :pre (                                                 ;;add key
;            (on prisoner ?tilea)
;            (is ?tilea exit)
;            (escaped prisoner false)
;            )
;     :add ((escaped prisoner true))
;     :del ((escaped prisoner false))
;     :txt (prisoner escaped)
;     :cmd [exit]
;     }
;    })

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
     :cmd      (unlock-cell)
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
     :cmd      (move-junction ?p1)
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

    ;:move-x                                                 ;; a handy multi-move operator
    ;{:name     move-x
    ; :achieves (on prisoner ?p2)
    ; :when     ((on prisoner ?x)
    ;             (connects ?p1 ?p2)
    ;             (watched ?p2 false)
    ;             (:not (tried ?p1 ?p2))
    ;             (:not (protected ?x [on prisoner ?p1]))
    ;             )
    ; :post     ((tried ?p1 ?p2)
    ;             (protected ?x [on prisoner ?p2])
    ;             (on prisoner ?p1))
    ; :pre      ()
    ; :del      ((on prisoner ?x)
    ;             (tried ?p1 ?p2)
    ;             (protected ?x [on prisoner ?p2]))
    ; :add      ((on prisoner ?p2))
    ; :cmd      ()
    ; :txt      (| prisoner moved from ?p1 to ?p2)
    ; }

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
     :cmd (move-junction ?desti)
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
     :cmd      (get-key)
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
     :cmd      (exit-prison)
     }
    }
  )




