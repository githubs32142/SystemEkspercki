package projekt.Controller.Test;

import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import jess.JessException;
import jess.Rete;
import org.controlsfx.control.Notifications;
import projekt.Controller.MainWindow.FactorWindowController;

/**
 * FXML Controller class
 *
 * @author Andrrzej Kierepka
 */
public class AuditTest implements Initializable {

    double pr;
    private FactorWindowController window;
    @FXML
    private Label question;
    @FXML
    private RadioButton answer1;
    @FXML
    private ToggleGroup tg;
    @FXML
    private RadioButton answer2;
    @FXML
    private RadioButton answer3;
    @FXML
    private RadioButton answer4;
    @FXML
    private RadioButton answer5;
    @FXML
    private Button next;
    private int index;
    private boolean end = false;// kiedy klikneliśmy na koniec(wystaw diagnozę)
    List<String> qList = new ArrayList<>();// lista pytań
    List<String> a1List = new ArrayList<>();
    List<String> a2List = new ArrayList<>();
    List<String> a3List = new ArrayList<>();
    List<String> a4List = new ArrayList<>();
    List<String> a5List = new ArrayList<>();// lista odpowiedzi na 5 pytanie
    List<Integer> pList = new ArrayList<>();// ilość punktów przyznanych za każdą odpowiedź
    @FXML
    private ProgressBar progres;
    @FXML
    private Label text;

    /**
     *
     * Inicjalizacja kontriolera
     *
     * @param url wskaźnik do "zasobu" w sieci World Wide Web
     * @param rb wersja językowa
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        index = 0;
        qList.add("1. Czy kiedykolwiek miałeś poczucie, że powinieś ograniczyć picie?");
        qList.add("2. Czy zdarzyło się, że ktoś Cię zdenerwował, krytykując za picie?");
        qList.add("3. Czy czułeś się źle lub miałeś poczucie winy z powodu swojego picia?");
        qList.add("4. Czy rozpoczynałeś dzień od wypicia rana alkoholu dla uspokojenia lub dla złagodzenia kaca?");
        qList.add("5. Jak często pijesz napoje alkoholowe?");
        qList.add("6. Jak dużo porcji napojów alkoholowych wypijasz typowego dnia, kiedy pijesz?");
        qList.add("7. Jak często wypijasz 6 lub więcej drinków za jednym razem?");
        qList.add("8. Jak często w ciągu minionego roku okazywało się, że nie byłeś w stanie przerwać picia, gdy już zacząłeś?");
        qList.add("9. Jak często w minionym roku z powodu picia nie zrobiłeś czegoś, co normalnie powinieś zrobić?");
        qList.add("10. Jak często w minionym roku musiałeś rano napić się, aby dojść do siebie po spożyciu zncznej ilości alkoholu poprzedniego dnia?");
        qList.add("11. Jak często w minionym roku musiałeś poczucie winy lub wyrzuty sumienia po piciu?");
        qList.add("12. Jak często w minionym roku nie pamiętałeś wydarzeń z poprzedniej nocu z powodu picia?");
        qList.add("13. Czy Ty lub ktokolwiek inny doznał urazu z powod Twojego picia?");
        qList.add("14. Czy krewny, przyjaciel, lekarz lub inny pracownik medyczny martwił się Twoim piciem lub sugeroawł zaprzestanie picia?");
        for (int i = 0; i < 4; i++) {
            a1List.add(" Nie");
        }
        for (int i = 0; i < 8; i++) {
            if (i == 1) {
                a1List.add(" 1 lub 2");
            } else {
                a1List.add(" Nigdy");
            }

        }
        for (int i = 0; i < 2; i++) {
            a1List.add(" Nie");
        }

        for (int i = 0; i < 4; i++) {
            a2List.add(" Tak");
        }
        a2List.add(" Raz w miesiącu lub rzadziej ");
        a2List.add(" 3 lub 4");
        for (int i = 0; i < 6; i++) {
            a2List.add(" Rzadziej niż raz w miesiącu");
        }
        for (int i = 0; i < 2; i++) {
            a2List.add(" Tak, ale nie w minionym roku");
        }

        for (int i = 0; i < 4; i++) {
            a3List.add(" -");
        }
        a3List.add(" 2-4 razy w miesiącu");
        a3List.add(" 5 lub 6");
        for (int i = 0; i < 6; i++) {
            a3List.add(" Raz w miesiącu");
        }
        for (int i = 0; i < 2; i++) {
            a3List.add(" Tak, w mionym roku");
        }
        for (int i = 0; i < 4; i++) {
            a4List.add(" -");
        }
        a4List.add(" 2-3 razy w tygodniu");
        a4List.add(" od 7 do 9");
        for (int i = 0; i < 8; i++) {
            a4List.add(" Raz w tygodniu ");
        }
        for (int i = 0; i < 4; i++) {
            a5List.add(" -");
        }
        a5List.add(" 4 lub więcej razy w tygodniu");
        a5List.add(" 10 lub więcej");
        for (int i = 0; i < 8; i++) {
            a5List.add(" Codziennie lub prawie codziennie ");
        }
        for (int i = 0; i < 14; i++) {
            pList.add(0);
        }
        question.setText(qList.get(0));
        answer1.setText(a1List.get(0));
        answer2.setText(a2List.get(0));
        answer3.setText(a3List.get(0));
        answer4.setText(a4List.get(0));
        answer5.setText(a5List.get(0));
        answer3.setVisible(false);
        answer4.setVisible(false);
        answer5.setVisible(false);
        text.setText("Krok " + (index + 1) + "/" + pList.size());
        progres.setProgress(((index + 1) / (double) pList.size()));
    }

    /**
     ** Metoda, która ustawia index
     *
     * @param index index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     ** Metoda, która zwraca index
     *
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     ** Metoda, która wykonuje diagnoze
     *
     * @param s jest komenda która będzie interpretowana
     */
    public void makeDiagnostic(String s) {
        boolean add = false;
        StringBuilder text = new StringBuilder();
        try {
            Rete engine = new Rete();
            engine.reset();
            StringWriter o = new StringWriter();
            engine.addOutputRouter("t", o);
            String result = new String();
            engine.batch("projekt/JESS/audit.clp");
            engine.eval(s);
            engine.run();
            result = o.toString();
            engine.clear();
            if (result == null ? "" == null : result.equals("")) {
                result = "Brak diagnozy";
            }
            for (int i = 0; i < result.length(); i++) {
                if (isNewLine(result, i)) {
                    text.append("\n");
                }
                if (isFactor(result, i)) {
                    add = true;
                } else {
                    text.append(result.charAt(i));
                }
            }
            if (add) {
                window.changeFactToRight("Spożywanie alkoholu");
               Notifications.create()
              .title("OnkoTest")
              .text("Podany czynnik ryzyka został dodany automatycznie.")
              .showInformation();
            }
            showOutputMessage(text.toString());

        } catch (JessException ex) {
            Logger.getLogger(AuditTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static boolean isFactor(String result, int i) {
        return result.charAt(i) == '1';
    }

    private static boolean isNewLine(String result, int i) {
        return result.charAt(i) == 10;
    }

    /**
     ** Metoda, która powoduje przejście do kolejnego pytania.
     *
     * @param event obsługa zdarzenia polegająca na kliknięciu w przycisk
     */
    @FXML
    private void next(ActionEvent event) {
        if (getIndex() + 1 < 14) {// jeżeli możemy przejść do następnego pytanie (od 0 do 12 = 13)
            index++;
            text.setText("Krok " + (index + 1) + "/" + pList.size());
            progres.setProgress(((index + 1) / (double) pList.size()));
        }
        if (getIndex() + 1 > 12 || getIndex() < 4) {
            if (getIndex() < 4) {
                answer3.setVisible(false);
            } else {
                answer3.setVisible(true);
            }
            answer4.setVisible(false);
            answer5.setVisible(false);
        } else {
            answer3.setVisible(true);
            answer4.setVisible(true);
            answer5.setVisible(true);
        }
        if (end) {// jezeli kliknięto nA KLAWISZ zakończ
            makeAllDiagnose();
            Stage stage;
            stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.close();

        }
        question.setText(qList.get(getIndex()));
        answer1.setText(a1List.get(getIndex()));
        answer2.setText(a2List.get(getIndex()));
        answer3.setText(a3List.get(getIndex()));
        answer4.setText(a4List.get(getIndex()));
        answer5.setText(a5List.get(getIndex()));
        if (getIndex() < 12) {
            if (pList.get(getIndex()) == 0) {
                answer1.setSelected(true);
            }
            if (pList.get(getIndex()) == 1) {
                answer2.setSelected(true);
            }
            if (pList.get(getIndex()) == 2) {
                answer3.setSelected(true);
            }
            if (pList.get(getIndex()) == 3) {

                answer4.setSelected(true);
            }
            if (pList.get(getIndex()) == 4) {
                answer5.setSelected(true);
            }
        } else {
            if (pList.get(getIndex()) == 0) {
                answer1.setSelected(true);
            }
            if (pList.get(getIndex()) == 2) {
                answer2.setSelected(true);
            }
            if (pList.get(getIndex()) == 4) {
                answer3.setSelected(true);
            }
        }
        if (getIndex() + 1 == 14) {
            next.setText("Zakończ");
            end = true;
        }
    }

    /**
     ** Metoda, która powoduje przejście wstecz
     *
     * @param event obsługa zdarzenia wstecz
     */
    @FXML
    private void back(ActionEvent event) {// klikneliśmy wstecz
        end = false;
        next.setText("Dalej >");
        if (getIndex() - 1 >= 0) {
            index--;
        }
        text.setText("Krok " + (index + 1) + "/" + pList.size());
        progres.setProgress(((index + 1) / pList.size()));
        if (getIndex() + 1 > 12 || getIndex() < 4) {
            if (getIndex() < 4) {
                answer3.setVisible(false);
            } else {
                answer3.setVisible(true);
            }
            answer4.setVisible(false);
            answer5.setVisible(false);
        } else {
            answer3.setVisible(true);
            answer4.setVisible(true);
            answer5.setVisible(true);
        }
        question.setText(qList.get(getIndex()));
        answer1.setText(a1List.get(getIndex()));
        answer2.setText(a2List.get(getIndex()));
        answer3.setText(a3List.get(getIndex()));
        answer4.setText(a4List.get(getIndex()));
        answer5.setText(a5List.get(getIndex()));
        if (getIndex() < 12) {
            if (pList.get(getIndex()) == 0) {
                answer1.setSelected(true);
            }
            if (pList.get(getIndex()) == 1) {
                answer2.setSelected(true);
            }
            if (pList.get(getIndex()) == 2) {
                answer3.setSelected(true);
            }
            if (pList.get(getIndex()) == 3) {

                answer4.setSelected(true);
            }
            if (pList.get(getIndex()) == 4) {
                answer5.setSelected(true);
            }
        } else {
            if (pList.get(getIndex()) == 0) {
                answer1.setSelected(true);
            }
            if (pList.get(getIndex()) == 2) {
                answer2.setSelected(true);
            }
            if (pList.get(getIndex()) == 4) {
                answer3.setSelected(true);
            }
        }

    }

    /**
     ** Metoda polegająca na kliknięciu w 1 odpowiedź
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void answer1Action(ActionEvent event) {

        pList.set(getIndex(), 0);
    }

    /**
     ** Metoda polegająca na kliknięciu w 2 odpowiedź
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void answer2Action(ActionEvent event) {
        if (getIndex() < 12) {
            pList.set(getIndex(), 1);
        } else {
            pList.set(getIndex(), 2);
        }
    }

    /**
     ** Metoda polegająca na kliknięciu w 3 odpowiedź
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void answer3Action(ActionEvent event) {
        //pList.set(getIndex(),2);
        if (getIndex() < 12) {
            pList.set(getIndex(), 2);
        } else {
            pList.set(getIndex(), 4);
        }
    }

    /**
     ** Metoda polegająca na kliknięciu w 4 odpowiedź
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void answer4Action(ActionEvent event) {
        pList.set(getIndex(), 3);
    }

    /**
     ** Metoda polegająca na kliknięciu w 5 odpowiedź
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void answer5Action(ActionEvent event) {
        pList.set(getIndex(), 4);
    }

    @Override
    /**
     ** Metoda ktora zwraca w postaci ciągu znaków wyrażenie które będzie
     * potrzebne wykonania wniskowania
     *
     * @rerurn wyrażenie potrzebne do wykonania wniskowania
     */
    public String toString() {
        StringBuilder tmp = new StringBuilder("( assert ( Point");
        for (int i = 0; i < pList.size(); i++) {
            tmp.append("( answer").append(i + 1).append(" ").append(pList.get(i)).append(") ");
        }
        tmp.append(") )");
        return tmp.toString();
    }

    /**
     ** wyświetla wyniki diagnozy
     *
     * @param message rezultat diagnozy
     */
    public void showOutputMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Wynik diagnozy");
        alert.setHeaderText("Otrzymane rezulataty");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     ** Wykonyje diagnoze,wyświetla dignoze i czyści wartości zmiennych.
     */
    public void makeAllDiagnose() {
        makeDiagnostic(toString());
        // System.out.println(toString());
        index = 0;
        for (int i = 0; i < pList.size(); i++) {
            pList.set(i, 0);
        }
        index = 0;
        end = false;
        next.setText("Dalej >");
        question.setText(qList.get(getIndex()));
        answer1.setText(a1List.get(getIndex()));
        answer2.setText(a2List.get(getIndex()));
        answer3.setText(a3List.get(getIndex()));
        answer4.setText(a4List.get(getIndex()));
        answer5.setText(a5List.get(getIndex()));
        answer3.setVisible(false);
        answer4.setVisible(false);
        answer5.setVisible(false);
    }

    /**
     ** Metoda, która ustawia kontroler klasy głownej
     *
     * @param window kontroler klasy głównej
     */
    public void setWindow(FactorWindowController window) {
        this.window = window;
    }

}
