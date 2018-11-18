/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Activity;

import projekt.Model.Factor;

import java.util.List;


public class InitializeFactorActivity {

    public static void initFactor( List<Factor> fact) {
        //AuditTest
        fact.add(new Factor("Spożywanie alkoholu", "/projekt/HTML/alkoholizm.html", true, "/projekt/FXML/AuditTest.fxml"));
        // Samo dodaje
        fact.add(new Factor("Otyłość", "/projekt/HTML/otylosc.html", false, ""));
        // IonizingRadiation
        fact.add(new Factor("Promieniowanie jonizujące", "/projekt/HTML/promieniowanie.html", true, "/projekt/FXML/IonizingRadiation.fxml"));
        //  Radiotherapy
        fact.add(new Factor("Radioterapia", "/projekt/HTML/radioterapia.html", true, "/projekt/FXML/Radiotherapy.fxml"));
        // Solarium
        fact.add(new Factor("Lampy solarium", "/projekt/HTML/solarium.html", true, "/projekt/FXML/Solarium.fxml"));
        // Smoking
        fact.add(new Factor("Palenie papierosów", "/projekt/HTML/papierosy.html", true, "/projekt/FXML/SmokingTest.fxml"));
        //  da się zrobić
        fact.add(new Factor("Brak aktywności fizycznej", "/projekt/HTML/aktywnosc_fizyczna.html", false, ""));
        // Diet bez reguł//
        fact.add(new Factor("Niewłaściwa dieta", "/projekt/HTML/brak_owocow.html", false, "/projekt/FXML/Diet.fxml"));
        //
        fact.add(new Factor("Brak naturalnych antyoksydantów", "/projekt/HTML/brak_naturalnych_antyoksydantow.html", false, ""));
        // Menopause 
        fact.add(new Factor("Menopauza + otyłość", "/projekt/HTML/wzrost_bmi.html", true, "/projekt/FXML/Menopause.fxml"));
        // Fibre
        fact.add(new Factor("Brak błonnika", "/projekt/HTML/brak_blonnika.html", true, "/projekt/FXML/Fibre.fxml"));
        // 
        fact.add(new Factor("Pole elektromagnetyczne", "/projekt/HTML/pole_elektromagnetyczne.html", false, "/projekt/FXML/Fibre.fxml"));
        //
        fact.add(new Factor("Promieniwanie ultrafiloetowe", "/projekt/HTML/promieniowanie_ultrafioletowe.html", false, "/projekt/FXML/Fibre.fxml"));
        // AsbestiosTest
        fact.add(new Factor("Kontakt z azbestem", "/projekt/HTML/promieniowanie_ultrafioletowe.html", true, "/projekt/FXML/AsbestosTest.fxml"));
        //Wczesne współżycie  EarlyChAgeController
        fact.add(new Factor("Wczesne współżycie seksualne", "/projekt/HTML/wczesne_wsolzycie.html", true, "/projekt/FXML/EarlyChildhoodAge.fxml"));
                //Wczesne współżycie  EarlyChAgeController
        fact.add(new Factor("Wczesny wiek rodzenia", "/projekt/HTML/wczesne_dziecko.html", true, "/projekt/FXML/EarlyBabe.fxml"));
        //
        fact.add(new Factor("Dieta bogata w tłuszcz", "/projekt/HTML/dieta_w_tluszcze.html", false, ""));
        //
        fact.add(new Factor("Częste spożywanie czerwonego mięsa", "/projekt/HTML/spoz_cz_miesa.html", false, ""));
        //
        fact.add(new Factor("Spożywanie pokarmów smażonych", "/projekt/HTML/spoz_pok_smazonych.html", false, ""));
        //
        fact.add(new Factor("Spożywanie pokarmów grilowanch", "/projekt/HTML/spoz_pok_grill.html", false, ""));
         //
        fact.add(new Factor("Doustna antykoncepcja", "/projekt/HTML/d_antykoncepcja.html", false, ""));
         //
        fact.add(new Factor("Pierwsza miesiączka poniżej 10 r.ż.", "/projekt/HTML/sz_miesiaczka.html", false, ""));
        //
        fact.add(new Factor("Późny wiek rodzenia", "/projekt/HTML/p_wiek_rodzenia.html", false, ""));
        //
        fact.add(new Factor("Wcześniejsze zachorowanie na raka piersi", "/projekt/HTML/w_rak_piersi.html", false, ""));
         //
        fact.add(new Factor("Bezdzietność", "/projekt/HTML/bezdzietnosc.html", false, ""));
    }
    
    
}
