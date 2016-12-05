;;----------------
;;Guard
;;----------------
;;--face
(def planner-operations-guard
  '{
    :face
    {:name     face
     :achieves ()
     :when ()
     :post ()
     :pre ()
     :add ()
     :del ()
     :txt ()
     :cmd ()
     }
    }
  )



;'{:move-to-junction
;  {:name move-to-junction
;   :achieves (on prisoner ?junction)
;   :when ((connects ?junction ?corridor))
;   :post ((moving prisoner ?corridor) (escaped prisoner cell))
;   ;;Same as ops
;   :pre ((moving prisoner ?corridor)
;          (connects ?corridor ?junction)
;          )
;   :add ((on prisoner ?junction))
;   :del ((moving prisoner ?corridor))
;   :txt (prisoner moved from ?corridor to ?junction)
;   :cmd []
;   }
;
;  :move-to-corridoor
;  {:name     move-to-corridoor
;   :achieves (moving prisoner ?corridor)                      ;;:txt (unlocked cell prisoner escaped the cell prisoner moved from ?junction to c8)}
;   :when ((connects ?corridor ?junction) (watched ?corridor false))
;   :post ((on prisoner ?junction)
;           (connects ?junction ?corridor)
;           (escaped prisoner cell))
;   ;;Same as ops
;   :pre ((on prisoner ?junction)
;          (connects ?junction ?corridor)
;          (watched ?corridor false)
;          )
;   :add ((moving prisoner ?corridor))
;   :del ((on prisoner ?junction))
;   :txt (prisoner moved from ?junction to ?corridor)
;   :cmd ()
;   }


(defn check [prisonertile destitile sometile]
  (do (println prisonertile destitile sometile) true)
  )

;;----------------
;;Prisoner
;;----------------
;;--unlock
;;--leave-cell
;;--move
;;--hide
;;--got-key
(def planner-operations-prisoner

  '{:move-to-tile
    {:name move-to-tile
     :achieves (on prisoner ?tilea)     ;;Guard function defined above. - use https://www.scm.tees.ac.uk/isg/aia/student_papers/Healey_Milner_Percival-2.pdf (page 4-5)
     :when ((on prisoner ?tile) (connects ?tilea ?tileb) (:guard (check (? tile) (? tilea) (? tileb))))

     :post ((on prisoner ?tileb))
     ;;Same as ops
     :pre ((on prisoner ?tileb))
     :add ((on prisoner ?tilea))
     :del ((on prisoner ?tileb))
     :txt (prisoner moved from ?tileb to ?tilea)
     :cmd []
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
     :cmd []
     }

    :leave-cell
    {:name     leave-cell
     :achieves (on prisoner ?tilea)                      ;unlocked cell
     :when ((at prisoner c) (connects c ?tilea))
     :post ((is c unlocked))
     ;;Same as ops
     :pre ((at prisoner c)
            (is c unlocked)
            (connects c ?tilea)
            )
     :add ((on prisoner ?tilea))
     :del ((at prisoner c))
     :txt (leave cell)
     :cmd []
     }

    :get-key
    {:name     get-key
     :achieves (has prisoner key)                           ;failed
     :when ((on prisoner ?junction)
             (at ?guard ?junction)
             (has ?guard key))
     :post ((on prisoner ?junction))
     ;;Same as ops
     :pre ((on prisoner ?tile)
            (at ?guard ?tile)
            (has ?guard key)
            )
     :add ((has prisoner key))
     :del ((has ?guard key))
     :txt (key twoked)
     :cmd []
     }

    :protect-key
    { :name protect-key
     :achieves (has prisoner key)
     :add  ((has prisoner key)  )
     }

    :exit
    {:name     exit
     :achieves (escaped prisoner true)                      ;unlocks cell leave cell
     :when ((escaped prisoner false))
     :post ((on prisoner ?tilea))
     ;;Same as ops
     :pre (                                                 ;;add key
            (on prisoner ?tilea)
            (is ?tile exit)
            (escaped prisoner false)
            )
     :add ((escaped prisoner true))
     :del ((escaped prisoner false))
     :txt (get plannered m8)
     :cmd ()
     }
    })
