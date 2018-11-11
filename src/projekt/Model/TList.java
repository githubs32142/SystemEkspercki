/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javafx.scene.control.Alert;
import projekt.Interface.*;

/**
 *
 * @author Admin
 */
public class TList implements ReadData, Operation {

    private List<TObject> listObject = new ArrayList<>();

    public TList() {
    }

    public TList(String path) {
        readData(path);
    }

    @Override
    public String readInput(String path) {
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
            return "";
        }
    }

    @Override
    public void readData(String path) {
        listObject.clear();
        String line = readInput(path);
        if (!"".equals(line)) {
            StringTokenizer st = new StringTokenizer(line, "-;");
            boolean addNewObject = true;
            while (st.hasMoreElements()) {
                if (addNewObject) {
                    addNewObject();
                    addFullName(st);
                } else {
                    addAlias(st);
                }
                addNewObject = !addNewObject;
            }
        } else {
           showOutputMessage("Błąd! brak ścieżki dostępu.\nProgram zostanie zamknięty");
            System.exit(1);
        }
    }

    private void addNewObject() {
        listObject.add(new TObject());
    }

    private void addAlias(StringTokenizer st) {
        listObject.get(listObject.size() - 1).setAlias(st.nextElement().toString());
    }

    private void addFullName(StringTokenizer st) {
        listObject.get(listObject.size() - 1).setFactor(st.nextElement().toString());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < listObject.size(); i++) {
            str.append(listObject.get(i).getAlias()).append("\n");
        }
        return str.toString();

    }

    @Override
    public boolean contains(String s) {
        for (int i = 0; i < listObject.size(); i++) {
            if (listObject.get(i).getFactor().equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     ** Metoda, która przesyła strumień danych do systemu ekspertowego
     *
     * @param s- Nazwa szablonu
     * @return strumień danych w postaci łańcucha znaków
     */
    @Override
    public String makeAssert(String s) {
        StringBuilder str = new StringBuilder();
        str.append("( assert ( ").append(s);
        for (int i = 0; i < listObject.size(); i++) {
            str.append("( ").append(listObject.get(i).getAlias())
                    .append(" ")
                    .append(listObject.get(i).getAddedString())
                    .append(" ) ");
        }
        str.append(") )");
        return str.toString();
    }

    @Override
    public void makeOperation(List<String> list) {
        for (int i = 0; i < listObject.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (EqualString.equals(listObject.get(i).getFactor(), list.get(j))) {
                    listObject.get(i).setIsAdded(true);
                }
            }
        }
    }

    public void showOutputMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Treść błędu");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public int size() {
        return listObject.size();
    }
}
