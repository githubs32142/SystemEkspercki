(clear)
(reset)

(deftemplate Point
(slot answer1 )
(slot answer2 )
(slot answer3 )
(slot answer4 )
(slot answer5 )
(slot answer6 )
)

(defrule Sum1-6
( Point (answer1 ?answer1 )(answer2 ?answer2 )(answer3 ?answer3 ) (answer4 ?answer4 ) (answer5 ?answer5 ) (answer6 ?answer6 ) )
	=>
        (assert (Sum6 (+ ?answer1 ?answer2 ?answer3 ?answer4 ?answer5 ?answer6 )))
)
(defrule rule1
	(Sum6 ?sum6 )(test (> ?sum6 6))
	=>
    ( printout t "1Jesteś osobą silnie uzależnioną od palenia." crlf)
)
(defrule rule2
	(Sum6 ?sum6 )(and(test (> ?sum6 1))(test (< ?sum6 7)) ) 
	=>
    ( printout t " Nie jesteś jeszcze silnie uzależniona/y od nikotyny." crlf)
)
(defrule rule3
	(Sum6 ?sum6 )(test (= ?sum6 0)) 
	=>
    ( printout t " Nie jesteś uzależniona/y od nikotyny." crlf)
)
(facts)
(run)