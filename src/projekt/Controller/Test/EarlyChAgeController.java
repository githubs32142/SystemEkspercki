/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller.Test;

import java.io.StringWriter;
import java.net.URL;
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
 * @author Andrzej Kierepka
 */
public class EarlyChAgeController implements Initializable {

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
    private Button diagnose;
    @FXML
    private Button close;
    private int point;

    @Override
    /**
     *
     * Inicjalizacja kontriolera
     *
     * @param url wskaźnik do "zasobu" w sieci World Wide Web
     * @param rb wersja językowa
     */
    public void initialize(URL url, ResourceBundle rb) {
        point = 0;
    }

    @FXML
    private void answer1Action(ActionEvent event) {
        point = 0;
    }

    @FXML
    private void answer2Action(ActionEvent event) {
        point = 1;
    }

    @FXML
    private void diagnoseAction(ActionEvent event) {
        makeDiagnostic(new String("( assert ( Point ( sum " + point + " )))"));
        Stage stage;
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeAction(ActionEvent event) {
        Stage stage;
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    public void setWindow(FactorWindowController window) {
        this.window = window;
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
            engine.batch("projekt/JESS/EarlyChAge.clp");
            engine.eval(s);
            engine.run();
            result = o.toString();
            engine.clear();
            if (isEmpty(result)) {
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
                window.changeFactToRight("Wczesne współżycie seksualne");
                Notifications.create()
                        .title("OnkoTest")
                        .text("Podany czynnik ryzyka został dodany automatycznie.")
                        .showInformation();
            }
            showOutputMessage(text.toString());

        } catch (JessException ex) {
            Logger.getLogger(EarlyChAgeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static boolean isFactor(String result, int i) {
        return result.charAt(i) == '1';
    }

    private static boolean isEmpty(String result) {
        return result == null ? "" == null : result.equals("");
    }
}
