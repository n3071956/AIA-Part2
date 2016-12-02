(load "matcher(0.0m)")
(load "ops-search(1b)")
(load "socket")
(load "operators")
(load "planner(1a)")
(load "planner-ops")
(load "world")
;;(startup-server 2222)


(defn ui-out [& r]
  (apply println r))

;;Test commands

;(planner world '((escaped prisoner true)) planner-operations-prisoner)
;(ops-search world '((escaped prisoner true)) operations-prisoner)