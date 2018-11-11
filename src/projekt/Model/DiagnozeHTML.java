package projekt.Model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 ** Klasa która pozwala na zapis diagnozy w postaci html-owej.
 *
 * @author Andrzej Kierepka
 */
public class DiagnozeHTML {

    private ObservableList<CancerFamilly> cancerFamilly;
    private Person person;
    private ObservableList<String> dataFactors;
    private ObservableList<String> dataSymptoms;
    public StringBuilder text;
    public StringBuilder textCss;
    private String resultDiagnose;
    private List<String> dianoseCut = new ArrayList();

    public DiagnozeHTML(ObservableList<CancerFamilly> cancerFamilly, Person person, ObservableList<String> dataFactors, ObservableList<String> dataSymptoms) {
        this.cancerFamilly = cancerFamilly;
        this.person = person;
        this.dataFactors = dataFactors;
        this.dataSymptoms = dataSymptoms;
        text = new StringBuilder();
        textCss = new StringBuilder();
    }

    /**
     ** Metoda, która pozwala na ustawienie listy nowotworów w rodzinie
     *
     * @param cancerFamilly lista nowotworów w rodzinie
     */
    public void setCancerFamilly(ObservableList<CancerFamilly> cancerFamilly) {
        this.cancerFamilly = cancerFamilly;
    }

    /**
     ** Metoda, która pozwala na listy czynników ryzyka
     *
     * @param dataFactors lista czynników ryzyka
     */
    public void setDataFactors(ObservableList<String> dataFactors) {
        this.dataFactors = dataFactors;
    }

    /**
     ** Metoda, która pozwala na ustawienie listy symptomów
     *
     * @param dataSymptoms lista symptomów
     */
    public void setDataSymptoms(ObservableList<String> dataSymptoms) {
        this.dataSymptoms = dataSymptoms;
    }

    /**
     ** Metoda, która pozwala na ustawienie danych osoby
     *
     * @param person osoba
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     ** Metoda, która zwraca dane osobowe w tabeli
     *
     * @return tabela w postaci html
     */
    private StringBuilder makePersonInTable() {
        StringBuilder str = new StringBuilder();
        str.append("<table>\n");
        if (person.getEmail().isEmpty()) {
            str.append("<tr><td>Imię</td> <td>").append(person.getName()).append("</td></tr>\n");
            str.append("<tr > <td>Nazwisko</td> <td>").append(person.getSurName()).append("</td></tr>\n");
            str.append("<tr > <td>Wiek</td> <td>").append(person.getAge()).append(" lat</td></tr>\n");
            str.append("<tr > <td>Wzrost</td> <td>").append(person.getHeight()).append(" cm</td></tr>\n");
            str.append("<tr > <td>Waga</td> <td>").append(person.getWeight()).append(" kg</td></tr>\n");
            str.append("<tr > <td>Płeć:</td> <td>").append(person.getSex()).append("</td></tr>\n");
            str.append("<tr ><td>BMI:</td> <td>").append(person.getBmi()).append("</td></tr>\n");
        } else {
            str.append("<tr > <td>E-mail</td> <td>").append(person.getEmail()).append("</td></tr>\n");
            str.append("<tr > <td>Wiek</td> <td>").append(person.getAge()).append(" lat</td></tr>\n");
            str.append("<tr > <td>Wzrost</td> <td>").append(person.getHeight()).append(" cm</td></tr>\n");
            str.append("<tr > <td>Waga</td> <td>").append(person.getWeight()).append(" kg</td></tr>\n");
            str.append("<tr > <td>Płeć:</td> <td>").append(person.getSex()).append("</td></tr>\n");
            str.append("<tr ><td>BMI:</td> <td>").append(person.getBmi()).append("</td></tr>\n");
        }
        str.append("</table>\n");
        // String s = this.getClass().getResource("/projekt/HTML/img/bmi.png").getFile().toString();

//        str.append("<img src =\"src/projekt/HTML/img/bmi.png\" />");
        return str;
    }

    /**
     ** Metoda, która parsuje diagnoze do html
     */
    public void parseHTML() {
        StringBuilder str = new StringBuilder();
        textCss.append("<html>\n");
        parsePersonData(str);
        parseRiskFactorData(str);
        parseSymptomsData(str);
        parseCancerInFamillyData(str);
        createDiagnoseData(str);
        psrseUsingCss(str);
    }

    private void createDiagnoseData(StringBuilder str) {
        str.append("<table>\n");
        str.append("<tr> <td><h1> Wykryte zagrożenia:</h1></td> </tr>\n");
        str.append("</table>");
        str.append(createDiagnose());
        str.append("</body>\n");
        str.append("</html>\n");
    }

    private void parseCancerInFamillyData(StringBuilder str) {
        str.append("<table>\n");
        str.append("<tr> <td><h1> Nowotwór w rodzinie:</h1></td> </tr>\n");
        str.append("</table>");
        str.append(createCancerInFailly());
        str.append("<br/>\n");
    }

    private void parseSymptomsData(StringBuilder str) {
        str.append("<table>\n");
        str.append("<tr> <td><h1> Symptomy które wystąpiły w przeciągu 3 miesięcy:</h1></td> </tr>\n");
        str.append("</table>");
        str.append(createSymptoms());
        str.append("<br/>\n");
    }

    private void parseRiskFactorData(StringBuilder str) {
        str.append("<table>\n");
        str.append("<tr > <td><h1> Czynniki ryzyka:</h1></td> </tr>\n");
        str.append("</table>");
        str.append(createFactor());
        str.append("<br/>\n");
    }

    private void parsePersonData(StringBuilder str) {
        str.append("<html>\n");
        str.append("<meta charset=\"utf-8\"/>\n");
        str.append("<body>\n");
        str.append("<table>\n");
        str.append("<tr > <td><h1> Dane Pacjenta:</h1></td> </tr>\n");
        str.append("</table>");
        str.append(makePersonInTable());
        str.append("<br/>\n");
    }

    private void psrseUsingCss(StringBuilder str) {
        text = str;
        textCss.append("<meta charset=\"utf-8\"/>\n");
        textCss.append("<body>\n");
        //textCss.append(addCSS());
        textCss.append("<table>\n");
        textCss.append("<tr > <td><h1> Dane Pacjenta:</h1></td> </tr>\n");
        textCss.append("</table>");
        textCss.append(makePersonInTable());
        textCss.append("<br/>\n");
        textCss.append("<br/>\n");
        textCss.append("<table>\n");
        textCss.append("<tr> <td><h1> Czynniki ryzyka:</h1></td> </tr>\n");
        textCss.append("</table>");
        textCss.append(createFactor());
        textCss.append("<table>\n");
        textCss.append("<tr> <td><h1> Symptomy które wystąpiły w przeciągu 3 miesięcy:</h1></td> </tr>\n");
        textCss.append("</table>");
        textCss.append(createSymptoms());
        parseCancerInFamillyData(textCss);
        createDiagnoseData(textCss);
    }

    /**
     ** Metoda, która zamienia czynniki ryzyka do tabeli w postaci html
     *
     * @return czynniki ryzyka w postaci w html
     */
    private StringBuilder createFactor() {
        StringBuilder str = new StringBuilder();
        if (dataFactors.size() > 0) {
            str.append("<table>\n");
            for (int i = 0; i < dataFactors.size(); i++) {
                str.append("<tr> <td>").append(dataFactors.get(i)).append("</td></tr>\n");
            }
            str.append("</table>\n");
        }
        return str;
    }

    /**
     ** Metoda, która zwraca symptomy w postaci tabeli html
     *
     * @return symptomy w postaci html
     */
    private StringBuilder createSymptoms() {
        StringBuilder str = new StringBuilder();
        if (dataSymptoms.size() > 0) {
            str.append("<table>\n");
            for (int i = 0; i < dataSymptoms.size(); i++) {
                str.append("<tr> <td>").append(dataSymptoms.get(i)).append("</td></tr>\n");
            }
            str.append("</table>\n");
        }
        return str;
    }

    private StringBuilder createCancerInFailly() {
        StringBuilder str = new StringBuilder();
        if (cancerFamilly.size() > 0) {
            str.append("<table>\n");
            str.append("<tr> <td>Pokrewieństwo </td> <td>Rak w rodzienie </td></tr>\n");
            for (int i = 0; i < cancerFamilly.size(); i++) {
                str.append("<tr> <td>").append(cancerFamilly.get(i).getFamilly()).append("</td>\n <td> ").append(cancerFamilly.get(i).getCancer()).append("</td></tr>\n");
            }
            str.append("</table>\n");
        }
        return str;
    }

    private StringBuilder createDiagnose() {
        StringBuilder str = new StringBuilder();
        if (dianoseCut.size() > 0) {
            str.append("<table>\n");
            for (int i = 0; i < dianoseCut.size(); i++) {
                str.append("<tr> <td>").append(dianoseCut.get(i)).append("</td></tr>\n");
            }
            str.append("</table>\n");
        } else {
            str.append("-");
        }
        return str;
    }

    public void setResultDiagnose(String resultDiagnose) {
        this.resultDiagnose = resultDiagnose;
        cutDiagnose();
    }

    public void cutDiagnose() {
        String tmp = "";
        for (int i = 0; i < resultDiagnose.length(); i++) {
            if (resultDiagnose.charAt(i) == '\n') {
                dianoseCut.add(tmp);
                tmp = "";
            } else {
                tmp += resultDiagnose.charAt(i);
            }
        }
    }

}
