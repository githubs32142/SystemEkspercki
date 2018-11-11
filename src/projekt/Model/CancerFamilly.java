package projekt.Model;

import javafx.beans.property.SimpleStringProperty;

/**
 ** Klasa, która dzięki pozwala na działanie tabeli.
 *
 * @author Andrzej Kierepka
 */
public class CancerFamilly {

    private SimpleStringProperty cancer;
    private SimpleStringProperty familly;
/**
 ** Konstruktor z parametrami 
 * @param cancer nazwa nowotworu w postaci ciągu znaków
 * @param familly  pokrewieństwo w rodzinie w postaci ciągu znaków
 */
    public CancerFamilly(String cancer, String familly) {
        this.cancer = new SimpleStringProperty(cancer);
        this.familly = new SimpleStringProperty(familly);
    }
/**
 ** Metoda, która zwraca podany nowotwór 
 * @return nazwa nowotworu w postaci ciągu znaków
 */
    public String getCancer() {
        return cancer.get();
    }
/**
 ** Metoda, która zwraca pokrewieństwo w postaci ciągu znaków 
 * @return pokrewieństwo w rodzinie w postaci ciągu znaków
 */
    public String getFamilly() {
        return familly.get();
    }
/**
 ** Metoda, która pozwala na ustawienie nowotworu podanego jako ciąg znaków 
 * @param cancer nazwa nowotworu w postaci ciągu znaków
 */
    public void setCancer(String cancer) {
        this.cancer = new SimpleStringProperty(cancer);
    }
/**
 ** Metoda, która pozwala na ustawienie pokrewieństwa w rodzienie 
 * @param familly pokrewieństwo w rodzinie w postaci ciągu znaków
 */
    public void setFamilly(String familly) {
        this.familly = new SimpleStringProperty(familly);
    }

}
