(clear)
(reset)

(deftemplate Point
(slot answer1 )
(slot answer2 )
(slot answer3 )
(slot answer4 )
(slot answer5 )
(slot answer6 )
(slot answer7 )
(slot answer8 )
(slot answer9 )
(slot answer10 )
(slot answer11 )
(slot answer12 )
(slot answer13 )
(slot answer14 )
)

(defrule Sum1-4
( Point (answer1 ?answer1 )(answer2 ?answer2 )(answer3 ?answer3 ) (answer4 ?answer4 ) )
	=>
        (assert (Sum4 (+ ?answer1 ?answer2 ?answer3 ?answer4 )))
)

(defrule Sum5-7
( Point (answer5 ?answer5 )(answer6 ?answer6 )(answer7 ?answer7 ) )
	=>
        (assert (Sum3 (+ ?answer5 ?answer6 ?answer7  )))
)
(defrule Sum8-14
( Point (answer8 ?answer8 )(answer9 ?answer9 )(answer10 ?answer10 ) (answer11 ?answer11 ) (answer12 ?answer12 ) (answer13 ?answer13 ) (answer14 ?answer14 ))
	=>
        (assert (Sum7  (+ ?answer8 ?answer9 ?answer10 ?answer11 ?answer12 ?answer13  )  ) ) 
)
(defrule CAGE1
	(Sum4 ?sum4 )(test (> ?sum4 0))
	=>
    ( printout t "1Test CAGE: Prawdopodobieństwo istnienia uzależnienia od alkoholu." crlf)
)
(defrule CAGE2
	(Sum4 ?sum4 )(test (= ?sum4 0))
	=>
    ( printout t "Test CAGE: Brak prawdopodobieństwa uzależnienia od alkoholu." crlf)
)

(defrule AUDIT1
	(Sum3 ?sum3 )(test (> ?sum3 7))
	=>
    ( printout t "1Test AUDIT: Zagrożenie zdrowia spozywaniem alkoholu. " crlf)
)
(defrule AUDIT2
	(Sum3 ?sum3 )(test (< ?sum3 8))
	=>
    ( printout t "Test AUDIT: Brak prawdopodobieństwa uzależnienia od alkoholu." crlf)
)

(defrule Screener1 
	(Sum7 ?sum7 )(test (> ?sum7 14 ))
	=>
    ( printout t "1Test Przesiewowy: Ryzykowne picie. " crlf)
)

(defrule Screener2 
	(Sum7 ?sum7 )(test (< ?sum7 15))
	=>
    ( printout t "Test Przesiewowy: Brak prawdopodobieństwa uzależnienia od alkoholu. " crlf)
)

(facts)
(run)