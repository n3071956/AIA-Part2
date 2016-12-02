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

  '{

    :move-to-junction
    {:name     move-to-junction
     :achieves (on prisoner ?junction)
     :when ()
     :post ((moving prisoner ?corridor))
     :pre ((moving prisoner ?corridor)
            (connects ?corridor ?junction)
            )
     :add ((on prisoner ?junction))
     :del ((moving prisoner ?corridor))
     :txt (prisoner moved from ?corridor to ?junction)
     :cmd ()
     }

    :move-to-corridoor
    {:name     move-to-corridoor
     :achieves (on prisoner ?corridor)
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
     :achieves (is c unlocked)
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
     :achieves (on prisoner ?junction)
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
     :achieves (has prisoner key)
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

    :exit
    {:name     exit
     :achieves (escaped prisoner true)
     :when ((escaped prisoner false))
     :post ( (on prisoner ?junction))
     :pre ((on prisoner ?junction)
            (is ?junction exit)
            (escaped prisoner false)
            )
     :add ((escaped prisoner true))
     :del ((escaped prisoner false))
     :txt (get plannered m8)
     :cmd ()
     }
    })
