(def world-simple
  '#{
     (join A B) (join B A)
     (join B C) (join C B)
     (join C E) (join E C)
     (join E H) (join H E)
     (join H G) (join G H)
     (join G F) (join F G)
     (join F D) (join D F)
     (join D B) (join B D)
     (isa A exit)
     (on agent H)

     })

;(def state1
;  '#{(on agent H)
;     })
;
;(def state2
;  '#{(on agent A)
;     })

;(planner (concat world-simple state1) '(on agent A) planner-operations-prisoner-simple)


;(defn correction [current source desti]
;    (println "Current : " current " | Source : " source " | Destination : " desti)
;       (println (first (first (get (ops-search (concat world-simple '#{(on agent source)}) '((on agent current)) legal-next) :cmds)))  )
;          true
;            )


(def previous (atom (java.util.concurrent.LinkedBlockingDeque.)))

(defn correction [current source desti]
          (println "Current : " current " | Source : " source " | Destination : " desti)
          (if (nil? (first @previous))
            (adder source)
            (if (= 0 (compare (first @previous) desti))
              false
              (adder source))))

(defn adder [source]
      (.push @previous source) true)

(def planner-operations-prisoner-simple

  '{
    :move-to-tile
    {:name move-to-tile
     :achieves (on agent ?desti)
     :when ((join ?desti ?source) (join ?source ?desti) (on agent ?location) (:guard (correction  (? location) (? source) (? desti))))
     :post ((on agent ?source))
     :pre ((on agent ?source))
     :add ((on agent ?desti))
     :del ((on agent ?source))
     :txt (agent moved from ?source to ?desti)
     :cmds []
     }

    ;:attempted
    ;{:name attempted
    ; :achieves (tried ?source)
    ; :when ((:guard (adder (? ?source))))
    ; }
    }
  )
;
;(def legal-next
;  '{
;    path-to-tile  {:pre ((on agent ?source)
;                          (join ?desti ?source)
;                          (join ?source ?desti)
;                              )
;                       :add ((on agent ?desti))
;                       :del ((on agent ?source))
;                       :txt (mapped from ?source to ?desti)
;                       :cmd [?desti]
;                       }
;
;    }
;  )

;?target-location) = ?desti
;
;
;:move-to-tile
;{:name move-to-tile
; :achieves (on prisoner ?tilea)
; :when ((connects ?tilea ?tileb) (:not (tried ?tileb ?tilea)))
; :post ((tried ?tilea ?tileb)(on prisoner ?tileb))
; ;;Same as ops
; :pre ((on prisoner ?tileb))
; :add ((on prisoner ?tilea) )
; :del ((on prisoner ?tileb) (tried ?tilea ?tileb) )
; :txt (prisoner moved from ?tileb to ?tilea)
; :cmd [move-to-tile]
; }

;:move-to-tile
;{:name move-to-tile
; :achieves (on agent ?desti)
; :when ((join ?desti ?source) (:not (tried ?desti ?source)))
; :post ((tried ?source ?desti)(on agent ?source))
; :pre ((on agent ?source))
; :add ((on agent ?desti))
; :del ((on agent ?source) (tried ?desti ?source))
; :txt (agent moved from ?source to ?desti)
; :cmds []
; }