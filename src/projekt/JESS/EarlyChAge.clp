(clear)
(reset)

(deftemplate Point
(slot sum )
)

(defrule rule1
	 (Point {sum > 0 })
	=>
    ( printout t "1Zbyt wczesna inicjacja seksualna" crlf)
)
(defrule rule2
	 (Point {sum  < 1 } )
	=>
    ( printout t "Brak zagrożenia zbyt wczesnej inicjacji seksualnej." crlf)
)

(facts)
(run)