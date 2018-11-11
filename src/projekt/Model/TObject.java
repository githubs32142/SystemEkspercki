/**
 ** Klasa która odzwierciedla symtomy oraz jej aliasy które są użyte w regułach
 *
 * @author Andrzej Kierepka
 */
package projekt.Model;

public class TObject {

    private String factor;
    private String alias;
    private boolean isAdded;

    public TObject(String symptom, String alias) {
        this.factor = symptom;
        this.alias = alias;
        isAdded = false;
    }

    public TObject() {
        this("", "");
        isAdded = false;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setIsAdded(boolean isAdded) {
        this.isAdded = isAdded;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public String getAddedString() {
        if (isAdded) {
            return "1";
        }
        return "0";
    }

}
