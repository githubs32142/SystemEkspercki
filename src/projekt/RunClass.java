package projekt;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import projekt.DB.Hibernate.HibernateInit;
import projekt.Properties.FXMLConfigPath;

/**
 ** Klasa która powoduje uruchomienie programu
 *
 * @author Andrzej Kierepka
 */
public class RunClass extends Application {

    /**
     ** Metoda, która powoduje ładowanie aplikacji wraz ze splash-em
     *
     * @param primaryStage Scena na której znajdzie się formuularz
     * @throws IOException wyjątek wejścia/wyjścia
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        loadSplash(primaryStage);
    }

    /**
     ** Metoda głowna programu
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     ** Metoda która ładuje "Splash Screeen wraz z wejściem, a następnie ładuje
     * okno w którym podajemy dane."
     *
     * @param primaryStage scena na której będzie wyświetlany spalsh i pierwsze
     * okno
     * @throws IOException wyjątek błędnego wejścia/wyjścia.
     */
    public void loadSplash(Stage primaryStage) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource(FXMLConfigPath.SPLASH_SCREEN_FXML));
        Parent parent = load.load();
        Scene scene = new Scene(parent);
        FadeTransition fin = new FadeTransition(Duration.seconds(10), parent);
        fin.setFromValue(0);
        fin.setToValue(6);
        fin.setCycleCount(1);
        fin.play();
        HibernateInit.initSesionFactory();
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
        fin.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader load = new FXMLLoader(this.getClass().getResource(FXMLConfigPath.FIRST_WINDOW_FXML));
                Parent parent = null;
                try {
                    parent = load.load();
                } catch (IOException ex) {
                }
                Scene scene = new Scene(parent);
                FadeTransition fin = new FadeTransition(Duration.seconds(5), parent);
                fin.setFromValue(0);
                fin.setToValue(3);
                fin.setCycleCount(1);
                fin.play();
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
            }
        });

    }
}
