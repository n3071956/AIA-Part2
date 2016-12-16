;(planner world-simple '(on prisoner H) planner-operations-prisoner-simple)

(def world-simple
  '#{
     (connects A B) (connects B A) (watched A false)
     (connects B C) (connects C B) (watched B true)
     (connects C E) (connects E C) (watched C false)
     (connects E H) (connects H E) (watched D false)
     (connects H G) (connects G H) (watched E false)
     (connects G F) (connects F G) (watched F false)
     (connects F D) (connects D F) (watched G false)
     (connects D A) (connects A D) (watched H false)
     (on prisoner A)
     })


(defn in?
  [collection element]
  (some #(= element %) collection))

(def previous (atom (java.util.concurrent.LinkedBlockingDeque.)))

(defn adder [source]
  (.add @previous source) true)

(defn correction [source desti]
  (println "Checking : " source " to " desti)
  (if (nil? (in? (into '() @previous) (list desti source)) )
    (do (.push @previous (list source desti)) true)
    (do () false)))

(def planner-operations-prisoner-simple

  '{
    :move-to-tile
    {:name move-to-tile
     :achieves (on prisoner ?desti)
     :when ((connects ?desti ?source) (connects ?source ?desti) (on prisoner ?location) (watched ?desti false) (:guard (correction  (? source) (? desti))))
     :post ((on prisoner ?source))
     :pre ((on prisoner ?source))
     :add ((on prisoner ?desti))
     :del ((on prisoner ?source))
     :txt (prisoner moved from ?source to ?desti)
     :cmds []
     }
    }
  )
