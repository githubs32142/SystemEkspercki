package projekt.Activity;

import javafx.scene.control.Alert;

public class AlertActivity {

  public static void showOutputMessageError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Powiadomienie");
    alert.setHeaderText("Treść komunikatu:");
    alert.setContentText(message);
    alert.showAndWait();
  }

  public static void showOutputMessageDiagnose(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Wynik diagnozy");
    alert.setHeaderText("Otrzymane rezulataty");
    alert.setContentText(message);
    alert.showAndWait();
  }
}
