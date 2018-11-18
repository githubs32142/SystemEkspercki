/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import jess.JessException;
import jess.Rete;
import projekt.Controller.MainWindow.FactorWindowController;

/**
 * FXML Controller class
 *
 * @author Andrzej Kierepka
 */
public class DietTestController implements Initializable {

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
    private Button next;
    private int index;
    private boolean end = false;// kiedy klikneliśmy na koniec(wystaw diagnozę)
    List<String> qList = new ArrayList<>();// lista pytań
    List<String> a1List = new ArrayList<>();
    List<String> a2List = new ArrayList<>();
    List<String> a3List = new ArrayList<>();
    List<String> a4List = new ArrayList<>();
    List<Integer> pList = new ArrayList<>();// ilość punktów przyznanych za każdą odpowiedź
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
        qList.add("1. Jak często dostarczasz posiłki do swojego organizmu?");
        qList.add("2. Jak dużo pijesz napojów dziennie?");
        qList.add("3. Jak często zjadasz produkty zawierające mąkę pszenną (białe pieczywo, makaron)?");
        qList.add("4. Jak często zjadasz produkty pełnoziarniste (chleb razowy, bułka grahamka)?");
        qList.add("5. Jak często zjadasz owoce i warzywa?");
        qList.add("6. Jak często zjadasz produkty mleczne?");
        qList.add("7. Jakie produkty zjadasz najczęsciej?");
        qList.add("8. Jak często zjadasz produkty mięsne?");
        qList.add("9. Jak często zjadasz: słone przekąski, słodycze, napoje słodzone?");

        a1List.add("4 lub więcej");
        a2List.add("3");
        a3List.add("2");
        a4List.add("1");

        a1List.add("1.5 l lub więcej");
        a2List.add("1.2-1.5l");
        a3List.add("1-1.2l");
        a4List.add("mniej niż 1l");
        //3 pytanie
        a1List.add("Wcale");
        a2List.add("kilka razy w tygodniu");
        a3List.add("raz dziennie");
        a4List.add("4-5 razy dziennie");
        //4 pytanie
        a1List.add("4-5 razy dziennie");
        a2List.add("raz dziennie");
        a3List.add("kilka razy w tygodniu");
        a4List.add("wcale");
        // 5 pytanie
        a1List.add("1-2 razy dziennie");
        a2List.add("1-2 razy w tygodniu");
        a3List.add("1-2 razy dziennie");
        a4List.add("stosuje suplementacje");
        // 6 pytanie
        a1List.add("codziennie (1 porcja)");
        a2List.add("3-4 razy w tygodniu");
        a3List.add("1-2 razy w tygodniu");
        a4List.add("wcale");
        // 7 pytanie
        a1List.add("ryby");
        a2List.add("drób");
        a3List.add("inne");
        a4List.add("mięso wieprzowe");
        //8 pytanie
        a1List.add("wcale");
        a2List.add("1-2 razy w tygodniu");
        a3List.add("3-4 razy w tygodniu");
        a4List.add("5 lub więcej razy w tygodniu");
        // 9 pytanie       
        a1List.add("wcale");
        a2List.add("1-2 razy w tygodniu");
        a3List.add("3-4 razy w tygodniu");
        a4List.add("5 lub więcej razy w tygodniu");

        question.setText(qList.get(0));
        answer1.setText(a1List.get(0));
        answer2.setText(a2List.get(0));
        answer3.setText(a3List.get(0));
        answer4.setText(a4List.get(0));
        for (int i = 0; i < qList.size(); i++) {
            pList.add(0);
        }
    }

    /**
     * Metoda, która powoduje wybranie odpowiedzi nr 1
     *
     * @param event odbsługa zdarzenia obiekt
     */
    @FXML
    private void answer1Action(ActionEvent event) {
        pList.set(index, 0);
    }

    /**
     * Metoda, która powoduje wybranie odpowiedzi nr 2
     *
     * @param event odbsługa zdarzenia obiekt
     */
    @FXML
    private void answer2Action(ActionEvent event) {
        pList.set(index, 1);
    }

    /**
     * Metoda, która powoduje wybranie odpowiedzi nr 3
     *
     * @param event odbsługa zdarzenia obiekt
     */
    @FXML
    private void answer3Action(ActionEvent event) {
        pList.set(index, 2);
    }

    /**
     * Metoda, która powoduje wybranie odpowiedzi nr 4
     *
     * @param event odbsługa zdarzenia obiekt
     */
    @FXML
    private void answer4Action(ActionEvent event) {
        pList.set(index, 3);
    }

    /**
     ** Metoda, która powoduje przejście do następnego pytania lub zakończnie
     * testu
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void next(ActionEvent event) {
        if (index <= qList.size()) {
            index++;
        }
        if (index == qList.size()) {
            // koniec
            makeAllDiagnose();
            next.setText("Dalej>");
            index = 0;
            for (int i = 0; i < qList.size(); i++) {
                pList.set(i, 0);
            }

        }
        if (index < qList.size()) {
            if (index == qList.size() - 1) {
                next.setText("Zakończ");
            }
            question.setText(qList.get(index));
            answer1.setText(a1List.get(index));
            answer2.setText(a2List.get(index));
            answer3.setText(a3List.get(index));
            answer4.setText(a4List.get(index));
            if (pList.get(index) == 0) {
                answer1.setSelected(true);
            }
            if (pList.get(index) == 1) {
                answer2.setSelected(true);
            }
            if (pList.get(index) == 2) {
                answer3.setSelected(true);
            }
            if (pList.get(index) == 3) {
                answer4.setSelected(true);
            }
        }
    }

    /**
     ** Metoda, która powoduje przejście do poprzedniego pytania nie przechodzi
     * do tyłu jeżeli jesteśmy na [ytaniu
     *
     * @param event obsługa zdarzenia obiektu
     */
    @FXML
    private void back(ActionEvent event) {
        if (index > 0) {
            index--;
        }
        if (index >= 0) {
            question.setText(qList.get(index));
            answer1.setText(a1List.get(index));
            answer2.setText(a2List.get(index));
            answer3.setText(a3List.get(index));
            answer4.setText(a4List.get(index));
            if (pList.get(index) == 0) {
                answer1.setSelected(true);
            }
            if (pList.get(index) == 1) {
                answer2.setSelected(true);
            }
            if (pList.get(index) == 2) {
                answer3.setSelected(true);
            }
            if (pList.get(index) == 3) {
                answer4.setSelected(true);
            }
        }
    }

    /**
     ** Metoda, która zwraca położenie w rozwiazywaniu pytań
     *
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     ** Metoda ktora zwraca w postaci ciągu znaków wyrażenie które będzie
     * potrzebne wykonania wniskowania
     *
     * @return  wyrażenie potrzebne do wykonania wniskowania
     */
    @Override
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
    private void showOutputMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wynik diagnozy");
        alert.setHeaderText("Otrzymane rezulataty");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     ** Wykonyje diagnoze,wyświetla dignoze i czyści wartości zmiennych.
     */
    private void makeAllDiagnose() {
        makeDiagnostic(toString());
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

    }

    public void makeDiagnostic(String s) {
        boolean add = false;
        StringBuilder text = new StringBuilder();
        try {
            Rete engine = new Rete();
            engine.reset();
            StringWriter o = new StringWriter();
            engine.addOutputRouter("t", o);
            String result = new String();
            // Load the pricing rules
            engine.batch("projekt/JESS/solarium.clp");
            engine.eval(s);
            engine.run();
            result = o.toString();
            engine.clear();
            if (resultIsNull(result)) {
                result = "Brak diagnozy";
            }
            for (int i = 0; i < result.length(); i++) {
                if (isFactor(result, i)) {
                    add = true;
                } else {
                    text.append(result.charAt(i));
                }
            }
            if (add) {
                window.changeFactToRight("Lampy solarium");
            }
            showOutputMessage(text.toString());

        } catch (JessException ex) {
            Logger.getLogger(SmokingTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static boolean isFactor(String result, int i) {
        return result.charAt(i) == '1';
    }

    private static boolean resultIsNull(String result) {
        return result == null ? "" == null : result.equals("");
    }

    /**
     ** Metoda, która pozwala na ustawienie instancji klasy
     * FactorWindowController
     *
     * @param window obiekt klasy FactorWindowController
     */
    public void setWindow(FactorWindowController window) {
        this.window = window;
    }
}
