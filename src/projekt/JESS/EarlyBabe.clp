(clear)
(reset)

(deftemplate Point
(slot sum )
)

(defrule rule1
	 (Point {sum > 0 })
	=>
    ( printout t "1Zbyt wczesny wiek rodzenia" crlf)
)
(defrule rule2
	 (Point {sum  < 1 } )
	=>
    ( printout t "Brak zagrożenia zbyt wczesnego rodzenia." crlf)
)

(facts)
(run)