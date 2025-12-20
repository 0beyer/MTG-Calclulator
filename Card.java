import java.util.ArrayList;

public class Card {
    private ArrayList<String> attributes;

    public Card(ArrayList<String> arr) {
        attributes = arr;
    }

    public Boolean containsAttribute(String str) {
        for(int i = 0; i < attributes.size(); i++) {    
            if (attributes.get(i).equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void setAttributes(ArrayList<String> arr) {
        attributes = arr;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public Boolean isBlank() {
        return (attributes.size() == 0);
    }
}