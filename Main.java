import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        File f = new File("Riemann Deck.text");

        ArrayList<Card> deck = fillDeck(f);

        String[] arr1 = { "Land" };
        String[] arr2 = { "SacLand", "LandRecursion" };

        printAtrStats(deck, arr1, 7, 3);
        printAtrStats(deck, arr2, 10, 1);
        printDeckStats(deck, 7, 1);
    }

    public static ArrayList<Card> fillDeck(File f) {
        ArrayList<Card> deck = new ArrayList<>();
        String line;

        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(f));
            while ((line = br.readLine()) != null) {
                String[] arr1 = line.split("; ");
                String[] arr2 = arr1[1].split(", ");
                deck.add(new Card(Integer.parseInt(arr1[0]), arr2));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error during file reading: " + e.getMessage());
        }

        return deck;
    }

    public static int attributeCount(ArrayList<Card> deck, String str) {
        int c = 0;
        for (Card i : deck) {
            if (i.containsAttribute(str)) {
                c += i.getCount();
            }
        }
        return c;
    }

    public static int cardCount(ArrayList<Card> deck) {
        int c = 0;
        for (Card card : deck) {
            c += card.getCount();
        }
        return c;
    }

    public static ArrayList<String> attributeList(ArrayList<Card> deck) {
        ArrayList<String> atrNew = new ArrayList<>();
        for (Card c : deck) {
            for (String s : c.getAttributes()) {
                if (!atrNew.contains(s)) {
                    atrNew.add(s);
                }
            }
        }
        return atrNew;
    }

    public static String singleAtrStat(ArrayList<Card> deck, String atr, int drawTop, int atrCount) {
        int c = attributeCount(deck, atr);
        int len = cardCount(deck);

        float percNot = 1;
        for (int i = 0; i < drawTop; i++) {
            percNot *= ((float) (len - (c / atrCount))) / ((float) len);
            len -= 1;
        }

        float perc = 1 - percNot;

        return ((double) Math.round(((perc) * 10000)) / 100) + "%";
    }

    public static String atrStat(ArrayList<Card> deck, String[] atr, int drawTop, int atrCount) {

        float percNot = 1;

        for (String s : atr) {
            int c = attributeCount(deck, s);
            int len = cardCount(deck);

            for (int i = 0; i < drawTop; i++) {
                percNot *= ((float) (len - c / atrCount)) / ((float) len);
                len -= 1;
            }
        }

        float perc = 1 - percNot;

        return ((double) Math.round(((perc) * 100) * 100) / 100) + "%";
    }

    public static ArrayList<String> deckStat(ArrayList<Card> deck, int drawTop, int atrCount) {
        ArrayList<String> out = new ArrayList<>();
        for (String s : attributeList(deck)) {
            out.add(s + ": " + singleAtrStat(deck, s, drawTop, atrCount));
        }

        return out;
    }

    public static void printDeckStats(ArrayList<Card> deck, int drawTop, int atrCount) {
        ArrayList<String> s = deckStat(deck, drawTop, atrCount);
        System.out.println("Draw chance of " + atrCount + " card, from " + drawTop + " draws, for the type:");
        for (int i = 0; i < attributeList(deck).size(); i++) {
            System.out.println(s.get(i));
        }
    }

    public static void printAtrStats(ArrayList<Card> deck, String[] atr, int drawTop, int atrCount) {
        System.out.println("Draw chance of " + atrCount + " " + Arrays.asList(atr) + " cards in " + drawTop + " draws: "
                + atrStat(deck, atr, drawTop, atrCount));
    }

    public static void printDeck(ArrayList<Card> deck) {
        System.out.println("Deck:");
        for (Card c : deck) {
            System.out.println("Count: " + c.getCount() + " of: " + Arrays.asList(c.getAttributes()));
        }
    }
}
