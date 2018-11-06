package projekt.Class;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 ** Klasa która odpowiada za sprawdzanie wyrażeń reguralnych.
 * @author Andrzej Kierepka
 */
public class CheckReg {

    /**
     ** Metoda, która sprawdza, czy poprawnie podano adres e-mail
     *
     * @param mail- podany e-mail
     * @return prawda jeżeli podano prawidłowo e-mail.
     */
    public static boolean checkEmail(String mail) {
        Pattern p = Pattern.compile("[a-zA-Z0-9-_.]+@[a-z0-9-.]+.[a-z0-9]{1,4}");
        Matcher matcher = p.matcher(mail);
        return matcher.matches();
    }

    /**
     ** Metoda sprawdza, czy podano imię
     *
     * @param word- imie podano jako ciąg znaków
     * @return true- jeżeli podano prawidłowo imie <br/>
     * Przykłady:<br/>
     * Jan- zwróci prawdę<br/>
     * jan- zwróci fałsz
     */
    public static boolean checkWord(String word) {
        Pattern p = Pattern.compile("[A-Z]{1}[a-z]+");
        Matcher matcher = p.matcher(word);
        return matcher.matches(); //zwraca true lub false
    }

    /**
     ** Metoda, która sprawdza, czy poprawdznie podano wzrost w metrach
     *
     * @param word wzrost podany w metrach.
     * @return prawda- jeżeli prawidłowo podano wzrost<br/>
     * Przykłady: <br/>
     * 1.40 zwróci prawdę<br/>
     * 1,49 zróci fałsz<br/>
     * 1.3 zwróci fałsz <br/>
     * 4.21 zwróci fałsz<br/>
     * 0.32 zwróci prawdę.
     */
    public static boolean checkHeightMetr(String word) {
        Pattern p = Pattern.compile("[0-2]{1}[.]{1}[0-9]{2}");
        Matcher matcher = p.matcher(word);
        return matcher.matches(); //zwraca true lub false
    }

    /**
     ** Metoda, która sprawdza wzrost podany w centymetrach
     *
     * @param word wzrost podany w centymetrach
     * @return prawdę jeżeli wzrost podany poprawnie<br/>
     * Przykłady:<br/>
     * 120 zwróci prawdę<br/>
     * 210 zwróci prawdę <br/>
     */
    public static boolean checkHeightCent(String word) {
        Pattern p = Pattern.compile("[2-2]{1}[0-4]{1}[0-9]{1}|[1-1]{1}[0-9]{1,2}|[1-9]{1}[0-9]{1}");
        Matcher matcher = p.matcher(word);
        return matcher.matches(); //zwraca true lub falSse
    }

    /**
     ** Metoda która sprawdza, czy poprawnie podano wagę w kilogramach 
     * @param word waga 
     * @return prawdę jeżeli wage podano poprawnie<br/>
     * Przyłady:<br/>
     * 12.0 zwróci prawdę<br/>
     * 21,0 zwróci fałsz<br/>
     * 210.213 zwróci prawdę
     */
    public static boolean checkWeight(String word) {
        Pattern p = Pattern.compile("[1-9]{1}[0-9]{1}[.]{1}[0-9]*|[1]{1}[0-9]{1,2}[.]{1}[0-9]*|[2]{1}[0-4]{1}[0-9]{1}[.]{1}[0-9]*");
        Matcher matcher = p.matcher(word);
        return matcher.matches(); //zwraca true lub false
    }
    /**
     ** Metoda która sprawdza, czy poprawnie podano wagę w kilogramach 
     * @param word waga 
     * @return prawdę jeżeli wage podano poprawnie
     * <br/>
     * Przyłady:<br/>
     * 12 zwróci prawdę<br/>
     * 210s zwróci prawdę
     */
    public static boolean checkWeight2(String word) {
        Pattern p = Pattern.compile("[1-9]{1}[0-9]{1,2}");
        Matcher matcher = p.matcher(word);
        return matcher.matches(); //zwraca true lub false
    }
    /**
     ** Metoda która sprawdza,czy podano poprawnie wiek 
     * @param word podany wiek
     * @return prawdę jeżeli wage podano poprawnie
     */
    public static boolean checkAge(String word) {
        Pattern p = Pattern.compile("[1-9]{1}|[1-9]{1}[0-9]{1}|[1]{1}[0-4]{1}[0-9]{1}");
        Matcher matcher = p.matcher(word);
        
        return matcher.matches(); //zwraca true lub false
    }
}
