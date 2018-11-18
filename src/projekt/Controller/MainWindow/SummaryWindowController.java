/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller.MainWindow;

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
import java.util.ArrayList;
import java.util.List;
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
import projekt.Activity.StageActivity;
import projekt.DB.Hibernate.Transaction.TransactionManager;
import projekt.DB.Maper.MaperInput;
import projekt.DB.Maper.MapperOutput;
import projekt.DB.Model.Input;
import projekt.DB.Model.Output;
import projekt.DB.Model.User;
import projekt.Model.CancerFamilly;
import projekt.Model.DiagnozeHTML;
import projekt.Model.FCList;
import projekt.Model.JessEngine;
import projekt.Model.TList;
import projekt.Model.Person;
import projekt.Properties.DataConfigPath;
import projekt.Properties.FXMLConfigPath;

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
        FXMLLoader load = new FXMLLoader(this.getClass().getResource(FXMLConfigPath.DIAGNOSE_WINDOW_FXML));
        DiagnoseWindowController cnt = new DiagnoseWindowController();
        Parent parent = load.load();
        cnt = load.getController();
        cnt.cancerFamilly = cancerFamilly;
        cnt.setPerson(person);
        cnt.dataSymptoms = dataSymptoms;
        cnt.cancerFamilly = cancerFamilly;
        DiagnozeHTML html = new DiagnozeHTML(cancerFamilly, person, dataFactors, dataSymptoms);
        String str = "";
        str = "( assert ( Person (age " + person.getAge() + ")))";
        TList f = new TList(DataConfigPath.FACTOR_WITH_ALIANS);
        f.makeOperation(dataFactors);
        TList f2 = new TList(DataConfigPath.SYMPTOMS_WITH_ALIANS);
        f2.makeOperation(dataSymptoms);
        FCList f3 = new FCList(DataConfigPath.FAMILLY_WITH_ALIANS);
        f3.makeOperation(cancerFamilly);
        List<Input> inputs= new ArrayList<>();
        User user= new User();
        user.setAge(person.getAge());
        user.setWeight(person.getWeight());
        user.setSex(person.getSex());
        Logger.getLogger(SummaryWindowController.class.getName()).log(Level.INFO,"Rozpoczęcie tranzakcji");

        try {
            s = JessEngine.queryInferenceEngine(str + " " + f.makeAssert("RiskFactor") + " "
                    + f2.makeAssert("Symptoms") + " " + f3.makeAssert("FamillyCancer"));

        } catch (JessException ex) {
            Logger.getLogger(SummaryWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(s.toString());

        mapingInputs(f, f2, f3, inputs, user);
        List<Output> outputs= new ArrayList<>();
        MapperOutput.mappingOutput(s,outputs,user);
        TransactionManager manager = new TransactionManager(inputs,outputs,user);
        Logger.getLogger(SummaryWindowController.class.getName()).log(Level.INFO,"Rozpoczęcie tranzakcji");
        manager.transact();
        Logger.getLogger(SummaryWindowController.class.getName()).log(Level.INFO,"Zakończenie tranzakcji");
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
     ** Metoda do mapowania danych.
     * @param f czynniki ryzyka
     * @param f2 sympptomy
     * @param f3 Nowotworzeniew rodzinie
     * @param inputs model danych bazy
     * @param user użytkownik
     */
    private void mapingInputs(TList f, TList f2, FCList f3, List<Input> inputs, User user) {
        MaperInput.mapingTListToInput(f,inputs,user,"Czyniik ryzyka");
        MaperInput.mapingTListToInput(f2,inputs,user,"Symptom");
        MaperInput.mapingTObjectToInput(f3,inputs,user,"Nowotworzeie w rodzinie");
    }

    /**
     ** Metoda, która powoduje, że formulrz rozciąga się na cały ekran 3
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void fullScreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        StageActivity.fullStage(stage);
    }

    /**
     ** Metoda która minimalizuje formularz
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void minimalizeSscreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        StageActivity.minimalizeStage(stage,rec2,w,h);
    }

    /**
     ** Metoda, która maksymalizuje formularz
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void maximalizeSscreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        StageActivity.maximalizeStage(stage,rec2,w,h);
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
        FXMLLoader load = new FXMLLoader(this.getClass().getResource(FXMLConfigPath.FACTOR_WINDOW_FXML));
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
        FXMLLoader load = new FXMLLoader(this.getClass().getResource(FXMLConfigPath.SYMPTOMS_WINDOW_FXML));
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
     ** Metoda, która powoduje przejście do poprzedniego okna
     *
     * @param event obsługa zdarzenia przejście do poprzedniego okna
     * @throws IOException wyjątek wejścia/wyjścia.
     */
    @FXML
    private void undoClick(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource(FXMLConfigPath.CANCER_IN_FAMILLY_WINDOW_FXML));
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
