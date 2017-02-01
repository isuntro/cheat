package question1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
/**         TO DO ITERATE OVER INITIAL ORDER
 * Created by tiberiusimionvoicu on 19/01/2017.
 */
public class Hand implements Serializable,Iterable<Card> {

    private static final long serialVersionUID = 102;
    private ArrayList<Card> cards;
    /* histogram storing counts of every rank(0-12)
       and every suit(13-17), histogram is updated after every
       add or remove, as well as when the hand is constructed
    */
    private int[] histogram = new int[17];
    private int hValue;
    private ArrayList<Card> order;

    /**
     * Default constructor
     * of hand
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Constructor that initializes
     * this hand object by adding
     * cards array to the arrayList
     * field
     *
     * @param cards - array of card objects
     */
    public Hand(Card[] cards) {
        this.cards = new ArrayList<>();
        for (int i = 0; i < cards.length; i++) {
            Card acard = cards[i];
            this.histogram[(acard.getRank().ordinal()) % 13]++;
            this.histogram[(acard.getSuit().ordinal() + 13) % 17]++;
            hValue += acard.getRank().value;
            this.cards.add(acard);
        }
    }

    /**
     * Constructor that initializes
     * this hand object with cards
     * from another hand object
     *
     * @param ahand - a hand object
     */
    public Hand(Hand ahand) {
        this.cards = new ArrayList<>();
        for (Card acard : ahand.cards) {
            histogram[acard.getRank().ordinal() % 13]++;
            histogram[(acard.getSuit().ordinal() + 13) % 17]++;
            hValue += acard.getRank().value;
        }
        this.cards = ahand.cards;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * Basic getter to
     * retrieve this hands histogram
     *
     * @return int[] - this histogram
     */
    public String getHistogram() {
        String output ="";
        for(int i : this.histogram){
            output += Integer.toString(i);
            output += "\n";
        }
        return output;
    }

    /**
     * Basic getter to
     * retrieve this hands value
     *
     * @return int - value of this hand
     */
    public int gethValue() {
        return this.hValue;
    }
    public int size(){
        return this.cards.size();
    }
    /**
     * Method used to add
     * a card object to
     * this hand, it also
     * increases the hValue
     * and increments to the correct
     * positions in the histogram count
     *
     * @param acard - a card object
     */
    public void add(Card acard) {
        histogram[acard.getRank().ordinal() % 13]++;
        histogram[(acard.getSuit().ordinal() + 13) % 17]++;
        this.cards.add(acard);
    }


    /**
     * Method use to add a
     * collection of cards to
     * this hand object, it also
     * increases the hValue
     * and increments the correct
     * positions in the histogram count
     *
     * @param cards
     */
    public void add(Collection<Card> cards) {
        for (Card acard : cards) {
            histogram[acard.getRank().ordinal() % 13]++;
            histogram[(acard.getSuit().ordinal() + 13) % 17]++;
            hValue += acard.getRank().value;
        }
        this.cards.addAll(cards);
    }

    /**
     * Method adds card objects from
     * another hand to this hand it also
     * increases the hValue
     * and adds 1 to the correct
     * positions in the histogram
     *
     * @param ahand - a hand object
     */
    public void add(Hand ahand) {
        for (Card acard : ahand.cards) {
            histogram[acard.getRank().ordinal() % 13]++;
            histogram[(acard.getSuit().ordinal() + 13) % 17]++;
            hValue += acard.getRank().value;
        }
        this.cards.addAll(ahand.cards);
    }

    /**
     * Method removes a given card
     * from this hand, it also
     * decreases the hValue
     * and decrements the correct
     * position in the histogram
     *
     * @param acard - a card object
     * @return - true if succesful
     * false otherwise
     */
    public boolean remove(Card acard) {
        if (this.cards.contains(acard)) {
            histogram[acard.getRank().ordinal() % 13]--;
            histogram[(acard.getSuit().ordinal() + 13) % 17]--;
            hValue -= acard.getRank().value;
            this.cards.remove(acard);
            return true;
        } else return false;
    }

    /**
     * ASK ABOUT METHOD§   CHECK IF SHOULD BE LIKE THIS
     *
     * @param ahand
     * @return
     */
    public boolean remove(Hand ahand) {
        if (this.cards.containsAll(ahand.getCards())) {
            for (Card acard : ahand.getCards()) {
                histogram[acard.getRank().ordinal() % 13]--;
                histogram[(acard.getSuit().ordinal() + 13) % 17]--;
                hValue -= acard.getRank().value;
                this.cards.remove(acard);
            }
            return true;
        }
        return false;
    }

    /**
     * Method to remove a card from
     * this hand at specified index
     *
     * @param index - position in the cards list
     * @return - a card object (present at given index)
     * - null if index higher than number of cards
     */
    public Card remove(int index) {
        if (this.cards.size() < index) {
            return null;
        }
        Card acard = this.cards.get(index);
        histogram[acard.getRank().ordinal() % 13]--;
        histogram[(acard.getSuit().ordinal() + 13) % 17]--;
        hValue -= acard.getRank().value;
        this.cards.remove(index);
        return acard;
    }

    /**
     * ASK ABOUT HAVING TO DO OWN SORT FUNCTION
     * Method used to sort cards in ascending
     * order of rank and then by suit using
     * the overridden compareTo method in card class
     */
    public void sortAscending() {
        this.order = new ArrayList();
        this.order.addAll(this.cards);
        Collections.sort(this.cards);
    }

    /**
     * ASK ABOUT HAVING TO DO OWN SORT FUNCTION
     * Method used to sort cards in descending
     * order of rank and then by suit using
     * the comparator CompareDescending class
     */
    public void sortDescending() {
        this.order = new ArrayList();
        this.order.addAll(this.cards);
        Card.CompareDescending descending = new Card.CompareDescending();
        this.cards.sort(descending);
    }

    /**
     * Method used to count the number of suits
     * of a given suit present in this hand
     *
     * @param asuit - a given Suit to search for
     * @return - int representing number
     * of cards of given Suit;
     */
    public int countSuit(Card.Suit asuit) {
        int count = this.histogram[asuit.ordinal()+13];
        return count;
    }

    /**
     * Method used to count the number of ranks
     * of a given rank present in this hand
     *
     * @param arank - a given Rank to search for
     * @return - int representing the number of
     * cards of given Rank
     */
    public int countRank(Card.Rank arank) {
        int count = this.histogram[arank.ordinal()];
        return count;
    }

    /**
     * Basic getter used to
     * get the value of this hand
     *
     * @return - int representing
     * the value of this hand
     */
    public int handValue() {
        return this.hValue;
    }

    /**
     * Overrides toString method
     *
     * @return - Textual representation
     * of this hand object
     */
    public String toString() {
        String text = "";
        for (Card acard : this.cards) {
            text += (acard);
        }
        return text;
    }

    /**
     * Method checks if this hand object
     * has a flush ie. all the cards present
     * are of the same suit
     *
     * @return - true if has flush
     * false otherwise
     */
    public boolean isFlush() {
        Card check = this.cards.get(0);
        for (Card acard : this.cards) {
            if (acard.getSuit().compareTo(check.getSuit()) != 0)
                return false;
        }
        return true;
    }

    /**
     *
     * Method checks if this hand object
     * has a straight ie. all the cards present
     * are in order eg. TWO,THREE,FOUR,FIVE,SIX
     * however no duplicates are allowed
     *
     * @return - true if has straight
     * false otherwise or if only 1 card
     */
    public boolean isStraight() {
        if (this.cards.size() == 1)
            return false;
        Collections.sort(this.cards);
        int check = this.cards.get(0).getRank().ordinal();
        for (Card acard : this.cards) {
            check++;
            if (acard.getRank().getNext().ordinal() != check)
                return false;
        }
        return true;
    }

    /** Default iterator will return cards
     *  in the order that they were added
     *
     * @return iterator
     */
    @Override
    public Iterator<Card> iterator() {
        return new Iterator<Card>() {
            private int index=0;
            @Override
            public boolean hasNext() {
                return (index != order.size());
            }
            @Override
            public Card next() {
                return order.get(index++);
            }
        };
    }

    public static void main(String[] args) {
        Hand testHand;
        Card[] cards = new Card[5];
        cards[0] = (new Card(Card.Rank.THREE, Card.Suit.DIAMOND));
        cards[1] = (new Card(Card.Rank.TWO, Card.Suit.DIAMOND));
        cards[2] = (new Card(Card.Rank.FOUR, Card.Suit.DIAMOND));
        cards[3] = (new Card(Card.Rank.FIVE, Card.Suit.DIAMOND));
        cards[4] = (new Card(Card.Rank.TWO, Card.Suit.DIAMOND));
        testHand = new Hand(cards);
        Card.Rank testrank = testHand.cards.get(4).getRank();
        Card.Suit testsuit = testHand.cards.get(3).getSuit();
        System.out.print("Testing countRank method:   ");
        System.out.println(testHand.countRank(testrank)+"\n");

        System.out.print("Testing countSuit method:   ");
        System.out.println(testHand.countSuit(testsuit)+"\n");

        System.out.println("Initial Hand : \n" + testHand);
        testHand.sortAscending();
        System.out.println("Testing sort ascending: \n" + testHand);
        testHand.sortDescending();
        System.out.println("Testing sort descending: \n" + testHand);
        System.out.println(testHand.getHistogram());
        Hand test = new Hand();
        test.add(cards[1]);
        test.add(cards[0]);
        testHand.remove(test);
        System.out.println(testHand.getHistogram());
        System.out.println("Testing isStraight :" + testHand.isStraight());
        System.out.println("Testing isStraight :" + testHand.isFlush());



    }
}