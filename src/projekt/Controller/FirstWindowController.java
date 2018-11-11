package projekt.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;
import projekt.Model.CheckReg;
import projekt.Model.Person;
import projekt.Propertis.FXMLConfigPath;
import projekt.ToolTip.TTFirstWindnow;
import projekt.animations.FadeInLeftTransition;
import projekt.animations.FadeInRightTransition;
import projekt.animations.FadeInUpTransition;

/**
 ** Klasa, ktora obsługuje plik FXML FirstWindow
 *
 * @author Andrzej Kierepka
 */
public class FirstWindowController implements Initializable {

    ObservableList<String> sexList = FXCollections.observableArrayList("Kobieta", "Mężczyzna");
    private boolean mail = false;
    @FXML
    public TextField name;
    @FXML
    public TextField surname;
    @FXML
    private TextField weight;
    @FXML
    private TextField age;
    @FXML
    private ChoiceBox<String> sex;
    @FXML
    public Label toplbl;
    @FXML
    public Label lblname;
    @FXML
    public Label lblsurname;
    @FXML
    private Label lblweight;
    @FXML
    private Label lblage;
    @FXML
    private Label lblsex;
    @FXML
    private Button next;
    @FXML
    private Button close;
    @FXML
    private TextField height;
    @FXML
    private Label lbllheight;
    @FXML
    private CheckBox eMail;
    private FactorWindowController fwc;
    private CancerInFamillyController cif;
    private SymptomWindowController sw;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane root;

    /**
     ** Konstruktor bezparametrowy
     */
    public FirstWindowController() {
        sw = new SymptomWindowController();
        cif = new CancerInFamillyController();
        fwc = new FactorWindowController();

    }

    /**
     ** Konstruktor z prametrem w którym podajemy osobę
     *
     * @param p instacja klasy Person
     */
    public FirstWindowController(Person p) {
        name.setText(p.getName());
        surname.setText(p.getSurName());
        weight.setText(String.valueOf(p.getWeight()));
        height.setText(String.valueOf(p.getHeight()));
        age.setText(String.valueOf(p.getAge()));
        sw = new SymptomWindowController();
        cif = new CancerInFamillyController();
        fwc = new FactorWindowController();

    }

    /**
     ** Inicjalizacja kontrolera
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            new FadeInRightTransition(name).play();
            new FadeInRightTransition(surname).play();
            new FadeInRightTransition(weight).play();
            new FadeInRightTransition(age).play();
            new FadeInRightTransition(sex).play();
            new FadeInLeftTransition(lblname).play();
            new FadeInLeftTransition(lblsurname).play();
            new FadeInLeftTransition(lblweight).play();
            new FadeInLeftTransition(lblage).play();
            new FadeInLeftTransition(lblname).play();
            new FadeInLeftTransition(lblsex).play();
            new FadeInLeftTransition(lbllheight).play();
            new FadeInUpTransition(toplbl).play();
            new FadeInUpTransition(next).play();
            new FadeInUpTransition(close).play();
            new FadeInUpTransition(height).play();
            new FadeInUpTransition(eMail).play();
        });
        sex.setItems(sexList);
        sex.getSelectionModel().selectFirst();
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
        new TTFirstWindnow(this);

    }

    /**
     ** Metoda, która powoduje przejście do następnego okna
     *
     * @param event zdarzenie obsługujące przyciśnięcieprzycisku
     * @throws IOException wyjątek który może być sposodowany przez złe podanie
     * ścieżek dostępu
     */
    @FXML
    private void nextWindow(ActionEvent event) throws IOException {
        Double w = new Double(0.0);
        int h = 0;
        int a = 0;
        if (mail) {
            if (!isEmptySurname() && !isEmptySex()) {
                try {
                    if (CheckReg.checkHeightCent(height.getText()) || CheckReg.checkHeightMetr(height.getText())) {
                        w = Double.valueOf(height.getText());
                        if (CheckReg.checkHeightMetr(height.getText())) {
                            w *= 100;
                        }
                        h = w.intValue();
                        w = 0.0;
                    } else {
                        showOutputMessage("Niepoprawnie wprowadzono dane na temat wzrostu.\nWzrost podajemy w centymertrach!");
                        return;
                    }

                    if (CheckReg.checkWeight(weight.getText()) || CheckReg.checkWeight2(weight.getText())) {
                        w = Double.valueOf(weight.getText());
                    } else {
                        showOutputMessage("Niepoprawnie wprowadzono dane na temat wagi.\nWagę podajemy w kilogramach!");
                        return;
                    }

                    if (CheckReg.checkAge(age.getText())) {
                        a = Integer.parseInt(age.getText());
                    } else {
                        showOutputMessage("Niepoprawnie wprowadzono dane na temat wieku.\nWiek podajemy w latach!");
                        return;
                    }

                    Person p = new Person(surname.getText(), w, a, sex.getValue(), h);
                    if (CheckReg.checkEmail(surname.getText())) {
                        FXMLLoader load = new FXMLLoader(this.getClass().getResource(FXMLConfigPath.FACTOR_WINDOW_FXML));
                        FactorWindowController cnt = new FactorWindowController(p);
                        Parent parent = load.load();
                        cnt = load.getController();
                        cnt.setPerson(p);
                        if (p.getBmi() > 25) {
                            cnt.changeFactToRight("Otyłość");
                            Notifications.create()
                                    .title("OnkoTest")
                                    .text("Drogi użytkowniku, Twoje bmi wynosi:" + p.getBmi() + ".\n Twoje BMI mówi, że jesteś osobą otyłą.")
                                    .showInformation();
                        }
                        for (int i = 0; i < fwc.dataRight.size(); i++) {
                            cnt.changeFactToRight(fwc.dataRight.get(i));
                        }
                        if (p.getBmi() < 25) {
                            cnt.changeFactToLeft("Otyłość");
                        }
                        cnt.setSymptomWindowController(sw);
                        cnt.setCancerInFamillyController(cif);
                        Scene scene = new Scene(parent);
                        Stage primaryStage = new Stage();
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        Stage stage;
                        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                        stage.close();
                    } else {
                        showOutputMessage("Wprowadzony adres e-mail jest nieprawidłowy.\nPrzkładowy poprawny adres e-mail: jan_kowalski@email.com");
                    }
                } catch (IOException | NumberFormatException e) {
                    showOutputMessage("Niepoprawnie wprowadzono dane na temat wagi.\nWagę podajemy w kilogramach!");
                }
            } else {
                showOutputMessage("Wypełnij pola które nie zostały jeszcze uzupełnione");
            }
        } else {
            if (!isEmptyName() && !isEmptySurname() && !isEmptySex()) {
                try {
                    if (CheckReg.checkWord(name.getText()) && CheckReg.checkWord(surname.getText())) {
                        if (CheckReg.checkHeightCent(height.getText()) || CheckReg.checkHeightMetr(height.getText())) {
                            w = Double.valueOf(height.getText());
                            if (CheckReg.checkHeightMetr(height.getText())) {
                                w *= 100;
                            }
                            h = w.intValue();
                            w = 0.0;
                        } else {
                            showOutputMessage("Niepoprawnie wprowadzono dane na temat wzrostu.\nWzrost podajemy w centymertrach!");
                            return;
                        }

                        if (CheckReg.checkWeight(weight.getText()) || CheckReg.checkWeight2(weight.getText())) {
                            w = Double.valueOf(weight.getText());
                        } else {
                            showOutputMessage("Niepoprawnie wprowadzono dane na temat wagi.\nWagę podajemy w kilogramach!");
                            return;
                        }

                        if (CheckReg.checkAge(age.getText())) {
                            a = Integer.parseInt(age.getText());
                        } else {
                            showOutputMessage("Niepoprawnie wprowadzono dane na temat wieku.\nWiek podajemy w latach!");
                            return;
                        }

                        Person p = new Person(name.getText(), surname.getText(), w, a, sex.getValue(), h);
                        FXMLLoader load = new FXMLLoader(this.getClass().getResource(FXMLConfigPath.FACTOR_WINDOW_FXML));
                        FactorWindowController cnt = new FactorWindowController(p);
                        Parent parent = load.load();
                        cnt = load.getController();
                        cnt.setPerson(p);
                        if (p.getBmi() > 25) {
                            cnt.changeFactToRight("Otyłość");

                        }
                        for (int i = 0; i < fwc.dataRight.size(); i++) {
                            cnt.changeFactToRight(fwc.dataRight.get(i));
                        }
                        if (p.getBmi() < 25) {
                            cnt.changeFactToLeft("Otyłość");
                        }
                        cnt.setSymptomWindowController(sw);
                        cnt.setCancerInFamillyController(cif);
                        Scene scene = new Scene(parent);
                        Stage primaryStage = new Stage();
                        primaryStage.initStyle(StageStyle.UNDECORATED);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        Stage stage;
                        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                        stage.close();
                        if (p.getBmi() > 25) {
                            Notifications.create()
                                    .title("OnkoTest")
                                    .text("Drogi użytkowniku, Twoje bmi wynosi: " + p.getBmi() + ".\n Twoje BMI mówi, że jesteś osobą otyłą.")
                                    .darkStyle()
                                    .showWarning();
                        }
                    } else {
                        showOutputMessage("Niepoprawnie wprowadzono imie i/lub nazwisko.\nKażde imię i nazwisko powinno zaczynać się od dużej litery\nPrzykład prawidłowego imienia: Jan \nPrzykład prawidłowego nazwiska: Nowak");
                    }
                } catch (IOException | NumberFormatException e) {
                    showOutputMessage(e.toString());
                }
            } else {
                showOutputMessage("Wypełnij dane, które nie zostały jeszcze uzupełnione!");
            }
        }

    }

    private boolean isEmptyName() {
        return name.getText().isEmpty();
    }

    private boolean isEmptySex() {
        return sex.getValue().isEmpty();
    }

    private boolean isEmptySurname() {
        return surname.getText().isEmpty();
    }

    /**
     ** Metoda, która zamyka okno
     *
     * @param event obsługa zdarzenia polegająca na przyciśnięciu przycisku
     */
    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }

    /**
     ** Metoda, która zamyka okno
     *
     * @param event obsługa zdarzenia polegająca na przyciśnięciu obiektu
     */
    @FXML
    private void mouseClosed(MouseEvent event) {
        Platform.exit();
    }

    /**
     ** Metoda, która ustawia osobę
     *
     * @param p instacjna która imituje osobę
     */
    public void setPerson(Person p) {
        name.setText(p.getName());
        surname.setText(p.getSurName());
        weight.setText(String.valueOf(p.getWeight()));
        height.setText(String.valueOf(p.getHeight()));
        age.setText(String.valueOf(p.getAge()));
    }

    /**
     ** Metoda, która oznacza zaznaczenie kliknięcia w zaznaczenie
     *
     * @param event obsługa zdarzenia kliknięcia w e-mail
     */
    @FXML
    private void eMailClicked(ActionEvent event) {
        mail = !mail;
        if (mail) {
            lblname.setVisible(false);
            name.setVisible(false);
            lblsurname.setText("E-mail:");
            surname.setPromptText("Podaj swój e-mail");
            toplbl.setLayoutX(186);
            toplbl.setLayoutY(50);
        } else {
            toplbl.setLayoutY(10);
            lblname.setVisible(true);
            name.setVisible(true);
            lblsurname.setText("Nazwisko:");
            surname.setPromptText("Podaj swóje nazwisko");
        }
    }

    /**
     ** Metoda, która ustawiia e-mail
     *
     * @param mail true- trzeba podać e-mail
     */
    public void setMail(boolean mail) {
        this.mail = mail;
        eMail.setSelected(mail);

    }

    /**
     ** Metoda, która zwraca e-mail
     *
     * @return e-mail
     */
    public CheckBox geteMail() {
        return eMail;
    }

    /**
     ** wyświetla okno dialogowe
     *
     * @param message wiadomość
     */
    private void showOutputMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Powiadomienie");
        alert.setHeaderText("Treść komunikatu:");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     ** Metoda pozwala na instancji klasy SymptomWindowController
     *
     * @param sw instancji klasy SymptomWindowController
     */
    public void setSymptomWindowController(SymptomWindowController sw) {
        this.sw = sw;
    }

    /**
     ** Metoda pozwala na instancji klasy CancerInFamillyController
     *
     * @param cif instancji klasy CancerInFamillyController
     */
    public void setCancerInFamillyController(CancerInFamillyController cif) {
        this.cif = cif;
    }

    /**
     ** Metoda pozwala na instancji klasy FactorWindowController
     *
     * @param fwc instancji klasy FactorWindowController
     */
    public void setFactorWindowController(FactorWindowController fwc) {
        this.fwc = fwc;
    }

    public TextField getAge() {
        return age;
    }

    public ObservableList<String> getSexList() {
        return sexList;
    }

    public TextField getWeight() {
        return weight;
    }

    public TextField getName() {
        return name;
    }

    public TextField getHeight() {
        return height;
    }

    public TextField getSurname() {
        return surname;
    }

    public void setAge(TextField age) {
        this.age = age;
    }

    public void setHeight(TextField height) {
        this.height = height;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public void setSurname(TextField surname) {
        this.surname = surname;
    }

    public void setWeight(TextField weight) {
        this.weight = weight;
    }

}
