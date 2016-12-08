(ns fwrap.sweet
  (:refer-clojure :exclude [time]))

(defn time [f]
  (fn [& args]
    (let [fn-name (-> f meta :name)
          start (System/nanoTime)
          r (apply f args)]
      (prn (str fn-name " elapsed time: " (/ (double (- (System/nanoTime) start)) 1000000.0) " msecs"))
      r)))
