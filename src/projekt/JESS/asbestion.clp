(clear)
(reset)

(deftemplate Point
(slot sum )
)

(defrule rule1
	 (Point {sum > 0 })
	=>
    ( printout t "1Zagrożenie kontaktem z azbestem." crlf)
)
(defrule rule2
	 (Point {sum  < 1 } )
	=>
    ( printout t "Brak zagrożenia kontaktem z azbstem." crlf)
)

(facts)
(run)