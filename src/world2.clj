;;----------------
;;World Define
;;----------------
(def world2
  '#{
      (connects exit c5)
     (connects exit c6)
     (connects c5 exit)
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
      (connects c6 exit)

     (connects j6 c)
     (connects c j6)

     (is exit exit)
     (is c locked)

     (at guard1 j5)
     (facing guard1 c8)
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

     (at prisoner c)
     (locked door false)
     (caged prisoner true)
     (escaped prisoner false)

     })