
(load "matcher(0.0m)")
(load "ops-search(1b)")
(load "socket")
(load "operators")
(load "planner(1a)")
(load "planner-ops")
(load "planner-ops-warp")
(load "world")
(load "world-warp")

(defn startup [port]
  (set-shrdlu-comms port)
  )

(defn ui-out [& r]
  (apply println r))

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
