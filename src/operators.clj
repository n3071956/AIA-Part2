;;----------------ops-search operators
;;----------------
;;Guard
;;----------------
(def guard
  '{face {:pre (()
                 ()
                 )
          :add (())
          :del (()
                 ())
          :txt ()
          :cmd []
          }
    })


;'{move-to-junction {:pre ((moving prisoner ?corridor)
;                           (connects ?corridor ?junction)
;                           )
;                    :add ((on prisoner ?junction))
;                    :del ((moving prisoner ?corridor))
;                    :txt (prisoner moved from ?corridor to ?junction)
;                    :cmd []
;                    }
;  move-to-corridoor {:pre ((on prisoner ?junction)
;                            (connects ?junction ?corridor)
;                            (watched ?corridor false)
;                            )
;                     :add ((moving prisoner ?corridor))
;                     :del ((on prisoner ?junction))
;                     :txt (prisoner moved from ?junction to ?corridor)
;                     :cmd []
;                     }

;;----------------
;;Prisoner
;;----------------
(def operations-prisoner
  '{
   move-to-tile {:pre ((on prisoner ?tileb) (connects ?tilea ?tileb))
                     :add ((on prisoner ?tilea))
                     :del ((on prisoner ?tileb))
                     :txt (prisoner moved from ?tileb to ?tilea)
                     :cmd []
                     }

   unlock           {:pre ((at prisoner c)
                            (is c locked)
                            )
                     :add ((is c unlocked))
                     :del ((is c locked))
                     :txt (unlocked cell)
                     :cmd []
                     }
   leave-cell       {:pre ((at prisoner c)
                            (is c unlocked)
                            (connects c ?tile)
                            )
                     :add ((on prisoner ?tile))
                     :del ((at prisoner c))
                     :txt (leave cell)
                     :cmd []
                     }
   get-key          {:pre ((on prisoner ?tile)
                            (at ?guard ?tile)
                            (has ?guard key)
                            )
                     :add ((has prisoner key))
                     :del ((has ?guard key))
                     :txt (key twoked)
                     :cmd []
                     }
   exit             {:pre ((has prisoner key)
                            (on prisoner ?tile)
                            (is ?tile exit)
                            (escaped prisoner false)
                            )
                     :add ((escaped prisoner true))
                     :del ((escaped prisoner false))
                     :txt (get ops-searched m8)
                     :cmd []
                     }
   })