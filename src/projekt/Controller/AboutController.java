/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AboutController extends Application implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Hyperlink hl1;
    @FXML
    private Hyperlink hl2;
    @FXML
    private Hyperlink hl3;
    @FXML
    private Hyperlink hl4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void mouseClosed(MouseEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void open1(ActionEvent event) {
         HostServicesDelegate hostServices = HostServicesFactory.getInstance(this);
        getHostServices().showDocument(hl1.getText());
    }

    @FXML
    private void open2(ActionEvent event) {
     HostServicesDelegate hostServices = HostServicesFactory.getInstance(this);
        getHostServices().showDocument(hl2.getText());
    }

    @FXML
    private void open3(ActionEvent event) {
         HostServicesDelegate hostServices = HostServicesFactory.getInstance(this);
        getHostServices().showDocument(hl3.getText());
    }

    @FXML
    private void open4(ActionEvent event) {
         HostServicesDelegate hostServices = HostServicesFactory.getInstance(this);
        getHostServices().showDocument(hl4.getText());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
