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
    public static final String FACTOR_WITH_ALIANS ="src/projekt/Data/czynniki_ryzyka.txt";
    public static final String FACTOR_LIST ="src/projekt/Data/czyniki_ryzyka_list.txt";
    public static final String SYMPTOMS_LIST ="src/projekt/Data/symptoms.txt";
    public static final String SYMPTOMS_WITH_ALIANS ="src/projekt/Data/objawy.txt";
    public static final String FAMILLY_WITH_ALIANS ="src/projekt/Data/rak_rodzina.txt";


    public static String getSymptomsWithAlians() {
        return SYMPTOMS_WITH_ALIANS;
    }

    public static String getFamillyWithAlians() {
        return FAMILLY_WITH_ALIANS;
    }
}
