(clear)
(reset)

(deftemplate Point
(slot answer1 )
(slot answer2 )

)

(defrule Sum
( Point (answer1 ?answer1 )(answer2 ?answer2 ))
	=>
        (assert (Sum (+ ?answer1 ?answer2  )))
)

(defrule rule1
	(Sum ?sum )(test (> ?sum 0))
	=>
    ( printout t "1Potencjalne ryzyko nowotworu." crlf)
)
(defrule rule2
	(Sum ?sum )(test (= ?sum 0))
	=>
    ( printout t "Brak diagnozy." crlf)
)

(facts)
(run)