package projekt.Model;

/**
 ** Klasa, która pozwala na zarządzanie czynników ryzyka
 *
 * @author Andrzej Kierepka
 */
public class Factor {

    private String factor;
    private String urlHTML;
    private boolean test;
    private String urlTest;

    public Factor(String factor, String symptom, boolean test, String urlTest) {
        this.factor = factor;
        this.urlHTML = symptom;
        this.test = test;
        this.urlTest = urlTest;
    }

    public Factor(Factor factor) {
        this.factor = factor.factor;
        this.urlHTML = factor.urlHTML;
        this.test = factor.test;
        this.urlTest = factor.urlTest;
    }

    /**
     ** Metoda która sprwdza, czy podany objaw już jest
     *
     * @param factor podany czynnik ryzyka
     * @return prawda, jeżeli się zawiera
     */
    public boolean contains(String factor) {
        return this.factor.equals(factor);
    }

    /**
     ** Metoda, która zwraca czynnik ryzyka
     *
     * @return czynnik ryzyka
     */
    public String getFactor() {
        return factor;
    }

    /**
     ** Metoda, któraz zwraca symptom
     *
     * @return symptom
     */
    public String getUrlHTML() {
        return urlHTML;
    }

    /**
     ** Metoda zwraca, czy czynnik ryzyka posiada test
     *
     * @return true- jeżeli czynnik ryzyka posiada test
     */
    public boolean isTest() {
        return test;
    }

    /**
     ** Metoda, która zwraca ścieżkę do testu
     *
     * @return ścieżka do testu
     */
    public String getUrlTest() {
        return urlTest;
    }

    @Override
    /**
     * Metoda która porównuje instancje z obiektem.
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Factor f = (Factor) o;
        if (factor != null ? !factor.equals(f.factor) : f.factor != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return factor != null ? factor.hashCode() : 0;
    }

}
