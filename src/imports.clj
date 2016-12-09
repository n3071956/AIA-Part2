(load "matcher(0.0m)")
(load "ops-search(1b)")
(load "socket")
(load "operators")
(load "planner(1a)")
(load "planner-ops")
(load "world")
(load "world2")
(load "breadth-first")

(defn ui-out [& r]
  (apply println r))

;;Test commands - planner
;---move
;(planner world '(on prisoner j2) planner-operations-prisoner)
;(planner world '(on prisoner j7) planner-operations-prisoner)
;---goal state
;(planner world '(escaped prisoner true) planner-operations-prisoner)