(clear)
(reset)

(deftemplate Point
(slot answer1 )
(slot answer2 )
(slot answer3 )
(slot answer4 )

)

(defrule Sum
( Point (answer1 ?answer1 )(answer2 ?answer2 )(answer3 ?answer3 )(answer4 ?answer4 ))
	=>
        (assert (Sum (+ ?answer1 ?answer2 ?answer3 ?answer4  )))
)

(defrule rule1
	(Sum ?sum )(test (> ?sum 0))
	=>
    ( printout t "1Potencjalne ryzyko nowotworu." crlf)
)
(defrule rule2
	(Sum ?sum )(test (= ?sum 0))
	=>
    ( printout t "Brak zagrożenia czynnikiem ryzyka." crlf)
)
(facts)
(run)