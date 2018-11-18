package projekt.Controller.MainWindow;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
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
import javafx.scene.effect.BlendMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projekt.Activity.StageActivity;
import projekt.Model.EqualString;
import projekt.Model.Person;
import projekt.Properties.DataConfigPath;
import projekt.Properties.FXMLConfigPath;

import static projekt.Activity.AlertActivity.showOutputMessageError;

/**
 * FXML Controller class
 *
 * @author Andrzej Kierepka
 */
public class SymptomWindowController implements Initializable {

    Stage stage;
    Rectangle2D rec2;
    Double w, h;
    private Person person;
    private List<String> factor = new ArrayList<>();
    ObservableList<String> data = FXCollections.observableArrayList();
    ObservableList<String> dataRight = FXCollections.observableArrayList();
    @FXML
    private Button next;
    @FXML
    private ListView<String> symptoms;
    @FXML
    private ListView<String> addedSymptoms;
    private CancerInFamillyController cif;
    @FXML
    private Button add;
    @FXML
    private Button remove;
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
    public SymptomWindowController() {
        cif = new CancerInFamillyController();
    }

    /**
     * Metoda, która powoduje inicjalizajcę kontrolera klasy.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;
        readData(DataConfigPath.SYMPTOMS_LIST);
        Collections.sort(data, (String t, String t1) -> t1.compareTo(t)); 
        symptoms.setItems(data);
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
        sort();
    }

    /**
     ** Metoda, która powoduje przejście do następnego okna
     *
     * @param event obsługa zdarzenia przyciśnięcie klawisza
     * @throws IOException wyjątek wejścia wyjścia
     */
    @FXML
    private void nextWindow(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource(FXMLConfigPath.CANCER_IN_FAMILLY_WINDOW_FXML));
        CancerInFamillyController cnt = new CancerInFamillyController();
        Parent parent = load.load();
        cnt = load.getController();
        cnt.setPerson(person);
        cnt.setFactor(factor);
        cnt.setSymptoms(dataRight);
        for (int i = 0; i < cif.getSizeCancerInFamilly(); i++) {
            if (cnt.getCancerInFamilly(0).getCancer().isEmpty()) {
                cnt.setCancerInFamilly(0, cif.getCancerInFamilly(i));
            } else {
                cnt.addCancer(cif.getCancerInFamilly(i));
            }
        }
        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    /**
     ** Metoda, ltóra zwraca obiekt klasy person
     *
     * @return obiekt klasy prenos
     */
    public Person getPerson() {
        return person;
    }

    /**
     ** Metoda, która pozwala na ustawienie zmiennej typu person
     *
     * @param person instancja klasy Person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     ** Metoda, która pozwala na ustawienie listy czynników rysyka
     *
     * @param factor lista czynników ryzyka
     */
    public void setFactor(List<String> factor) {
        this.factor = factor;
    }

    /**
     ** Metoda, która odczytuje dane z pliku zewnętrznego i pozwala na
     * zachowanie znakowania UTF-8
     *
     * @param path ścieżka dostępu do pliku
     * @return ciąg znaków w pliku tekstowym
     */
    static String readInput(String path) {
        StringBuilder buffer = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis, "UTF8");
            try (Reader in = new BufferedReader(isr)) {
                int ch;
                while ((ch = in.read()) > -1) {
                    buffer.append((char) ch);
                }
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     ** Metoda, która pozwala na odczytamnie danych
     *
     * @param path ścieżka dostępu do pliku tekstowego
     */
    public void readData(String path) {
        String line = readInput(path);
        StringTokenizer st = new StringTokenizer(line, ",");
        while (st.hasMoreElements()) {
            data.add(st.nextElement().toString());
        }
    }

    /**
     ** Przeciągnięcie z miejsca zródłowego do miejsca docelowego
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void symptomsDragEntered(DragEvent event) {
        symptoms.setBlendMode(BlendMode.SRC_ATOP);
    }

    /**
     ** Metoda, która wykrywa przeciąganie i rozpoczyna drag & drop
     *
     * @param event obsługa zdarzenia MouseEvent
     */
    @FXML
    private void symptomsDragDetected(MouseEvent event) {
        Dragboard dragBoard = symptoms.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(symptoms.getSelectionModel().getSelectedIndex()));
        dragBoard.setContent(content);
    }

    /**
     ** Wyjście poza obszar formularza
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void symptomsDragExited(DragEvent event) {
        symptoms.setBlendMode(null);
    }

    /**
     ** Metoda, która odpowiada za przeciąganie danych z źródała do miejsca
     * docelowego
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void symptomsDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
    }

    /**
     ** Metoda, która odpowiada za opuszczenie przeciąganego symptomów
     *
     * @param event obsługa zdarzeniA
     */

    @FXML
    private void symptomsDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd = addedSymptoms.getItems().get(tmp);
        dataRight.remove(tmp);
        data.add(aadd);
        symptoms.setItems(data);
        addedSymptoms.setItems(dataRight);

    }

    /**
     ** Metoda, która odpowiada za opuszczenie przeciąganego csymptomów
     *
     *
     * @param event obsługa zdarzeniA
     */
    @FXML
    private void addedSymptomsDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd = symptoms.getItems().get(tmp);
        data.remove(tmp);
        dataRight.add(aadd);
        symptoms.setItems(data);
        addedSymptoms.setItems(dataRight);
    }

    /**
     ** Metoda, która wykrywa przeciąganie i rozpoczyna drag & drop
     *
     * @param event obsługa zdarzenia MouseEvent
     */
    @FXML
    private void addedSymptomsDragDetected(MouseEvent event) {
        Dragboard dragBoard = addedSymptoms.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(addedSymptoms.getSelectionModel().getSelectedIndex()));
        dragBoard.setContent(content);
    }

    /**
     ** Wyjście poza obszar formularza
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void addedSymptomsDragExited(DragEvent event) {
        addedSymptoms.setBlendMode(null);
    }

    /**
     ** Metoda, która odpowiada za przeciąganie danych z źródała do miejsca
     * docelowego
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void addedSymptomsDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
    }

    /**
     ** Przeciągnięcie z miejsca zródłowego do miejsca docelowego
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void addedSymptomsDragEntered(DragEvent event) {
        addedSymptoms.setBlendMode(BlendMode.SRC_ATOP);
    }

    /**
     ** Metoda, która powoduje, że formulrz rozciąga się na cały ekran
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
     ** Metoda która przenosi podaną jako parametr symptom na listę symptomów
     * użytkowanika
     *
     * @param symptom podany symptom
     */
    public void changeSymptomToRight(String symptom) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(symptom)) {
                dataRight.add(symptom);
                data.remove(i);
                symptoms.setItems(data);
                addedSymptoms.setItems(dataRight);
                return;
            }
        }
    }

    /**
     ** Metoda która przenosi podaną jako parametr symptom na listę symptomów
     *
     * @param symptom podany symptom
     */
    public void changeSympomsToLeft(String symptom) {
        for (int i = 0; i < dataRight.size(); i++) {
            if (dataRight.get(i).equals(symptom)) {
                data.add(symptom);
                dataRight.remove(i);
                symptoms.setItems(data);
                addedSymptoms.setItems(dataRight);
                return;
            }
        }
    }

    /**
     ** Metoda, która ustawia kontroller typu CancerInFamillyController
     *
     * @param cif instancja klasy CancerInFamillyController
     */
    public void setCancerInFamillyController(CancerInFamillyController cif) {
        this.cif = cif;
    }


    @FXML
    private void undoClick(ActionEvent event) {
        try {
            FXMLLoader load = new FXMLLoader(this.getClass().getResource(FXMLConfigPath.FACTOR_WINDOW_FXML));
            FactorWindowController cnt = new FactorWindowController();
            Parent parent = load.load();
            cnt = load.getController();
            for (int i = 0; i < factor.size(); i++) {
                cnt.changeFactToRight(factor.get(i));
            }
            cnt.setPerson(person);
            cnt.setSymptomWindowController(this);
            cnt.setCancerInFamillyController(cif);
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

    @FXML
    private void addSymptom(ActionEvent event) {
        try {
            int index = symptoms.getSelectionModel().getSelectedIndex();
            String tmp = data.remove(index);
            dataRight.add(tmp);
            addedSymptoms.setItems(dataRight);
            symptoms.setItems(data);
        } catch (Exception ex) {
            showOutputMessageError("Nie zaznaczyłeś wiersza");
        }
    }

    @FXML
    private void removeSymptom(ActionEvent event) {
        try {
            int index = addedSymptoms.getSelectionModel().getSelectedIndex();
            String tmp = dataRight.remove(index);
            data.add(tmp);
            addedSymptoms.setItems(dataRight);
            symptoms.setItems(data);
        } catch (Exception e) {
            showOutputMessageError("Nie zaznaczyłeś wiersza");
        }
    }
    /**
     ** Metoda, która sortuje kolekcje
     */
    private void sort() {
        Collections.sort(data, ( t,  t1) -> EqualString.removeChar(t).compareTo(EqualString.removeChar(t1)));
    }

}
