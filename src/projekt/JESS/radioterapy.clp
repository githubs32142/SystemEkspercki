(clear)
(reset)

(deftemplate Point
(slot answer1 )
)


(defrule rule1
	(Point (answer1 1))
	=>
    ( printout t "1Ryzyko choroby nowotworowej w okoliy naświetlenia." crlf)
)
(defrule rule2
	(Point (answer1 0))
	=>
    ( printout t "Nie stwierdzono ryzyka choroby nowotworowej." crlf)
)


(facts)
(run)