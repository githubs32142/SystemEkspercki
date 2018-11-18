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
import lombok.Getter;
import projekt.Interface.ReadData;

/**
 *
 * @author Admin
 */
public class FCList implements ReadData {

    @Getter
    List<FCObject> list = new ArrayList<>();

    public FCList(String path) {
        readData(path);
    }

    public FCList() {
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

    /**
     ** Metoda, która odczytuje dane z pliku txt.
     *
     * @param path
     */
    @Override
    public void readData(String path) {
        list.clear();
        String line = readInput(path);
        if (!"".equals(line)) {
            StringTokenizer st = new StringTokenizer(line, "-;:");
            int count = 0;
            while (st.hasMoreElements()) {
                switch (count % 3) {
                    case 0: {
                        list.add(new FCObject());
                        list.get(list.size() - 1).setFamily(st.nextElement().toString());
                    }
                    break;
                    case 1: {
                        list.get(list.size() - 1).setCancer(st.nextElement().toString());
                    }
                    break;
                    case 2: {
                        list.get(list.size() - 1).setAlians(st.nextElement().toString());
                    }
                    break;
                }
                count++;
            }
        } else {
            showOutputMessage("Błąd! brak ścieżki dostępu.\nProgram zostanie zamknięty");
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            //  str.append(list.get(i).getFamily()).append(" ").append(list.get(i).getCancer()).append(" ").append(list.get(i).getAlians());
            str.append("(slot ").append(list.get(i).getAlians()).append(")\n");
        }
        return str.toString();
    }

    public boolean contains(CancerFamilly cf) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCancer().equals(cf.getCancer()) && list.get(i).getFamily().equals(cf.getFamilly())) {
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
    public String makeAssert(String s) {
        StringBuilder str = new StringBuilder();
        str.append("( assert ( ").append(s);
        for (int i = 0; i < list.size(); i++) {
            str.append("( ").append(list.get(i).getAlians()).append(" ").append(list.get(i).getAddedString()).append(" ) ");
        }
        str.append(") )");
        return str.toString();
    }

    public void makeOperation(List<CancerFamilly> list) {
        int tmp;
        for (int i = 0; i < this.list.size(); i++) {
            tmp = 0;
            for (int j = 0; j < list.size(); j++) {
                if (isTheSameCancer(i, list, j) && isTheSameFamilly(i, list, j)) {
                    tmp++;
                }

            }
            this.list.get(i).setAdded(tmp);
        }
    }

    private boolean isTheSameFamilly(int i, List<CancerFamilly> list1, int j) {
        return EqualString.equals(this.list.get(i).getFamily(), list1.get(j).getFamilly());
    }

    private boolean isTheSameCancer(int i, List<CancerFamilly> list1, int j) {
        return EqualString.equals(this.list.get(i).getCancer(), list1.get(j).getCancer());
    }

    /**
     ** Metoda która zwraca ilość elementów na liście
     *
     * @return ilość elementów listy
     */
    public int size() {
        return this.list.size();
    }

    public String getAlias(int i) {
        if (i >= 0 && i < list.size()) {
            return list.get(i).getAlians();
        }
        return "";
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
}
