(defn strips-solver [state goal goal-ops])

(load "matcher(0.0m)")
(load "ops-search(1b)")

(load "socket")

(load "operators")
(load "planner(1a)")
(load "planner-ops")
(load "world")
(load "wrappers-1a")
(load "definitions(1c)")
(load "parsing-1a")

;;Test commands - planner
;---move
;(planner world '(on prisoner j2) planner-operations-prisoner)
;(planner world '(on prisoner j7) planner-operations-prisoner)
;---goal state
;(planner world '(escaped prisoner true) planner-operations-prisoner)

;(get (ops-search world '((escaped prisoner true)) operations-prisoner) :cmds)