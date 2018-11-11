package projekt.Model;

/**
 ** Klasa, która zawiera produkt oraz jego zawartość błonnika
 *
 * @author Andrzej Kierepka
 */
public class ProductFibre {

    private String nameProduct;
    private Double fibre;
    private Double weight;

    public ProductFibre(String nameProduct, Double fibre) {
        this.nameProduct = nameProduct;
        this.fibre = fibre;
    }

    public ProductFibre(ProductFibre product) {
        this.nameProduct = product.nameProduct;
        this.fibre = product.fibre;
        this.weight = product.weight;
    }

    public ProductFibre() {
        fibre = new Double(0);
        nameProduct = new String();
        weight = new Double(100);
    }

    /**
     ** Metoda, która ustawia zawartość błonnika
     *
     * @param fibre zawartość błonnika podana w gramach
     */
    public void setFibre(Double fibre) {
        this.fibre = fibre;
    }

    /**
     ** Metoda, która zwraca nazwę produktu
     *
     * @return nazwa produktu
     */
    public String getNameProduct() {
        return nameProduct;
    }

    /**
     ** Metoda, która zwraca zawartość błonnika
     *
     * @return zawartość błonnika
     */
    public Double getFibre() {
        return fibre;
    }

    /**
     ** Metoda, która ustawia nazwę produktu
     *
     * @param nameProduct naza produktu
     */
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    /**
     ** Metoda, która ustaiwa wagę produktu w gramach
     *
     * @param weight podana waga w kilogramach
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     ** Metoda, która zwraca wagę produktu w gramach
     *
     * @return waga podana w gramach
     */
    public Double getWeight() {
        return weight;
    }

    /**
     ** Metoda stworzona po to aby wyświetlć ile błonnika przyjęto
     *
     * @return łańcuch znaków .
     */
    @Override
    public String toString() {
        return nameProduct + " w " + getWeight() + "g zawiera " + getFibre() * (getWeight() / 100) + "g błonnika";

    }
}
