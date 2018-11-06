(clear)
(reset)

(deftemplate Point
(slot answer1 )
(slot answer2 )
(slot answer3 )
)

(defrule Sum
( Point (answer1 ?answer1 )(answer2 ?answer2 )(answer3 ?answer3 ))
	=>
        (assert (Sum (+ ?answer1 ?answer2 ?answer3  )))
)

(defrule rule1
	(Sum ?sum )(test (> ?sum 1))
	=>
    ( printout t "1Ryzyko nowotworu skóry." crlf)
)
(defrule rule2
	(Sum ?sum )(test (< ?sum 2))
	=>
    ( printout t "Brak prawdopodobieństwa nowotworu skóry." crlf)
)

(facts)
(run)