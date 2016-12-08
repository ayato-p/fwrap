(ns fwrap.wrapper)

(defmacro wrap-var! [v wrap-f]
  `(alter-var-root
    ~v
    (fn [f#]
      (~wrap-f (with-meta f# (meta ~v))))))

(defmacro wrap-ns! [ns-sym wrap-f]
  `(temp/do-template
    [v#]
    (wrap-var! v# ~wrap-f)
    ~@(vals (ns-interns ns-sym))))

(defmacro wrap! [ns-vec wrap-f]
  `(temp/do-template
    [ns#]
    (wrap-ns! ns# ~wrap-f)
    ~@ns-vec))
