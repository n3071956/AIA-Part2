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

;;----------------
;;Prisoner
;;----------------
;;--unlock
;;--leave-cell
;;--move
;;--hide
;;--got-key
(def planner-operations-prisoner

  '{ :move-to-junction
    {:name     move-to-junction
     :achieves (on prisoner ?junction)                      ;;unlocked cell
     :when ((moving prisoner ?corridor))
     :post ((at prisoner c))
     :pre ((connects ?corridor ?junction))
     :add ((on prisoner ?junction))
     :del ((moving prisoner ?corridor))
     :txt (prisoner moved somehow)
     :cmd ()
     }

    :move-to-corridoor
    {:name     move-to-corridoor
     :achieves (on prisoner ?corridor)                      ;;(unlocked cell leave cell prisoner moved from j6 to c8)
     :when ((watched ?corridor false))
     :post ((on prisoner ?junction))
     :pre ((on prisoner ?junction)
            (connects ?junction ?corridor)
            (watched ?corridor false)
            )
     :add ((moving prisoner ?corridor))
     :del ((on prisoner ?junction))
     :txt (prisoner moved from ?junction to ?corridor)
     :cmd ()
     }

    :unlock
    {:name     unlock
     :achieves (is c unlocked)                              ;unlocked cell
     :when ((is c locked))
     :post ()
     :pre ((at prisoner c)
            (is c locked)
            )
     :add ((is c unlocked))
     :del ((is c locked))
     :txt (unlocked cell)
     :cmd ()
     }

    :leave-cell
    {:name     leave-cell
     :achieves (on prisoner ?junction)                      ;unlocked cell
     :when ((at prisoner c))
     :post ((is c unlocked))
     :pre ((at prisoner c)
            (is c unlocked)
            (connects c ?junction)
            )
     :add ((on prisoner ?junction))
     :del ((at prisoner c))
     :txt (leave cell)
     :cmd ()
     }

    :get-key
    {:name     get-key
     :achieves (has prisoner key)                           ;failed
     :when ((on prisoner ?junction)
             (at ?guard ?junction)
             (has ?guard key))
     :post ((on prisoner ?junction))
     :pre ((on prisoner ?junction)
            (at ?guard ?junction)
            (has ?guard key)
            )
     :add ((has prisoner key))
     :del ((has ?guard key))
     :txt (key twoked)
     :cmd ()
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
     :post ( (on prisoner ?junction))
     :pre ((has prisoner key)
            (on prisoner ?junction)
            (is ?junction exit)
            (escaped prisoner false)
            )
     :add ((escaped prisoner true))
     :del ((escaped prisoner false))
     :txt (get plannered m8)
     :cmd ()
     }
    })
