import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Card[] deck = emptyDeck(99);
        ArrayList<Pair> atr = new ArrayList<>();

        ArrayList<String> a0 = new ArrayList<>();
        a0.add("Land");
        ArrayList<String> a1 = new ArrayList<>();
        a1.add("Ramp");
        ArrayList<String> a2 = new ArrayList<>();
        a2.add("Land");
        a2.add("SacLand");
        ArrayList<String> a3 = new ArrayList<>();
        a3.add("LandRecursion");
        ArrayList<String> a4 = new ArrayList<>();
        a4.add("Counters");
        ArrayList<String> a5 = new ArrayList<>();
        a5.add("Counterspell");
        ArrayList<String> a6 = new ArrayList<>();
        a6.add("Draw");
        ArrayList<String> a7 = new ArrayList<>();
        a7.add("LandSac");
        a7.add("Draw");
        ArrayList<String> a8 = new ArrayList<>();
        a8.add("Trample");
        ArrayList<String> a9 = new ArrayList<>();
        a9.add("Ramp");
        a9.add("Draw");
        ArrayList<String> a10 = new ArrayList<>();
        a10.add("Land");
        a10.add("Ramp");
        ArrayList<String> a11 = new ArrayList<>();
        a11.add("Misc");

        Pair Lands = new Pair(25, a0);
        Pair Ramp = new Pair(18, a1);
        Pair SacLands = new Pair(7, a2);
        Pair LandRecursion = new Pair(6, a3);
        Pair Counters = new Pair(10, a4);
        Pair Counterspell = new Pair(8, a5);
        Pair Draw = new Pair(4, a6);
        Pair DrawLandsac = new Pair(2, a7);
        Pair Trample = new  Pair(4, a8);
        Pair DrawRamp = new  Pair(3, a9);
        Pair LandRamp = new Pair(1, a10);
        Pair Misc = new Pair(10, a11);
        
        atr.add(Lands);
        atr.add(Ramp);
        atr.add(SacLands);
        atr.add(LandRecursion);
        atr.add(Counters);
        atr.add(Counterspell);
        atr.add(Draw);
        atr.add(DrawLandsac);
        atr.add(Trample);
        atr.add(DrawRamp);
        atr.add(LandRamp);
        atr.add(Misc);
    
        fillDeck(deck, atr);

        String[] arr1 = {"Land"};
        String[] arr2 = {"SacLand", "LandRecursion"};

        // System.out.println(draw1AtrStatTopdeck(deck, "Land"));
        // System.out.println(draw1AtrStat(deck, "Land", 1));
        // System.out.println(singleAtrStat(deck, "Land", 1, 1));
        // System.out.println(atrStat(deck, arr1, 1, 1));

        // System.out.println(draw1AtrStat(deck, "Land", 7));
        // System.out.println(singleAtrStat(deck, "Land", 7, 1));
        // System.out.println(atrStat(deck, arr1, 7, 1));

        System.out.println(atrStat(deck, arr1, 7, 3));

        System.out.println(atrStat(deck, arr2, 10, 1));

        // System.out.println(draw1DeckStatTopdeck(deck));
        // System.out.println(draw1DeckStat(deck, 7));

        // System.out.println(deckStat(deck, 7, 3));
        printAtrStats(deck, arr1, 7, 3);
        printAtrStats(deck, arr2, 10, 1);
        printDeckStats(deck, 7, 1);
    }

    public static Card[] emptyDeck(int size) {
        Card[] deck = new Card[size];
        ArrayList<String> atr = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            deck[i] = new Card(atr);
        }
        return deck;
    }

    public static Card[] fillDeck(Card[] deck, ArrayList<Pair> atr) {
        int cur = 0;
        for (Pair i : atr) {
            while (i.getCount() != 0) {
                i.decrement();
                deck[cur].setAttributes(i.getAttributes());
                cur++;
            }
        }

        return deck;
    }

    // public static Boolean deckContains(Card[] deck, String str) {
    //     for (Card i : deck) {
    //         if (i.containsAttribute(str)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public static int attributeCount(Card[] deck, String str) {
        int c = 0;
        for (Card i : deck) {
            if (i.containsAttribute(str)) {
                c++;
            }
        }
        return c;
    }

    public static ArrayList<String> attributeList(Card[] deck) {
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

    // public static String draw1AtrStatTopdeck(Card[] deck, String atr) {
    //     int c = attributeCount(deck, atr);
    //     int len = deck.length;

    //     float perc = ((float)c)/((float)len);

    //     return ((double) Math.round(((perc)*100)*100)/100) + "%";
    // }

    // public static String draw1AtrStat(Card[] deck, String atr, int drawTop) {
    //     int c = attributeCount(deck, atr);
    //     int len = deck.length;

    //     float percNot = 1;
    //     for (int i=0; i < drawTop; i++) {
    //         percNot *= ((float)(len - c))/((float)len);
    //         len -= 1;
    //     }

    //     float perc = 1 - percNot;

    //     return ((double) Math.round(((perc)*100)*100)/100) + "%";
    // }

    public static String singleAtrStat(Card[] deck, String atr, int drawTop, int atrCount) {
        int c = attributeCount(deck, atr);
        int len = deck.length;

        float percNot = 1;
        for (int i=0; i < drawTop; i++) {
            percNot *= ((float)(len - c/atrCount))/((float)len);
            len -= 1;
        }

        float perc = 1 - percNot;

        return ((double) Math.round(((perc)*100)*100)/100) + "%";
    }

    public static String atrStat(Card[] deck, String[] atr, int drawTop, int atrCount) {

        float percNot = 1;

        for (String s : atr) {
            int c = attributeCount(deck, s);
            int len = deck.length;

            for (int i=0; i < drawTop; i++) {
                percNot *= ((float)(len - c/atrCount))/((float)len);
                len -= 1;
            }
        }

        float perc = 1 - percNot;

        return ((double) Math.round(((perc)*100)*100)/100) + "%";
    }

    // public static ArrayList<String> draw1DeckStatTopdeck(Card[] deck) {
    //     ArrayList<String> out = new ArrayList<>();
    //     for (String s : attributeList(deck)) {
    //         out.add(s + ": " + draw1AtrStatTopdeck(deck, s));
    //     }
    
    //     return out;
    // }

    // public static ArrayList<String> draw1DeckStat(Card[] deck, int drawTop) {
    //     ArrayList<String> out = new ArrayList<>();
    //     for (String s : attributeList(deck)) {
    //         out.add(s + ": " + draw1AtrStat(deck, s, drawTop));
    //     }
    
    //     return out;
    // }

    public static ArrayList<String> deckStat(Card[] deck, int drawTop, int atrCount) {
        ArrayList<String> out = new ArrayList<>();
        for (String s : attributeList(deck)) {
            out.add(s + ": " + singleAtrStat(deck, s, drawTop, atrCount));
        }
    
        return out;
    }

    public static void printDeckStats(Card[] deck, int drawTop, int atrCount) {
        ArrayList<String> s = deckStat(deck, drawTop, atrCount);
        System.out.println("Draw chance of " + atrCount + " card, from " + drawTop + " draws, for the type:");
        for (int i = 0; i < attributeList(deck).size(); i++) {
            System.out.println(s.get(i));
        }
    }

    public static void printAtrStats(Card[] deck, String[] atr, int drawTop, int atrCount) {
        System.out.println("Draw chance of " + atrCount + " " + Arrays.asList(atr) + " cards in " + drawTop + " draws: " + atrStat(deck, atr, drawTop, atrCount));
    }
}
