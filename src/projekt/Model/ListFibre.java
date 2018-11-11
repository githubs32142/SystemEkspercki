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

/**
 ** Klasa przeznaczona do zarządzania listą produktów oraz iich zawartością
 * błonnika
 *
 * @author Andrzej Kierepka
 */
public class ListFibre {

    List<ProductFibre> listFibre = new ArrayList<>();

    public ListFibre() {
    }

    /**
     ** Metoda która pozwala na odczytywanie listy z pliku tekstowego
     *
     * @param path ścirżka dostępu
     * @return zawartość pliku tekstowego w kodowaniu UTF-8
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

    public void readData(String path) {
        String line = readInput(path);
        StringTokenizer st = new StringTokenizer(line, "-\n");
        boolean count = true;
        while (st.hasMoreElements()) {
            if (count) {
                listFibre.add(new ProductFibre());
                listFibre.get(listFibre.size() - 1).setNameProduct(st.nextElement().toString());
            } else {
                listFibre.get(listFibre.size() - 1).setFibre(Double.valueOf(st.nextElement().toString()));
            }
            count = !count;
        }

    }

    public int size() {
        return listFibre.size();
    }

    public int searchProduct(String name) {
        for (int i = 0; i < listFibre.size(); i++) {
            if (listFibre.get(i).getNameProduct().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public String getNameProduct(int i) {
        return listFibre.get(i).getNameProduct();
    }

    public void setNameProduct(int i, String nameProduct) {
        listFibre.get(i).setNameProduct(nameProduct);
    }

    public ProductFibre getFibre(int i) {
        return listFibre.get(i);
    }

    public void setFibre(int i, Double fibre) {
        listFibre.get(i).setFibre(fibre);
    }

    public Double getWeight(int i) {
        return listFibre.get(i).getWeight();
    }

    public void setWeight(int i, Double weight) {
        listFibre.get(i).setWeight(weight);
    }

    public void addFibre(String productName, Double weight, Double fibre) {
        listFibre.add(new ProductFibre(productName, fibre));
        listFibre.get(listFibre.size() - 1).setWeight(weight);
    }

    public void addFibre(ProductFibre product) {
        listFibre.add(new ProductFibre(product));
    }

    /**
     ** Metoda która wykonuje sortowanie metodą bąbelkową
     *
     * @param type typ sortowania:
     * <br/> 1- Według nazw rosnąco
     * <br/>2- Według
     * nazw malejąco
     * <br/>3- Według wartości błonnika rosnąco
     * <br/>4- Według wartości błonnika
     * malejąco
     */
    public void sort(int type) {
        for (int i = 0; i < listFibre.size(); i++) {
            for (int j = i; j < listFibre.size(); j++) {
                if (type == 1) {
                    if (!sortWord(listFibre.get(i).getNameProduct(), listFibre.get(j).getNameProduct())) {
                        ProductFibre tmp = listFibre.get(j);
                        listFibre.set(j, listFibre.get(i));
                        listFibre.set(i, tmp);
                    }
                }
                if (type == 2) {
                    if (sortWord(listFibre.get(i).getNameProduct(), listFibre.get(j).getNameProduct())) {
                        ProductFibre tmp = listFibre.get(j);
                        listFibre.set(j, listFibre.get(i));
                        listFibre.set(i, tmp);
                    }
                }
                if (type == 3) {
                    if (listFibre.get(i).getFibre() > listFibre.get(j).getFibre()) {
                        ProductFibre tmp = listFibre.get(j);
                        listFibre.set(j, listFibre.get(i));
                        listFibre.set(i, tmp);
                    }
                }
                if (type == 4) {
                    if (listFibre.get(i).getFibre() < listFibre.get(j).getFibre()) {
                        ProductFibre tmp = listFibre.get(j);
                        listFibre.set(j, listFibre.get(i));
                        listFibre.set(i, tmp);
                    }
                }
            }
        }
    }

    /**
     ** Metoda która sprawdza, czy słowo 1 jest wyżej w alfabecie niż słowo 2
     *
     * @param word1
     * @param word2
     * @return
     */
    private boolean sortWord(String word1, String word2) {
        for (int i = 0; i < word2.length(); i++) {
            if (word1.length() - 1 == i) {
                return true;
            }
            if (word1.charAt(i) < word2.charAt(i)) {
                return true;
            }
            if (word1.charAt(i) > word2.charAt(i)) {
                return false;
            }
        }
        return false;
    }

    /**
     ** Metoda która zwraca ilość błonnika którą spożyliśmy
     *
     * @return suma błonnika wyrażona w gramach.
     */
    public double getSumFibre() {
        double sum = 0;
        for (int i = 0; i < listFibre.size(); i++) {
            sum += ((listFibre.get(i).getWeight() / 100) * listFibre.get(i).getFibre());
        }
        return sum;
    }

    /**
     ** Metoda która usuwa produkt z listy
     *
     * @param index index 
     */
    public void remove(int index) {
        if (index >= 0 && index < listFibre.size()) {
            listFibre.remove(index);
        } else {
        }
    }
}
//pr.readData("src/projekt/Data/jeżyna.txt");
