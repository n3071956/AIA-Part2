;;----------------ops-search operators
;;----------------
;;World Define
;;----------------
(def world
  '#{(connects j7 c1)
     (connects j7 c2)
     (connects c1 j7)
     (connects c1 j2)
     (connects c2 j7)
     (connects c2 j1)
     (connects j1 c2)
     (connects j1 c4)
     (connects c4 j1)
     (connects c4 j3)
     (connects j2 c1)
     (connects j2 c3)
     (connects c3 j3)
     (connects c3 j2)
     (connects j3 c3)
     (connects j3 c4)
     (connects j3 c5)
     (connects j3 c6)
     (connects c5 j3)
     (connects c5 j4)
     (connects j4 c5)
     (connects j4 c7)
     (connects c7 j4)
     (connects c7 j6)
     (connects j6 c7)
     (connects j6 c8)
     (connects c8 j6)
     (connects c8 j5)
     (connects j5 c8)
     (connects j5 c6)
     (connects c6 j5)
     (connects c6 j3)

     (connects j6 c)
     (connects c j6)
     (top c j6)
     (bottom j6 c)

     (right j7 c1)
     (bottom j7 c2)
     (left c1 j7)
     (right c1 j2)
     (top c2 j7)
     (bottom c2 j1)
     (top j1 c2)
     (right j1 c4)
     (left c4 j1)
     (right c4 j3)
     (left j2 c1)
     (bottom j2 c3)
     (bottom c3 j3)
     (top c3 j2)
     (top j3 c3)
     (left j3 c4)
     (right j3 c5)
     (bottom j3 c6)
     (left c5 j3)
     (right c5 j4)
     (left j4 c5)
     (bottom j4 c7)
     (top c7 j4)
     (bottom c7 j6)
     (top j6 c7)
     (left j6 c8)
     (right c8 j6)
     (left c8 j5)
     (right j5 c8)
     (top j5 c6)
     (bottom c6 j5)
     (top c6 j3)

     (is j7 exit)
     (is c locked)

     (at guard1 j1)
     (facing guard1 c4)
     (has guard1 key)

     (watched c1 false)
     (watched c2 false)
     (watched c3 false)
     (watched c4 true)
     (watched c5 true)
     (watched c6 false)
     (watched c7 false)
     (watched c8 false)

     (at guard2 j4)
     (facing guard2 c5)

     (at prisoner c)
     (escaped prisoner false)
     })
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

;;----------------
;;Prisoner
;;----------------
(def operations-prisoner
  '{move-to-junction {:pre ((moving prisoner ?corridor)
                             (connects ?corridor ?junction)
                             )
                      :add ((on prisoner ?junction))
                      :del ((moving prisoner ?corridor))
                      :txt (prisoner moved from ?corridor to ?junction)
                      :cmd []
                      }
    move-to-corridoor {:pre ((on prisoner ?junction)
                              (connects ?junction ?corridor)
                              (watched ?corridor false)
                              )
                       :add ((moving prisoner ?corridor))
                       :del ((on prisoner ?junction))
                       :txt (prisoner moved from ?junction to ?corridor)
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
                             (connects c ?junction)
                             )
                      :add ((on prisoner ?junction))
                      :del ((at prisoner c))
                      :txt (leave cell)
                      :cmd []
                      }
    get-key          {:pre ((on prisoner ?junction)
                             (at ?guard ?junction)
                             (has ?guard key)
                             )
                      :add ((has prisoner key))
                      :del ((has ?guard key))
                      :txt (key twoked)
                      :cmd []
                      }
    exit             {:pre ((has prisoner key)
                             (on prisoner ?junction)
                             (is ?junction exit)
                             (escaped prisoner false)
                             )
                      :add ((escaped prisoner true))
                      :del ((escaped prisoner false))
                      :txt (get ops-searched m8)
                      :cmd []
                      }
    })
;;----------------
;;Rules
;;----------------

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
;;--exit
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
    }
  )

;======================
; testing
;======================

;(def ops
;  '{pickup {:pre ((agent ?agent)
;                   (manipulable ?obj)
;                   (at ?agent ?place)
;                   (on ?obj   ?place)
;                   (holds ?agent nil)
;                   )
;            :add ((holds ?agent ?obj))
;            :del ((on ?obj   ?place)
;                   (holds ?agent nil))
;            :txt (pickup ?obj from ?place)
;            :cmd [grasp ?obj]
;            }
;    drop    {:pre ((at ?agent ?place)
;                    (holds ?agent ?obj)
;                    (:guard (? obj))
;                    )
;             :add ((holds ?agent nil)
;                    (on ?obj   ?place))
;             :del ((holds ?agent ?obj))
;             :txt (drop ?obj at ?place)
;             :cmd [drop ?obj]
;             }
;    move    {:pre ((agent ?agent)
;                    (at ?agent ?p1)
;                    (connects ?p1 ?p2)
;                    )
;             :add ((at ?agent ?p2))
;             :del ((at ?agent ?p1))
;             :txt (move ?p1 to ?p2)
;             :cmd [move ?p2]
;             }
;    })
;
;
;(def state1
;  '#{(at R table)
;     (on book table)
;     (on spud table)
;     (holds R nil)
;     (connects table bench)
;     (manipulable book)
;     (manipulable spud)
;     (agent R)
;     })
;
;
;user=> (ops-search state1 '((on book bench)) ops)
;{:state #{(agent R) (manipulable book) (on spud table) (on book bench) (holds R nil)
;          (at R bench) (manipulable spud) (connects table bench)},
; :path (#{(agent R) (manipulable book) (on spud table) (holds R nil) (manipulable spud)
;          (connects table bench) (on book table) (at R table)}
;        #{(agent R) (holds R book) (manipulable book) (on spud table) (manipulable spud)
;          (connects table bench) (at R table)}
;        #{(agent R) (holds R book) (manipulable book) (on spud table) (at R bench)
;          (manipulable spud) (connects table bench)}),
; :cmds ([grasp book] [move bench] [drop book]),
; :txt ((pickup book from table) (move table to bench) (drop book at bench))}
;
;
;(def world
;  '#{(connects table bench)
;     (manipulable book)
;     (manipulable spud)
;     (agent R)
;     })
;
;(def state2
;  '#{(at R table)
;     (on book table)
;     (on spud table)
;     (holds R nil)
;     })
;
;
;user=> (ops-search state2 '((on book bench)) ops :world world)
;{:state #{(on spud table) (on book bench) (holds R nil) (at R bench)},
; :path (#{(on spud table) (holds R nil) (on book table) (at R table)}
;        #{(holds R book) (on spud table) (at R table)}
;        #{(holds R book) (on spud table) (at R bench)}),
; :cmds ([grasp book] [move bench] [drop book]),
; :txt ((pickup book from table) (move table to bench) (drop book at bench))}
;
;
;(def world2
;  '#{(connects table bench)
;     (connects bench table)
;     (connects bench sink)
;     (connects sink bench)
;     (manipulable book)
;     (manipulable spud)
;     (agent R)
;     })
;
;(def state3
;  '#{(at R table)
;     (on book table)
;     (on spud table)
;     (holds R nil)
;     })
;
;user=> (ops-search state3 '((on book bench)(on spud sink)) ops :world world2)
;{:state #{(at R sink) (on book bench) (holds R nil) (on spud sink)},
; :path (#{(on spud table) (holds R nil) (on book table) (at R table)}
;        #{(holds R book) (on spud table) (at R table)}
;        #{(holds R book) (on spud table) (at R bench)}
;        #{(on spud table) (on book bench) (holds R nil) (at R bench)}
;        #{(on spud table) (on book bench) (holds R nil) (at R table)}
;        #{(on book bench) (holds R spud) (at R table)}
;        #{(on book bench) (at R bench) (holds R spud)}
;        #{(at R sink) (on book bench) (holds R spud)}),
; :cmds ([grasp book] [move bench] [drop book]
;        [move table] [grasp spud] [move bench] [move sink] [drop spud]),
; :txt ((pickup book from table) (move table to bench) (drop book at bench)
;       (move bench to table) (pickup spud from table) (move table to bench)
;       (move bench to sink) (drop spud at sink))}
