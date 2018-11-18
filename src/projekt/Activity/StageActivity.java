package projekt.Activity;

import com.sun.istack.internal.NotNull;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;

public class StageActivity {

  public static void minimalizeStage(@NotNull Stage stage,@NotNull Rectangle2D rec2, @NotNull Double w, @NotNull Double h){
    if (stage.isMaximized()) {
      w = rec2.getWidth();
      h = rec2.getHeight();
      stage.setMaximized(false);
      stage.setHeight(h);
      stage.setWidth(w);
      stage.centerOnScreen();
      Platform.runLater(() -> stage.setIconified(true));
    } else {
      stage.setIconified(true);
    }
  }
  public static void maximalizeStage(@NotNull Stage stage,@NotNull Rectangle2D rec2, @NotNull Double w, @NotNull Double h){
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
  public static void fullStage(@NotNull Stage stage){
    if (stage.isFullScreen()) {
      stage.setFullScreen(false);
    } else {
      stage.setFullScreen(true);
    }
  }

}
