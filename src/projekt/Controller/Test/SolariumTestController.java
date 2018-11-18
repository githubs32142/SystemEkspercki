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
import projekt.Controller.Test.SmokingTestController;

/**
 * FXML Controller clas
 *
 * @author Andrzej Kierepka
 * @version 1.0.1
 */
public class SolariumTestController implements Initializable {

    private FactorWindowController window;
    private boolean end = false;// kiedy klikneliśmy na koniec(wystaw diagnozę)
    private int index;
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
    List<String> qList = new ArrayList<>();// lista pytań
    List<String> a1List = new ArrayList<>();
    List<String> a2List = new ArrayList<>();
    List<String> a3List = new ArrayList<>();
    List<String> a4List = new ArrayList<>();
    List<Integer> pList = new ArrayList<>();// ilość punktów przyznanych za każdą odpowiedź
    @FXML
    private ProgressBar progres;
    @FXML
    private Label text;

    /**
     ** Inicjalizacja kontrolera klasy
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        index = 0;
        qList.add("1. Czy używasz specjalnych kosmetyków opalania w solarium?");
        qList.add("2. Jak długo trwa opalanie na solarium?");
        qList.add("3. Jak często korzystasz z solarium?");

        a1List.add(" Tak");
        a1List.add(" 1-5 min");
        a1List.add(" 1-2 razy w tygodniu");

        a2List.add(" Nie");
        a2List.add(" 5-10 min");
        a2List.add(" 3-4 razy w tygodniu");

        a3List.add(" -");
        a3List.add(" 10-20 min");
        a3List.add(" 5-6 razy w tygodniu");

        a4List.add(" -");
        a4List.add(" 30 lub więcej");
        a4List.add(" codzniennie");
        for (int i = 0; i < qList.size(); i++) {
            pList.add(0);
        }
        question.setText(qList.get(0));
        answer1.setText(a1List.get(0));
        answer2.setText(a2List.get(0));
        answer3.setText(a3List.get(0));
        answer4.setText(a4List.get(0));
        if (a3List.get(index).equals(" -")) {
            answer3.setVisible(false);
            answer4.setVisible(false);
        } else {

            answer3.setVisible(true);
            answer4.setVisible(true);
        }
        answer1.setSelected(true);
        text.setText("Krok " + (index + 1) + "/" + pList.size());
        progres.setProgress(((index + 1) / (double) pList.size()));
    }

    /**
     ** Metoda polegająca na kliknięciu w 1 odpowiedź
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void answer1Action(ActionEvent event) {
        pList.set(index, 0);
    }

    /**
     ** Metoda polegająca na kliknięciu w 2 odpowiedź
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void answer2Action(ActionEvent event) {
        pList.set(index, 1);
    }

    /**
     ** Metoda polegająca na kliknięciu w 3 odpowiedź
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void answer3Action(ActionEvent event) {
        pList.set(index, 2);
    }

    /**
     ** Metoda polegająca na kliknięciu w 4 odpowiedź
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void answer4Action(ActionEvent event) {
        pList.set(index, 3);
    }

    /**
     ** Metoda, która powoduje przejście do kolejnego pytania.
     *
     * @param event obsługa zdarzenia polegająca na kliknięciu w przycisk
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
            Stage stage;
            stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.close();
        }
        if (index < qList.size()) {
            if (index == qList.size() - 1) {
                next.setText("Zakończ");
            }
            text.setText("Krok " + (index + 1) + "/" + pList.size());
            progres.setProgress(((index + 1) / (double) pList.size()));
            question.setText(qList.get(index));
            answer1.setText(a1List.get(index));
            answer2.setText(a2List.get(index));
            if (a3List.get(index).equals(" -")) {
                answer3.setVisible(false);
                answer4.setVisible(false);
            } else {

                answer3.setVisible(true);
                answer4.setVisible(true);
            }
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
     ** Metoda, która powoduje przejście wstecz
     *
     * @param event obsługa zdarzenia wstecz
     */
    @FXML
    private void back(ActionEvent event) {
        if (index > 0) {
            index--;
        }
        if (index >= 0) {
            text.setText("Krok " + (index + 1) + "/" + pList.size());
            progres.setProgress(((index + 1) / (double) pList.size()));
            question.setText(qList.get(index));
            answer1.setText(a1List.get(index));
            answer2.setText(a2List.get(index));
            if (a3List.get(index).equals(" -")) {
                answer3.setVisible(false);
                answer4.setVisible(false);
            } else {

                answer3.setVisible(true);
                answer4.setVisible(true);
            }
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
     ** Metoda, która zwraca index
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
     * @rerurn wyrażenie potrzebne do wykonania wniskowania
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
            // Load the pricing rules
            engine.batch("projekt/JESS/solarium.clp");
            engine.eval(s);
            engine.run();
            result = o.toString();
            engine.clear();
            if (result == null ? "" == null : result.equals("")) {
                result = "Brak diagnozy";
            }
            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i) == '1') {
                    add = true;
                } else {
                    text.append(result.charAt(i));
                }
            }
            if (add) {
                window.changeFactToRight("Lampy solarium");
                Notifications.create()
                        .title("OnkoTest")
                        .text("Podany czynnik ryzyka został dodany automatycznie.")
                        .showInformation();
            }
            showOutputMessage(text.toString());

        } catch (JessException ex) {
            Logger.getLogger(SmokingTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
