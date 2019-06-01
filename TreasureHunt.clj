(ns TreasureHunt)
;use to read the file
(require '[clojure.string :as str])
(defn ReadFile []
  (with-open [rdr (clojure.java.io/reader "map.txt")]
    (reduce conj [] (line-seq rdr)))
  )

(defn PrintFile []
  (def string1 (slurp "map.txt"))

  ;use to print the file on the console
  (println "*****INPUT*****")
  (println string1))

(PrintFile)

(with-open [rdr (clojure.java.io/reader "map.txt")]
  (def rowLen(count (line-seq rdr))) )
(def ncol 13)
(def nrow 8)
;use to split the file in vector
(def a (vec(ReadFile)))
(def getMapValues
  (mapv #(str/split % #"") a))

(def z getMapValues)
;variables declared for using in the definition
(def solved false)
(def wall false)
(def colLen (count (get z 0 0)))
(def x (- 7 1))
(def ncol colLen)
(def nrow rowLen)
(def ab false)
(def opr "ii")
(def nr 1)
(def nc 1)
(def sol false)
(def nz (assoc-in z [0 0] "-" ))
(def v 1)
(if (= opr "col")
  (print "1")
  (if (= opr "row")
    (do (println "3")
        )) )
;defining the function for finding the path
(defn findpath [row col]
  (if (and (< (+ col 1) ncol ) (= (get-in nz [row  (+ col 1) ]) "@"))
    (do
      (def nr (+ nr 1))
      ))
  (if (and (< (+ row 1) nrow ) (= (get-in nz [(+ row 1)  col ]) "@"))
    (do
      (def nc (+ nr 1))
      ))
  (if (and (>= (- col 1) 0 ) (= (get-in nz [row  (- col 1) ]) "@"))
    (do
      (def nc (+ nr 1))

      ))
  (if (and(>= (- row 1) 0) (= (get-in nz [(- row 1) col]) "@"))
    (do
      (def nc (+ nr 1))
      ))
  ;use to check whether the moves can be made at which direction
  (if (and (< (+ col 1) ncol ) (= (get-in z [row  (+ col 1) ]) "@"))
    (do
      (def z (assoc-in z [row col] "+"))
      (def solved true)
      (if (= (get-in z [row  (- col 1) ]) "+")
        (do

          (def v 2)
          (println "found the treasure")
          (dorun (map println z))
          ))
      true

      ))
  (if (and (< (+ row 1) nrow ) (= (get-in z [(+ row 1)  col ]) "@"))
    (do
      (def z (assoc-in z [row col] "+"))
      (def solved true)
      (if (= (get-in z [row  (- col 1) ]) "+")
        (do
          (println "found the treasure")
          (dorun (map println z))
          (def v 2)

          ))
      true
      ))
  (if (and (>= (- col 1) 0 ) (= (get-in z [row  (- col 1) ]) "@"))
    (do
      (def z (assoc-in z [row col] "+"))
      (def solved true)
      ;(let [z (string/join "\n" (map string/join a))] (println z))
      (if (= (get-in z [row  (- col 1) ]) "+")
        (do
          (println "found the treasure")
          (dorun (map println z))
          (def v 2)
          ))
      true

      ))
  (if (and(>= (- row 1) 0) (= (get-in z [(- row 1) col]) "@"))
    (do
      (def z (assoc-in z [row col] "+"))
      (def solved true)
      (if (= (get-in z [row  (- col 1) ]) "+")
        (do
          (println "found the treasure")
          (dorun (map println z))
          (def v 2)
          )
        )
      true

      ))
  (def ab false)
  (def z (assoc-in z [row col] "+"))
  (if (and (>= (- row 1) 0) (or (= (get-in z [(- row 1) col]) "-") (= (get-in z [(- row 1) col]) "-")))
    (findpath (- row 1)  col))
  (if (and (< (+ col 1) ncol ) (or (= (get-in z [row  (+ col 1) ]) "-") (= (get-in z [row  (+ col 1) ]) "-")))
    (findpath row (+ col 1)))
  (if (and (< (+ row 1) nrow ) (or (= (get-in z [(+ row 1)  col ]) "-") (= (get-in z [(+ row 1)  col ]) "-")))
    (findpath (+ row 1)  col))
  (if(and (>= (- col 1) 0 ) (or (= (get-in z [row  (- col 1) ]) "-") (= (get-in z [row  (- col 1) ]) "-")))
    (findpath row (- col 1)))

  (if (= ab false)
    (def z (assoc-in z [row col] "!")))




  )

(findpath 0 0)
(if (= v 1)
  (do(println "cannot find the treasure")

     (dorun (map println z))))

