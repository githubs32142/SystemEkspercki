(clear)
(reset)

(deftemplate Point
(slot sum )
)

(defrule rule1
	 (Point {sum > 40})
	=>
    ( printout t "1Zbyt duże spożywanie błonnika." crlf)
)
(defrule rule2
	 (Point {sum < 20})
	=>
    ( printout t "1Zbyt małe spożywanie błonnika." crlf)
)
(defrule rule3
	(Point {sum >= 20 && sum <= 40} )
	=>
    ( printout t "Poprawne spożywanie błonnika." crlf)
)

(facts)
(run)