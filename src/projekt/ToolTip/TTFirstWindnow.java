package projekt.ToolTip;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import projekt.Controller.FirstWindowController;

public class TTFirstWindnow {
    
    public TTFirstWindnow(FirstWindowController fwc) {
        
        Tooltip ttname = new Tooltip();
        ttname.setText("Przykłady poprawnych imion:     \n"
                + "Jan\nMarek\nMonika\nitp...");
        ttname.setStyle("-fx-font: normal  15 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: #FFFFFF;");
        
        fwc.name.setTooltip(ttname);
        Tooltip ttsurname = new Tooltip();
        ttsurname.setText("Przykłady poprawnie wprwadzonych nawisk:     \n"
                + "Kowalski\nNowak\nitp...");
        ttsurname.setStyle("-fx-font: normal  15 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: #FFFFFF;");
        
        fwc.surname.setTooltip(ttsurname);
        Tooltip ttsweight = new Tooltip();
        ttsweight.setText("Przykłady poprawnie wprwadzonych wag w kligramach:     \n"
                + "70,32\n87\nitp...");
        ttsweight.setStyle("-fx-font: normal  15 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: #FFFFFF;");
        
        TextField tmp = fwc.getWeight();
        tmp.setTooltip(ttsweight);
        fwc.setWeight(tmp);
        Tooltip ttheight = new Tooltip();
        ttheight.setText("Przykłady poprawnie wprwadzonego wzrostu w centymetrach:     \n"
                + "159\n88\nitp...");
        ttheight.setStyle("-fx-font: normal  15 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: #FFFFFF;");
        
        tmp = fwc.getHeight();
        tmp.setTooltip(ttheight);
        fwc.setHeight(tmp);
        Tooltip ttage = new Tooltip();
        ttage.setText("Przykłady poprawnie wprwadzonego wieku w latach:     \n"
                + "1\n28\nitp...");
        ttage.setStyle("-fx-font: normal  15 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: #FFFFFF;");
        
        tmp = fwc.getAge();
        tmp.setTooltip(ttage);
        fwc.setAge(tmp);
    }
    
}
