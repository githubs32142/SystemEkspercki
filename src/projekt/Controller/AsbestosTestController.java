package projekt.Controller;

import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Screen;
import javafx.stage.Stage;
import jess.JessException;
import jess.Rete;
import org.controlsfx.control.Notifications;

/**
 ** Klasa kontrolera, który obsługuje test na zagrożenie azbestem
 *
 * @author Andrzej Kierepka
 */
public class AsbestosTestController implements Initializable {

    @FXML
    private ListView<String> job;
    @FXML
    private ListView<String> addedJob;
    @FXML
    private Button test;
    ObservableList<String> data = FXCollections.observableArrayList(
            "Mechanik samochodowy", "Pracownik wykonywania kotłów", "Stolarz", "Montarz murów suchych", "Elektryk", "Hutnik",
            "Operator maszyn", "Tokarz", "Specjalista budowy młynów", "Monter rur", "Pracownik elektrowni", "Kolejarz", "Stoczniowiec");
    ObservableList<String> dataRight = FXCollections.observableArrayList();
    Stage stage;
    Rectangle2D rec2;
    Double w, h;
    private FactorWindowController window;

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
        job.setItems(data);
    }

    @FXML
    private void addedFactorRemove(MouseEvent event) {

    }

    @FXML
    private void addedDragDetected(MouseEvent event) {
        Dragboard dragBoard = addedJob.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(addedJob.getSelectionModel().getSelectedIndex()));
        dragBoard.setContent(content);
    }

    /**
     ** Metoda, która powoduje wykonanie testu
     *
     * @param event obsługa zdarzenia kliknięcie w przycisk
     */
    @FXML
    private void makeTest(ActionEvent event) {
        makeDiagnostic(toString());
        Stage stage;
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    /**
     ** Metoda, która powoduje, że formulrz rozciąga się na cały ekran
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
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void jobDragEntered(DragEvent event) {
        job.setBlendMode(BlendMode.SRC_ATOP);
    }

    @FXML
    private void jobDragDetected(MouseEvent event) {
        Dragboard dragBoard = job.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(job.getSelectionModel().getSelectedIndex()));
        dragBoard.setContent(content);
    }

    @FXML
    private void jobDragExited(DragEvent event) {
        job.setBlendMode(null);
    }

    @FXML
    private void jobDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
    }

    @FXML
    private void jobDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd = addedJob.getItems().get(tmp);
        dataRight.remove(tmp);
        data.add(aadd);
        job.setItems(data);
        addedJob.setItems(dataRight);
    }

    @FXML
    private void addToRightJob(ActionEvent event) {
        try {
            int index = job.getSelectionModel().getSelectedIndex();
            String tmp = data.remove(index);
            dataRight.add(tmp);
            job.setItems(data);
            addedJob.setItems(dataRight);//dataRight             
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OnkoTest");
            alert.setHeaderText("Błąd podczas przenoszenia");
            alert.setContentText("Nie zaznaczono zawodu który chcesz przenieść");
            alert.showAndWait();
        }

    }

    @FXML
    private void addToLeftJob(ActionEvent event) {
        try {
            int index = addedJob.getSelectionModel().getSelectedIndex();
            String tmp = dataRight.remove(index);
            data.add(tmp);
            addedJob.setItems(dataRight);
            job.setItems(data);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OnkoTest");
            alert.setHeaderText("Błąd podczas przenoszenia");
            alert.setContentText("Nie zaznaczono zawodu który chcesz przenieść");
            alert.showAndWait();
        }

    }

    @FXML
    private void addedDragEntered(DragEvent event) {
        addedJob.setBlendMode(BlendMode.SRC_ATOP);
    }

    @FXML
    private void addedDragExited(DragEvent event) {
        addedJob.setBlendMode(null);
    }

    @FXML
    private void addedDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
    }

    @FXML
    private void addedDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd = job.getItems().get(tmp);
        data.remove(tmp);
        dataRight.add(aadd);
        job.setItems(data);
        addedJob.setItems(dataRight);
    }

    @Override
    /**
     ** Metoda ktora zwraca w postaci ciągu znaków wyrażenie które będzie
     * potrzebne wykonania wniskowania
     *
     * @rerurn wyrażenie potrzebne do wykonania wniskowania
     */
    public String toString() {
        StringBuilder tmp = new StringBuilder("( assert ( Point ( sum ").append(dataRight.size());
        tmp.append(" ) ) )");
        return tmp.toString();
    }

    /**
     ** wyświetla wyniki diagnozy
     *
     * @param message rezultat diagnozy
     */
    public void showOutputMessage(String message) {
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
            engine.batch("projekt/JESS/asbestion.clp");
            engine.eval(s);
            engine.run();
            result = o.toString();
            engine.clear();
            if (isNoFactor(result)) {
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
                window.changeFactToRight("Kontakt z azbestem");
                Notifications.create()
                        .title("OnkoTest")
                        .text("Podany czynnik ryzyka został dodany automatycznie.")
                        .showInformation();
            }
            showOutputMessage(text.toString());

        } catch (JessException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OnkoTest");
            alert.setHeaderText("Błąd podczas wykonywania daignozy");
            alert.setContentText(ex.getLocalizedMessage());
            alert.showAndWait();
        }

    }

    private static boolean isNoFactor(String result) {
        return result == null ? "" == null : result.equals("");
    }

    private static boolean isFactor(String result, int i) {
        return result.charAt(i) == '1';
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
