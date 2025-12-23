public class Card {
    private String[] attributes;
    private int count;

    public Card(int c, String[] arr) {
        count = c;
        attributes = arr;
    }

    public Boolean containsAttribute(String str) {
        for(int i = 0; i < attributes.length; i++) {    
            if (attributes[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void setAttributes(String[] arr) {
        attributes = arr;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public int getCount() {
        return count;
    }

    public Boolean isBlank() {
        return (attributes.length == 0);
    }
}