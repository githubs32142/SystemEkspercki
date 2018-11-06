/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jess.JessException;
import projekt.Class.CancerFamilly;
import projekt.Class.DiagnozeHTML;
import projekt.Class.FCList;
import projekt.Class.JessEngine;
import projekt.Class.TList;
import projekt.Class.Person;
import projekt.Propertis.ConfigPath;

/**
 * FXML Controller class
 *
 * @author Andrzej Kierepka
 */
public class SummaryWindowController implements Initializable {

    boolean first = true;
    @FXML
    private ListView<String> symptoms;
    @FXML
    private Button next;
    @FXML
    private ListView<String> factors;
    @FXML
    private TableView<CancerFamilly> famillyCancer;
    private TableColumn cancer;
    private TableColumn familly;
    Stage stage;
    Rectangle2D rec2;
    Double w, h;
    ObservableList<CancerFamilly> cancerFamilly;
    private Person person;
    ObservableList<String> dataFactors = FXCollections.observableArrayList();
    ObservableList<String> dataSymptoms = FXCollections.observableArrayList();
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox box;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane root;
    private double xOffset = 0;
    private double yOffset = 0;

    /**
     ** Konstruktor bezparametrowy
     */
    public SummaryWindowController() {

        cancerFamilly = FXCollections.observableArrayList();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rec2 = Screen.getPrimary().getVisualBounds();
        factors.setItems(dataFactors);
        symptoms.setItems(dataSymptoms);
        cancer = new TableColumn("Rak w rodzinie");
        cancer.setMinWidth(320);
        cancer.setCellValueFactory(
                new PropertyValueFactory<>("cancer"));
        familly = new TableColumn("Rodzina ");
        familly.setMinWidth(320);
        familly.setCellValueFactory(new PropertyValueFactory<>("familly"));
        famillyCancer.getColumns().addAll(cancer, familly);
        famillyCancer.setItems(cancerFamilly);
        drawer.setSidePane(box);
        drawer.close();
        HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
                drawer.setPrefWidth(150);
            }
        });
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //set mouse drag
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage;
                stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    /**
     ** Metoda, która powoduje, że przechodzimy do następnego okna
     *
     * @param event obsługa zdarzenia kliknięcie w przycisk
     * @throws IOException wyjątek wejścia/wyjścia
     */
    @FXML
    private void nextWindow(ActionEvent event) throws IOException {
        String s = "";
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/DiagnoseWindow.fxml"));
        DiagnoseWindowController cnt = new DiagnoseWindowController();
        Parent parent = load.load();
        cnt = load.getController();
        cnt.cancerFamilly = cancerFamilly;
        cnt.setPerson(person);
        cnt.dataSymptoms = dataSymptoms;
        cnt.cancerFamilly = cancerFamilly;
        DiagnozeHTML html = new DiagnozeHTML(cancerFamilly, person, dataFactors, dataSymptoms);

        //view.getEngine().load(urlFile.toExternalForm());
        String str = "";
        str = "( assert ( Person (age " + person.getAge() + ")))";
        TList f = new TList(ConfigPath.getFactorWithAlians());
        f.makeOperation(dataFactors);
        TList f2 = new TList(ConfigPath.getSymptomsWithAlians());
        f2.makeOperation(dataSymptoms);
        FCList f3 = new FCList(ConfigPath.getFamillyWithAlians());
        f3.makeOperation(cancerFamilly);
        try {
            s = JessEngine.queryInferenceEngine(str + " " + f.makeAssert("RiskFactor") + " "
                    + f2.makeAssert("Symptoms") + " " + f3.makeAssert("FamillyCancer"));

        } catch (JessException ex) {
            Logger.getLogger(SummaryWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        html.setResultDiagnose(s);
        html.parseHTML();
        cnt.setString(html.text);
        WebView view = cnt.getWebView();
        try {
            OutputStreamWriter outputStreamWriter
                    = new OutputStreamWriter(
                            new FileOutputStream(
                                    new File("src/projekt/HTML/Diagnoza/diagnoza.html")), Charset.forName("UTF-8"));
            PrintWriter out = new PrintWriter(outputStreamWriter);
            out.write(html.text.toString());
            out.close();
        } catch (FileNotFoundException ex) {
        }
        view.getEngine().loadContent(html.textCss.toString());
        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        cnt.setWebView(view);
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    /**
     ** Metoda, która powoduje, że formulrz rozciąga się na cały ekran 3
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void fullScreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
    }

    /**
     ** Metoda która minimalizuje formularz
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void minimalizeSscreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        if (stage.isMaximized()) {
            w = rec2.getWidth();
            h = rec2.getHeight();
            stage.setMaximized(false);
            stage.setHeight(h);
            stage.setWidth(w);
            stage.centerOnScreen();
            Platform.runLater(() -> {
                stage.setIconified(true);
            });
        } else {
            stage.setIconified(true);
        }
    }

    /**
     ** Metoda, która maksymalizuje formularz
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void maximalizeSscreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(600);
                stage.setWidth(800);
                stage.centerOnScreen();
            } else {
                stage.setMaximized(false);

            }

        } else {
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
        }
    }

    /**
     ** Metoda, która zamyka program
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void closeeSscreen(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     ** Metoda, która powoduje przejście do okna gdzie znajdują się czynniki
     * ryzyka.
     *
     * @param event obsługa zdarzenia kliknięcie w przycisk
     * @throws IOException wyjątek wejścia/wyjścia
     */
    @FXML
    private void backToFactor(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/FactorWindow.fxml"));
        FactorWindowController cnt = new FactorWindowController();
        Parent parent = load.load();
        cnt = load.getController();
        for (int i = 0; i < dataFactors.size(); i++) {
            cnt.changeFactToRight(dataFactors.get(i));
        }
        cnt.setPerson(person);
        SymptomWindowController sw = new SymptomWindowController();
        sw.dataRight = dataSymptoms;
        cnt.setSymptomWindowController(sw);
        CancerInFamillyController cif = new CancerInFamillyController();
        for (int i = 0; i < cancerFamilly.size(); i++) {
            cif.addCancer(cancerFamilly.get(i));
        }
        cnt.setCancerInFamillyController(cif);
        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        Stage stage;
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    /**
     ** Metoda, która powoduje przejście do okna podsumowania
     *
     * @param event zdarzenie obsługi zdarzenia
     * @throws IOException wyjątek wejścia/wyjścia
     */
    @FXML
    private void backToSummary(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/SymptomWindow.fxml"));
        SymptomWindowController cnt = new SymptomWindowController();
        Parent parent = load.load();
        cnt = load.getController();
        cnt.setFactor(dataFactors);
        cnt.setPerson(person);
        for (int i = 0; i < dataSymptoms.size(); i++) {
            cnt.changeSymptomToRight(dataSymptoms.get(i));
        }
        CancerInFamillyController cif = new CancerInFamillyController();
        for (int i = 0; i < cancerFamilly.size(); i++) {
            cif.addCancer(cancerFamilly.get(i));
        }
        cnt.setCancerInFamillyController(cif);
        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        Stage stage;
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    /**
     ** Metoda która pozwala na ustawienie obiektu immitującego ooę
     *
     * @param person instancja Klasy Person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     ** wyświetla KOMUNIKAT O BŁĘDZIE
     *
     * @param message treść komunikatu o błędzie
     */
    public void showOutputMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Treść błędu");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     ** Metoda, która powoduje przejście do poprzedniego okna
     *
     * @param event obsługa zdarzenia przejście do poprzedniego okna
     * @throws IOException wyjątek wejścia/wyjścia.
     */
    @FXML
    private void undoClick(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/CancerInFamilly.fxml"));
        CancerInFamillyController cnt = new CancerInFamillyController();
        Parent parent = load.load();
        cnt = load.getController();
        cnt.setFactor(dataFactors);
        cnt.setPerson(person);
        cnt.setSymptoms(dataSymptoms);
        for (int i = 0; i < cancerFamilly.size(); i++) {
            cnt.addCancer(cancerFamilly.get(i));
        }
        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        Stage stage;
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

}
