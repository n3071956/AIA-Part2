;;----------------
;;World Define
;;----------------
(def world-9
  '#{
     (connects c2 exit) (connects exit c2)
     (connects c1 exit) (connects exit c1)
     (connects j1 c1) (connects c1 j1) (watched j1 false)
     (connects j2 c1) (connects c1 j2) (watched j2 false)
     (connects j2 c4) (connects c4 j2) (watched j3 false)
     (connects c4 j4) (connects j4 c4) (watched j4 false)
     (connects c3 j4) (connects j4 c3) (watched c1 false)
     (connects c3 j3) (connects j3 c3) (watched c2 false)
     (connects j3 c2) (connects c2 j3) (watched exit false)
     (connects c2 j1) (connects j1 c2) (watched c4 false)

     (at prisoner c)
     (locked door false)
     (is c locked)
     (is j1 exit)
     (watched c3 true)
     (at guard1 j3)
     (escaped prisoner false)
     (caged prisoner true)
     (isa c cell)
     (is exit exit)
     (has guard1 key)
     }
  )

(def world-9-var
  '#{
     (connects j4 c) (connects c j4)
     }
  )

(def world-14
  '#{
     (connects c5 j3) (connects j3 c5) (watched c5 false)
     (connects j4 c6) (connects c6 j4) (watched c6 false)
     (connects c6 j6) (connects j6 c6) (watched c7 false)
     (connects c5 j5) (connects j5 c5) (watched j5 false)
     (connects j5 c7) (connects c7 j5) (watched j6 false)
     (connects j6 c7) (connects c7 j6) (watched j7 false)
     }
  )

(def world-14-var
  '#{
     (connects j6 c) (connects c j6)
     }
  )

(def world-19
  '#{
     (connects j2 c8) (connects c8 j2) (watched c8 false)
     (connects j4 c9) (connects c9 j4) (watched c9 false)
     (connects c8 j7) (connects j7 c8) (watched c10 false)
     (connects c9 j8) (connects j8 c9) (watched j7 false)
     (connects c10 j7) (connects j7 c10) (watched j8 false)
     (connects c10 j8) (connects j8 c10)
     }
  )

(def world-19-var
  '#{
     (connects j8 c) (connects c j8)
     }
  )

(def world-22
  '#{
     (connects j8 c14) (connects c14 j8) (watched c14 false)
     (connects c14 j9) (connects j9 c14) (watched c16 false)
     (connects j9 c16) (connects c16 j9) (watched j9 false)
     (connects c16 j6) (connects j6 c16)
     }
  )

(def world-22-var
  '#{
     (connects j9 c) (connects c j9)
     }
  )

(def world-27
  '#{
     (connects j5 c18) (connects c18 j5) (watched c18 false)
     (connects c19 j6) (connects j6 c19) (watched c19 false)
     (connects j10 c18) (connects c18 j10) (watched c22 false)
     (connects j10 c22) (connects c22 j10) (watched j11 false)
     (connects c22 j11) (connects j11 c22) (watched j10 false)
     (connects j11 c19) (connects c19 j11)
     }
  )

(def world-27-var
  '#{
     (connects j11 c) (connects c j11)
     }
  )

(def world-29
  '#{
     (connects j11 c23) (connects c23 j11) (watched c23 false)
     (connects j12 c23) (connects c23 j12) (watched c20 false)
     (connects j12 c20) (connects c20 j12) (watched j12 false)
     (connects j9 c20) (connects c20 j9)
     }
  )

(def world-29-var
  '#{
     (connects j12 c) (connects c j12)
     }
  )

(def world-34
  '#{
     (connects j7 c11) (connects c11 j7) (watched c11 false)
     (connects j13 c11) (connects c11 j13) (watched c12 false)
     (connects j13 c12) (connects c12 j13) (watched c13 false)
     (connects j14 c12) (connects c12 j14) (watched j13 false)
     (connects j14 c13) (connects c13 j14) (watched j14 false)
     (connects j8 c13) (connects c13 j8)
     }
  )

(def world-34-var
  '#{
     (connects j14 c) (connects c j14)
     }
  )

(def world-37
  '#{
     (connects j14 c15) (connects c15 j14) (watched c15 false)
     (connects j15 c15) (connects c15 j15) (watched c17 false)
     (connects j15 c17) (connects c17 j15) (watched j15 false)
     (connects j9 c17) (connects c17 j9)
     }
  )

(def world-37-var
  '#{
     (connects j15 c) (connects c j15)
     }
  )

(def world-40
  '#{
     (connects j15 c21) (connects c21 j15) (watched c21 false)
     (connects j16 c21) (connects c21 j16) (watched c24 false)
     (connects j16 c24) (connects c24 j16) (watched j16 false)
     (connects j12 c24) (connects c24 j12)
     }
  )

(def world-40-var
  '#{
     (connects j16 c) (connects c j16)
     }
  )