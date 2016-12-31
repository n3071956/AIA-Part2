;;----------------
;;World Define
;;----------------
(def planner-world
  '#{
     (connects j7 c1) (connects c1 j7)
     (connects c2 j7) (connects j7 c2)
     (connects c1 j2) (connects j2 c1)
     (connects c2 j1) (connects j1 c2)
     (connects j2 c3) (connects c3 j2)
     (connects j1 c4) (connects c4 j1)
     (connects c4 j3) (connects j3 c4)
     (connects c3 j3) (connects j3 c3)
     (connects j3 c5) (connects c5 j3)
     (connects j3 c6) (connects c6 j3)
     (connects c5 j4) (connects j4 c5)
     (connects c6 j5) (connects j5 c6)
     (connects j4 c7) (connects c7 j4)
     (connects c8 j5) (connects j5 c8)
     (connects c7 j6) (connects j6 c7)
     (connects c8 j6) (connects j6 c8)

     (connects j6 c) (connects c j6)

     (is j7 exit)
     (is c locked)
     (at guard1 j1)
     (has guard1 key)

     (watched c1 false)
     (watched c2 false)
     (watched c3 false)
     (watched c4 false)
     (watched c5 false)
     (watched c6 false)
     (watched c7 false)
     (watched c8 false)

     (watched j1 false)
     (watched j2 false)
     (watched j3 false)
     (watched j4 false)
     (watched j5 false)
     (watched j6 false)
     (watched j7 false)

     (at guard2 j4)
     (facing guard2 c5)

     (locked door false)
     (on prisoner c)
     (escaped prisoner false)
     }
  )