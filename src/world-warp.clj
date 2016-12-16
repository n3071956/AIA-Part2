;;----------------
;;World Define
;;----------------
(def world-warp
  '#{
     (isa j1 location)
     (isa j2 location)
     (isa c cell)
     (isa j6 location)

     (connects c j6)

     (is j2 exit)
     (is c locked)

     (positioned guard1 j1)
     (holds guard1 key)

     (at prisoner c)
     (escaped prisoner false)
     })