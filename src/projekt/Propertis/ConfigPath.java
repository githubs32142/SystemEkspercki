/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Propertis;

/**
 *
 * @author Admin
 */
public class ConfigPath {
    private static final String factorWithAlians="src/projekt/Data/czynniki_ryzyka.txt";
    private static final String factorList="src/projekt/Data/czyniki_ryzyka_list.txt";
     private static final String symptomsList="src/projekt/Data/symptoms.txt";
     private static final String symptomsWithAlians="src/projekt/Data/objawy.txt";
     private static final String famillyWithAlians="src/projekt/Data/rak_rodzina.txt";

    public static String getFactorWithAlians() {
        return factorWithAlians;
    }

    public static String getFactorList() {
        return factorList;
    }

    public static String getSymptomsList() {
        return symptomsList;
    }

    public static String getSymptomsWithAlians() {
        return symptomsWithAlians;
    }

    public static String getFamillyWithAlians() {
        return famillyWithAlians;
    }
}
