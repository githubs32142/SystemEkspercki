package projekt.Model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 ** Klasa, która emiyuje osobę.
 *
 * @author Andrzej Kierepka
 */
@ToString
@EqualsAndHashCode
public class Person {

    private String name;
    private String surName;
    private Double weight;
    private int age;
    private String sex;
    private Double bmi;
    private int height;
    private String email;

    public Person(String name, String surName, Double weight, int age, String sex, int height) {
        this.name = name;
        this.surName = surName;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        double tmp = height;
        bmi = weight / (((tmp / 100) * (tmp / 100)));
        double roundOff = Math.round(bmi * 100.0) / 100.0;
        bmi=roundOff;
        this.height = height;
        email = "";
    }

    public Person(String email, Double weight, int age, String sex, int height) {
        this.name = "";
        this.surName = "";
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.height = height;
        double tmp = height;
        bmi = weight / (((tmp / 100) * (tmp / 100)));
        this.email = email;
    }

    public Person() {
    }

    /**
     ** Metoda zwraca nazwisko
     *
     * @return nazwisko jako łańcuch znaków
     */
    public String getSurName() {
        return surName;
    }

    /**
     ** Metoda, która zwraca wiek
     *
     * @return wiek
     */
    public int getAge() {
        return age;
    }

    /**
     ** Metoda, która zwraca wagę
     *
     * @return waga
     */
    public Double getWeight() {
        return weight;
    }

    /**
     ** Metoda, która zwraca imię
     *
     * @return imię
     */
    public String getName() {
        return name;
    }

    /**
     ** Metoda, która zwraca płeć
     *
     * @return płeć
     */
    public String getSex() {
        return sex;
    }

    /**
     ** Metoda, która zwraca bmi osoby
     *
     * @return bmi
     */
    public Double getBmi() {
        return bmi;
    }

    /**
     ** Metoda, która ustawia wiek
     *
     * @param age wiek
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     ** Metoda, która ustawia bmi
     *
     * @param bmi bmi
     */
    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    /**
     ** Metoda, która zwraca wzrost
     *
     * @return wzrost
     */
    public int getHeight() {
        return height;
    }

    /**
     ** Metoda, która ustawia wysokość
     *
     * @param height wysokość
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     ** Metoda, która ustawia płeć
     *
     * @param sex płeć
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     ** Metoda, która ustawia imię
     *
     * @param name imię
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Metoda, która zwraca adres e-mail
     *
     * @return e-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     ** Metoda, która ustawia e-mail
     *
     * @param email e-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
