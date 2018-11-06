package projekt.Controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projekt.Class.CancerFamilly;
import projekt.Class.Person;

/**
 * FXML Controller class
 *
 * @author Andrzzej Kierepka
 */
public class CancerInFamillyController implements Initializable {

    Stage stage;
    Rectangle2D rec2;
    Double w, h;
    private Person person;
    private List<String> factor;
    private List<String> symptoms;
    @FXML
    private TableView<CancerFamilly> table;
    private TableColumn cancer;
    private TableColumn familly;
    private ObservableList<CancerFamilly> data;
    private ObservableList<String> listCancer;
    private ObservableList<String> listFamilly;

    private ComboBox<String> famillyCancer;
    private int index;
    @FXML
    private ComboBox<String> cancerCombo;
    @FXML
    private ComboBox<String> famillyCombo;
    @FXML
    private MenuItem delete;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox box;
    @FXML
    private JFXHamburger hamburger;

    public CancerInFamillyController() {
        data = FXCollections.observableArrayList();
    }

    /**
     *
     * Inicjalizacja kontriolera
     *
     * @param url wskaźnik do "zasobu" w sieci World Wide Web
     * @param rb wersja językowa
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;

        listCancer = FXCollections.observableArrayList("Rak płuc", "Rak jelita grubrgo", "Rak piersi", "Rak jąder", "Rak gruczołu krokowego", "Guz mózgu", "Rak szyjki macicy", "Rak płuc", "Rak trzustki", "Rak żołądka", "Rak macicy", "Rak krtani","Rak jajnika");
        listFamilly = FXCollections.observableArrayList("Brat", "Siostra", "Ojciec", "Matka", "Dziadek", "Babcia", "Wujek", "Ciotka");
        cancer = new TableColumn("Rak w rodzinie");
        cancer.setMinWidth(300);
        cancer.setCellValueFactory(
                new PropertyValueFactory<>("cancer"));
        familly = new TableColumn("Rodzina ");
        familly.setMinWidth(280);
        familly.setCellValueFactory(new PropertyValueFactory<>("familly"));
        table.getColumns().addAll(cancer, familly);
        factor = new ArrayList<>();
        symptoms = new ArrayList<>();
        famillyCombo.setItems(listFamilly);
        cancerCombo.setItems(listCancer);
        index = -1;
        addIfEmpty();
        table.setItems(data);
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
     ** Metoda, która pozwala na ustawienie listy czynników ryzyka
     *
     * @param factor lista czynników ryzyka
     */
    public void setFactor(List<String> factor) {
        this.factor = factor;
    }

    /**
     ** Metoda, która ustawia pozwala na ustawienie symptomów
     *
     * @param symptoms lista symptomów
     */
    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    /**
     ** Metoda, która zrwaca zmienną określającą osobę
     *
     * @return zmienna typu Person
     */
    public Person getPerson() {
        return person;
    }

    /**
     ** Metoda, która zwraca listę czynników ryzyka
     *
     * @return Lista czynników ryzyka
     */
    public List<String> getFactor() {
        return factor;
    }

    /**
     ** Metoda, która pozawala na zwrócenie listy symptoów
     *
     * @return lista symptomów
     */
    public List<String> getSymptoms() {
        return symptoms;
    }

    /**
     ** Metoda która pozwala na dodanie danych do tabeli
     *
     * @param event obsługa zdarzenia kliknięcie w przycisk
     */
    @FXML
    private void addToTable(ActionEvent event) {
        if (isSelFamillyAndCancer()) {

            if (data.isEmpty()) {
                data.add(new CancerFamilly(cancerCombo.getSelectionModel().getSelectedItem(), famillyCombo.getSelectionModel().getSelectedItem()));
            } else {
                if (dataCancerIsEmpty()) {
                    data.set(0, new CancerFamilly(cancerCombo.getSelectionModel().getSelectedItem(), famillyCombo.getSelectionModel().getSelectedItem()));
                } else {
                    data.add(new CancerFamilly(cancerCombo.getSelectionModel().getSelectedItem(), famillyCombo.getSelectionModel().getSelectedItem()));
                }
            }

            table.setItems(data);
        } else {
            showOutputMessage("Nie można wprowadzić danych");
        }

    }

    private boolean dataCancerIsEmpty() {
        return data.get(0).getCancer().isEmpty();
    }

    private boolean isSelFamillyAndCancer() {
        return cancerCombo.getSelectionModel().getSelectedIndex() >= 0 && famillyCombo.getSelectionModel().getSelectedIndex() >= 0;
    }

    /**
     ** Metoda, która usuwa zaznaczony wiersz z tabeli
     *
     * @param event
     */
    @FXML
    private void deleteCancerInFamilly(ActionEvent event) {
        makeDelete();
    }

    /**
     ** Metoda, która klikniemy w tabelę
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void tableClicked(MouseEvent event) {

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

    @FXML
    private void removeObject(ActionEvent event) {
        makeDelete();
    }

    /**
     ** Metoda, która powoduje, że formulrz rozciąga się naa cały ekran
     *
     * @param event
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
     ** Metoda która dodaje rak w rodzienie do listy
     *
     * @param cf rak w rodzinie
     */
    public void addCancer(CancerFamilly cf) {
        if(data.size()==1 && data.get(0).getCancer().isEmpty()){
            data.set(0, cf);
        }
        else{
            data.add(cf);
        }
        
    }

    /**
     ** Metoda, która wyświetla ilość elementów raka w rodzinie
     *
     * @return ilość elementów raka w rodzinie
     */
    public int getSizeCancerInFamilly() {
        return data.size();
    }

    /**
     ** Metoda, która wyświetla rak w rodzinie o podanym indeksie
     *
     * @param i index
     * @return rak w rodzine 
     */
    public CancerFamilly getCancerInFamilly(int i) {
        return data.get(i);
    }

    /**
     ** Metoda, która ustawia rak w rodzinie o podanym indeksie
     *
     * @param i index na liście
     * @param cf rak w rodzinei
     */
    public void setCancerInFamilly(int i, CancerFamilly cf) {
        data.set(i, cf);
    }

    /**
     ** Metoda usuwa wiersz z tabeli
     */
    private void makeDelete() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("OnkoTest");
        alert.setHeaderText("Zawartość poniższego komunikatu:");
        alert.setContentText("Czy na pewno chcesz usunąć zaznaczony rak w rodzinie?");
        ButtonType buttonTypeYes = new ButtonType("Tak");
        ButtonType buttonTypeNo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            index = table.getSelectionModel().getSelectedIndex();
            if (selGoodRow()) {
                data.remove(index);
                addIfEmpty();
            } else {
                showOutputMessage("Nie zaznaczyłeś żadnego wiersza");
            }
        }
    }

    private void addIfEmpty() {
        if(data.isEmpty()){
            data.add(new CancerFamilly("", ""));
        }
    }

    private boolean selGoodRow() {
        return index >= 0 && index < data.size();
    }

    /**
     ** Metoda, która powoduje przejście do następnego okna
     *
     * @param event obsługa zdarzenia
     * @throws IOException wyjątek który może spowodowac błąd wejścia/wyjścia
     */
    @FXML
    private void nextWindow(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/SummaryWindow.fxml"));
        SummaryWindowController cnt = new SummaryWindowController();
        Parent parent = load.load();
        cnt = load.getController();
        addDataToOtherController(cnt);
        cnt.setPerson(person);
        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    private void addDataToOtherController(SummaryWindowController cnt) {
        for (int i = 0; i < factor.size(); i++) {
            cnt.dataFactors.add(factor.get(i));
        }
        for (int i = 0; i < symptoms.size(); i++) {
            cnt.dataSymptoms.add(symptoms.get(i));
        }
        for (int i = 0; i < this.data.size(); i++) {
            cnt.cancerFamilly.add(this.data.get(i));
        }
    }

    /**
     ** Metoda, która powoduje przejście do poprzedniego okna
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void undoClick(ActionEvent event) {
        try {
            FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/SymptomWindow.fxml"));
            SymptomWindowController cnt = new SymptomWindowController();
            Parent parent = load.load();
            cnt = load.getController();
            for (int i = 0; i < symptoms.size(); i++) {
                cnt.changeSymptomToRight(symptoms.get(i));
            }
            cnt.setFactor(factor);
            cnt.setPerson(person);
            cnt.setCancerInFamillyController(this);
            Scene scene = new Scene(parent);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
            stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
}
