(clear)
(reset)

(deftemplate Person
(slot age)
)

(deftemplate Rakpluc
(slot istnieje))

(deftemplate Rakjelitagrubego
(slot istnieje))

(deftemplate Rakgruczolukrokowy
(slot istnieje))

(deftemplate Rakpiersi
(slot istnieje))

(deftemplate Rakskory
(slot istnieje))

(deftemplate Rakjajnikow
(slot istnieje))

(deftemplate Rakzoloadka
(slot istnieje))

(deftemplate RiskFactor
(slot sp_alkohol)
(slot otylosc)
(slot pro_jonizujace)
(slot radioterapia)
(slot lam_solarium)
(slot pal_papierosow)
(slot br_akt_fizycznej)
(slot nie_dieta)
(slot br_nat_antykoksydantow)
(slot menopazua_otylosc)
(slot br_blonnika)
(slot pol_elektromagnetyczne)
(slot prom_ultra)
(slot kon_azbest)
(slot wcz_wsp_sexualne)
(slot wcz_rodz)
(slot diet_w_tluszcze)
(slot cz_sp_czerw_mieso)
(slot spoz_pok_smazonych)
(slot spoz_pok_grill)
(slot dos_antykoncepcja)
(slot sz_miesiacza)
(slot poz_w_rodzenia)
(slot w_r_piersi)
(slot bezdzietnosc)
)
(deftemplate Symptoms
(slot goraczka)
(slot oslabienie)
(slot blad_skory)
(slot apatia)
(slot stan_podgoraczkowe)
(slot os_apetyt)
(slot dusznosc)
(slot kr_jam_ustna)
(slot slk_infekcj)
(slot ob_poty)
(slot mdlosci)
(slot cz_bol_glowy)
(slot cz_bol_brzucha)
(slot sw_skory)
(slot pow_wezly_chlonne)
(slot pokaslywanie)
(slot chudniecie)
(slot kr_stolc)
(slot sl_stolcu)
(slot zm_tr_wypozniania)
(slot wycz_guz_krok)
(slot as_gr_krok)
(slot guz_w_piersi)
(slot as_piersi)
(slot n_do_miesa)
(slot kr_mocz)
(slot zm_skora)
(slot sk_na_piersi)
(slot wciek_brodawka)
(slot niet_krwawienie)
)

(deftemplate FamillyCancer
(slot brat_pluc)
(slot brat_jelito)
(slot brat_piersi)
(slot brat_jader)
(slot brat_gru_krok)
(slot brat_mozg)
(slot brat_syjki_macic)
(slot brat_trzustka)
(slot brat_zoladka)
(slot brat_krtani)
(slot brat_jajnik)
(slot siostra_pluc)
(slot siostra_jelito)
(slot siostra_piersi)
(slot siostra_jader)
(slot siostra_gru_krok)
(slot siostra_mozg)
(slot siostra_syjki_macic)
(slot siostra_trzustka)
(slot siostra_zoladka)
(slot siostra_krtani)
(slot siostra_jajnik)
(slot ojciec_pluc)
(slot ojciec_jelito)
(slot ojciec_piersi)
(slot ojciec_jader)
(slot ojciec_gru_krok)
(slot ojciec_mozg)
(slot ojciec_syjki_macic)
(slot ojciec_trzustka)
(slot ojciec_zoladka)
(slot ojciec_krtani)
(slot ojciec_jajnik)
(slot matka_pluc)
(slot matka_jelito)
(slot matka_piersi)
(slot matka_jader)
(slot matka_gru_krok)
(slot matka_mozg)
(slot matka_syjki_macic)
(slot matka_trzustka)
(slot matka_zoladka)
(slot matka_krtani)
(slot matka_jajnik)
(slot dziadek_pluc)
(slot dziadek_jelito)
(slot dziadek_piersi)
(slot dziadek_jader)
(slot dziadek_gru_krok)
(slot dziadek_mozg)
(slot dziadek_syjki_macic)
(slot dziadek_trzustka)
(slot dziadek_zoladka)
(slot dziadek_krtani)
(slot dziadek_jajnik)
(slot babcia_pluc)
(slot babcia_jelito)
(slot babcia_piersi)
(slot babcia_jader)
(slot babcia_gru_krok)
(slot babcia_mozg)
(slot babcia_syjki_macic)
(slot babcia_trzustka)
(slot babcia_zoladka)
(slot babcia_krtani)
(slot babcia_jajnik)
(slot wujek_pluc)
(slot wujek_jelito)
(slot wujek_piersi)
(slot wujek_jader)
(slot wujek_gru_krok)
(slot wujek_mozg)
(slot wujek_syjki_macic)
(slot wujek_trzustka)
(slot wujek_zoladka)
(slot wujek_krtani)
(slot wujek_jajnik)
(slot ciotka_pluc)
(slot ciotka_jelito)
(slot ciotka_piersi)
(slot ciotka_jader)
(slot ciotka_gru_krok)
(slot ciotka_mozg)
(slot ciotka_syjki_macic)
(slot ciotka_trzustka)
(slot ciotka_zoladka)
(slot ciotka_krtani)
(slot ciotka_jajnik)
)



; ############Reguły dotyczące raka płuc###########
(defrule cancerp00
         (and (RiskFactor (pal_papierosow 1) ) (exists (or (Symptoms (goraczka 1)) (Symptoms(pokaslywanie 1)) (Symptoms(chudniecie 1))  (Symptoms(oslabienie 1)  ))))
	=>
	 (assert (Rakpluc (istnieje 1)))
)
(defrule cancerp01
         (and (RiskFactor (pal_papierosow 1) ) (exists (and (Symptoms(pokaslywanie 1)) (Symptoms(chudniecie 1))  )))
	=>
	 (assert (Rakpluc (istnieje 1)))
)
(defrule cancerp02
         (and (RiskFactor (pal_papierosow 1) ))
	=>
	(assert (Rakpluc (istnieje 1)))
)

(defrule cancerp03
          (and (Symptoms (goraczka 1)) (Symptoms(pokaslywanie 1)) (Symptoms(chudniecie 1))  (Symptoms(oslabienie 1)  ))
	=>
	 (assert (Rakpluc (istnieje 1)))
)
(defrule cancerp04
    (Rakpluc (istnieje 1))
    =>
    (printout t "Istnieje prawdopodobieństwo zachorowania na raka płuc. Zalecenia:Wykonać badanie RTG lub TK klatki piersiowej"crlf )

)

; ###############Reguły dotycz±ce raka jelita grubego###############

(defrule cancerjg00
	( RiskFactor (otylosc ?ans1 )(diet_w_tluszcze ?ans2 )(br_akt_fizycznej ?ans3 ) (cz_sp_czerw_mieso ?ans4 ) (spoz_pok_smazonych ?ans5 ) (spoz_pok_grill ?ans6 ))
	=>
	(assert (Sumcjg (+ ?ans1 ?ans2 ?ans3 ?ans4 ?ans5 ?ans6 )))
)

(defrule cancerjg01
	(Sumcjg ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakjelitagrubego (istnieje 1)))
)


(defrule cancerjg02
	(Person ( age ?age ) )(test (> ?age 49))
 =>
        (assert (Sum (+ 0 1)))
)

(defrule cancerjg03
	(and (Sumcjg ?sum6 ) (Sum ?sum))
	=>
	(assert (Sumcjg01 (+ ?sum6  ?sum )))
)

(defrule cancerjg04
	(Sumcjg01 ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakjelitagrubego (istnieje 1)))
)

(defrule cancerjg05
	( FamillyCancer (brat_jelito ?ans1 )(siostra_jelito ?ans2 )(ojciec_jelito ?ans3 ) (matka_jelito ?ans4 ) )
	=>
	(assert (SumJGG (+ ?ans1 ?ans2 ?ans3 ?ans4  )))
)

(defrule cancerjg06
	(SumJGG  ?tmp )(test (> ?tmp 0))
 =>
        (assert (Sum2 (+ 0 1)))
)

(defrule cancerjg07
	(and (Sumcjg ?sum6 ) (Sum2 ?sum2))
	=>
	(assert (Sumcjg02 (+ ?sum6  ?sum2 )))
)

(defrule cancerjg08
	(Sumcjg02 ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakjelitagrubego (istnieje 1)))
)

(defrule cancerjg09
	(and (Sumcjg ?sum6 ) (Sum ?sum) (Sum2 ?sum2) )
	=>
	(assert (Sumcjg03 (+ ?sum6  ?sum ?sum2 )))
)

(defrule cancerjg10
	(Sumcjg03 ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakjelitagrubego (istnieje 1)))
)

(defrule cancerjg11
 (or( and (or (FamillyCancer (brat_jelito 1))(FamillyCancer (siostra_jelito 1))) 
 (or (FamillyCancer (ojciec_jelito 1))(FamillyCancer (matka_jelito 1))))
 ( and (or (FamillyCancer (ojciec_jelito 1))(FamillyCancer (matka_jelito 1))) 
 (or (FamillyCancer (dziadek_jelito 1))(FamillyCancer (babcia_jelito 1)))) )
	=>
	 (assert (Sum2Pok (+ 0 1)))
)
(defrule cancerjg12
	(and (Sumcjg ?sum6 ) (Sum2Pok ?s2p) )
	=>
	(assert (Sumcjg04 (+ ?sum6  ?s2p )))
)

(defrule cancerjg13
	(Sumcjg04 ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakjelitagrubego (istnieje 1))) 
)

(defrule cancerjg14
	(and (Sumcjg ?sum6 ) (Sum ?sum) (Sum2Pok ?s2p) )
	=>
	(assert (Sumcjg05 (+ ?sum6 ?sum  ?s2p )))
)

(defrule cancerjg15
	(Sumcjg05 ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakjelitagrubego (istnieje 1))) 
)
(defrule cancerjg16
	(and (Sumcjg ?sum6 ) (Sum2 ?sum2) (Sum2Pok ?s2p) )
	=>
	(assert (Sumcjg06 (+ ?sum6 ?sum2  ?s2p )))
)

(defrule cancerjg17
	(Sumcjg06 ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakjelitagrubego (istnieje 1))) 
)

(defrule cancerjg18
	(and (Sumcjg ?sum6 ) (Sum ?sum) (Sum2 ?sum2) (Sum2Pok ?sum2p))
	=>
	(assert (Sumcjg07 (+ ?sum6  ?sum ?sum2 ?sum2p)))
)

(defrule cancerjg19
	(Sumcjg07 ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakjelitagrubego (istnieje 1)))
)
(defrule cancerjg20
         (and (Symptoms (zm_tr_wypozniania 1) ) (exists (or (Symptoms(kr_stolc 1)) (Symptoms(sl_stolcu 1))  )))
	=>
	 (assert (Rakjelitagrubego (istnieje 1)))
)

(defrule cancerjg21
          (exists (and (Symptoms(kr_stolc 1)) (Symptoms(sl_stolcu 1))  ))
	=>
	(assert (Rakjelitagrubego (istnieje 1)))
)

(defrule cancerjg22
    (Rakjelitagrubego (istnieje 1))
    =>
    (printout t "Prawdopodobieństwo zachorowania na raka jelita grubego. Zalecenia: Wykonać kolonoskopie, badanie kału na krew utajoną, sigmoidoskopia. "crlf )

)

; ###############Reguły dotyczące raka piersi #####################
(defrule cancerpp00
	( RiskFactor (dos_antykoncepcja ?ans1 )(sz_miesiacza ?ans2 )(poz_w_rodzenia ?ans3 ) (br_akt_fizycznej ?ans4 ) (w_r_piersi ?ans5 ) (sp_alkohol ?ans6 ) (menopazua_otylosc ?ans7 ) )
	=>
	(assert (Sumpp00 (+ ?ans1 ?ans2 ?ans3 ?ans4 ?ans5 ?ans6 ?ans7 )))
)

(defrule cancerpp01
	(Sumpp00 ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakpiersi (istnieje 1)))
)

(defrule cancerpp02
	(and (Sumpp00 ?sumpp ) (Sum ?sum) )
	=>
	(assert (Sumpp01 (+ ?sumpp  ?sum  )))
)

(defrule cancerpp03
	(Sumpp01 ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakpiersi (istnieje 1)))
)

(defrule cancerpp04
          (exists (or (Symptoms (guz_w_piersi 1)) (Symptoms(as_piersi 1)) (Symptoms(sk_na_piersi 1))  (Symptoms(wciek_brodawka 1)  )))
	=>
	 (assert (Rakpiersi (istnieje 1)))
)

(defrule cancerpp05
    (Rakpiersi (istnieje 1))
    =>
    (printout t "Istnieje ryzyko zachorowania na raka piersi. Zalecienia: Wykonć badanie USG piersi, resonans magnetyczny, oznaczenie markera CA-125."crlf )

)

;############## Reguły dotyczące raka gruczołu krokowego#####################

(defrule cancergk00
	( RiskFactor (br_akt_fizycznej ?ans1 )(cz_sp_czerw_mieso ?ans2 )(otylosc ?ans3 ) (pal_papierosow ?ans4 )  )
	=>
	(assert (Sum9 (+ ?ans1 ?ans2 ?ans3 ?ans4  )))
)

(defrule cancergk01
	(Person ( age ?age ) )(test (> ?age 59))
 =>
        (assert (SumAge2 (+ 0 1)))
)

(defrule cancergk02
	(Sum9  ?sm99 )(test (> ?sm99 2))
 =>
        (assert (Rakgruczolukrokowy (istnieje 1)))
)

(defrule cancergk03
	(Sum9 ?s99 ) (SumAge2 ?sag2)
 =>
        (assert (sumcgk02 (+ ?s99  ?sag2)))
)
(defrule cancergk04
	(sumcgk02  ?smgk )(test (> ?smgk 2))
 =>
        (assert (Rakgruczolukrokowy (istnieje 1)))
)

(defrule cancergk05
	( FamillyCancer (brat_gru_krok ?ans1 )(ojciec_gru_krok ?ans2 ) )
	=>
	(assert (SumGK (+ ?ans1 ?ans2   )))
)

(defrule cancergk06
	(SumGK  ?sgk )(test (> ?sgk 0))
 =>
        (assert (SumFamilly2 (+ 0 1)))
		
)

(defrule cancergk07
	(Sum9 ?s9a ) (SumFamilly2 ?sfmc)
 =>
        (assert (sumcgk03 (+ ?s9a  ?sfmc)))
)
(defrule cancergk08
	(sumcgk03  ?smgk3 )(test (> ?smgk3 2))
 =>
        (assert (Rakgruczolukrokowy (istnieje 1)))
)

(defrule cancergk09
	 (Sum9 ?su9 ) (SumAge2 ?sumage2) (SumFamilly2 ?sf2)
	=>
	(assert (cancerc (+ ?su9  ?sumage2 ?sf2 )))
)

(defrule cancergk10
	(cancerc ?as )(test (> ?as 0))
	=>
	(assert (Rakgruczolukrokowy (istnieje 1)))
)

(defrule cancergk11
        (and (Symptoms(wycz_guz_krok 1)) (Symptoms(as_gr_krok 1))  )
	=>
	 (assert (Rakgruczolukrokowy (istnieje 1)))
)

(defrule cancergk12
     (Rakgruczolukrokowy (istnieje 1))
    =>
    (printout t "Potencjalne zagrożenie raka gruczołu krokowego. Zalecenia: badanie palpacyjne, markery PSA. "crlf )

)

; Rak jajników

(defrule cancerj00
	( FamillyCancer (ciotka_jajnik ?ans1 )(babcia_jajnik ?ans2 )(matka_jajnik ?ans3 ) (siostra_jajnik ?ans4 ) )
	=>
	(assert (Sumj00 (+ ?ans1 ?ans2 ?ans3 ?ans4)))
)

(defrule cancerj01
	(Sumj00 ?tmp )(test (> ?tmp 1))
	=>
	(assert (Sumj02 (+ 0 1)))
)

(defrule cancerj02
	( RiskFactor (bezdzietnosc ?ans1 )(otylosc ?ans2 ) )
	=>
	(assert (Sumj01 (+ ?ans1 ?ans2 )))
)

(defrule cancerj03
	(and (Sumj01 ?sumj1 ) (Sumj02 ?sumj2) )
	=>
	(assert (Sumj03 (+ ?sumj1  ?sumj2)))
)

(defrule cancerj04
	(Sumj03 ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakjajnikow (istnieje 1)))
)


(defrule cancerj05
	(and (SumAge2 ?sumage2) (Sumj03 ?sumj3))
	=>
	(assert (Sumj04 (+   ?sumage2 ?sumj3 )))
)

(defrule cancerj06
	(Sumj04 ?tmp )(test (> ?tmp 2))
	=>
	(assert (Rakjajnikow (istnieje 1)))
)


(defrule cancerj07
	(and (Symptoms (niet_krwawienie 1)))
	=>
	(assert (Rakjajnikow (istnieje 1)))
)

(defrule cancerj08
    (Rakjajnikow (istnieje 1))
    =>
    (printout t "Prawdopodobienstwo ryzyka raka jajnika. Zalecenia: badanie cytologiczne, USG przezpochwowe, markey CA-125."crlf )

)

;rak żołądla nie wszystko

(defrule cancerz00
	( RiskFactor (otylosc ?ans1 ) (pal_papierosow ?ans2 )  )
	=>
	(assert (Sumcz5 (+ ?ans1 ?ans2 )))
)
(defrule cancerz01
	( Symptoms (chudniecie ?ans1 ) (os_apetyt ?ans2 ) (n_do_miesa ?ans3 ) )
	=>
	(assert (Sumcz6 (+ ?ans1 ?ans2 ?ans3 )))
)
(defrule cancerz02
	(Sumcz5 ?tmp )(test (> ?tmp 1))
	=>
	(assert (RuleZZ0 (+ 0 1)))
)

(defrule cancerz03
	(Sumcz6 ?tmp )(test (> ?tmp 1))
	=>
	(assert (RuleZZ1 (+ 0 1)))
)

(defrule cancerz04
	(and (RuleZZ1 ?zz1 ) (RuleZZ0 ?zz0) )
	=>
	(assert (CancerZZ (+ ?zz1  ?zz0  )))
)
(defrule cancerz05
	(and (or( RiskFactor (otylosc 1 )) ( RiskFactor (pal_papierosow 1) ) )(and ( Symptoms (chudniecie 1 )) ( Symptoms (os_apetyt 1 )) ( Symptoms (n_do_miesa 1 )) ))
	=>
	(assert (Rakzoloadka (istnieje 1)))
)

(defrule cancerz06
	(CancerZZ ?tmp )(test (> ?tmp 1))
	=>
	(assert (Rakzoloadka (istnieje 1)))
)

(defrule cancerz07
    (Rakzoloadka (istnieje 1))
    =>
    (printout t "Potencjalne zagrożenie rakiem żołąkda. Zalecenia: badanie endoskopowe. "crlf )

)

;########################Rak pęcherza moczoweego #############################
(defrule cancerpm00
         (and (RiskFactor (pal_papierosow 1)) (Symptoms(kr_mocz 1)) (Symptoms(oslabienie 1)) )
	=>
	 (printout t "Potencjalne zagrożenie rakiem pęcherza moczoweego. Zalecenia: badanie USG oraz badania cytologiczne"  crlf)
)

;########################Rak skóry #############################
(defrule cancers00
         (or (RiskFactor (lam_solarium 1)) (RiskFactor(prom_ultra 1))  )
	=>
	 (assert (Rakskory (istnieje 1)))
)

(defrule cancers01
         (or  (Symptoms(zm_skora 1))  )
	=>
	 (assert (Rakskory (istnieje 1)))
)

(defrule cancers02
    (Rakskory (istnieje 1))
    =>
    (printout t "Potencjalne zagrożenie raka skóry. Zalecenia: badanie deroskopem "crlf )

)

; ##############
(facts)
(run)
