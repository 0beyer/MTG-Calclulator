import java.util.ArrayList;

public class Pair {

    private int count;
    private ArrayList<String> attributes;

    public Pair(int c, ArrayList<String> arr) {
        count = c;
        attributes = arr;
    }

    public void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }
}
