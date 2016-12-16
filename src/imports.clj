
(load "matcher(0.0m)")
(load "ops-search(1b)")
(load "socket")
(load "operators")
(load "planner(1a)")
(load "planner-ops")
(load "planner-ops-warp")
(load "world")
(load "worlds")
(load "world-warp")
(load "planner_ops_simple")

(defn ui-out [& r]
  (apply println r))


(defn startup [port]
  (set-shrdlu-comms port)
  )

(defn o-search [world]
  (ops-search world '((escaped prisoner true)) operations-prisoner)
  )

(defn p-search [world]
  (planner world '(escaped prisoner true) operations-prisoner)
  )

(defn timed-o-search [world]
  (time (o-search world)))

(defn timed-p-search [world]
  (time (p-search world)))

(defn worldlist []
  [(set (concat world-9 world-9-var))
   (set (concat world-9 world-14 world-14-var))
   (set (concat world-9 world-14 world-19 world-19-var))
   (set (concat world-9 world-14 world-19 world-22 world-22-var))
   (set (concat world-9 world-14 world-19 world-22 world-27 world-27-var))
   (set (concat world-9 world-14 world-19 world-22 world-27 world-29 world-29-var))
   (set (concat world-9 world-14 world-19 world-22 world-27 world-29 world-34 world-34-var))
   (set (concat world-9 world-14 world-19 world-22 world-27 world-29 world-34 world-37 world-37-var))
   (set (concat world-9 world-14 world-19 world-22 world-27 world-29 world-34 world-37 world-40 world-40-var))
   ]
  )

;(load "definitions(1c)")

;;Test commands - planner
;---move
;(planner world '(on prisoner j2) planner-operations-prisoner)
;(planner world '(on prisoner j7) planner-operations-prisoner)
;---goal state
;(planner world '(escaped prisoner true) planner-operations-prisoner)

;(get (ops-search world '((escaped prisoner true)) operations-prisoner) :cmds)

;(load "imports")
;(startup 2222)
;(map nlogo-send-exec (map nlogo-translate-cmd (get (ops-search world (quote ((escaped prisoner true))) operations-prisoner) :cmds)))

;(time (ops-search world (quote ((escaped prisoner true))) operations-prisoner))
;(nlogo-send-exec (ops-search world (quote ((escaped prisoner true))) operations-prisoner))
