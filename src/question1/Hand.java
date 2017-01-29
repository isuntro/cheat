package question1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by tiberiusimionvoicu on 19/01/2017.
 */
public class Hand implements Serializable,Iterable<Card>{

    private static final long serialVersionUID = 102;
    public ArrayList<Card> cards = new ArrayList<>();
    int [][] histogram = new int [13][4];
    int hValue;

    /** Default constructor
     *  of hand
     */
    public Hand(){  }

    /**Constructor that initializes
     * this hand object by adding
     * cards array to the arrayList
     * field
     *
     *
     * @param cards - array of card objects
     */
    public Hand(Card [] cards){
        for(int i=0;i<cards.length;i++){
            Card acard = cards[i];
            this.cards.add(acard);
            histogram[(acard.getRank().ordinal()) % 13] [(acard.getSuit().ordinal()) % 4] += 1;
            hValue += acard.getRank().value;
        }
    }

    /** Constructor that initializes
     *  this hand object with cards
     *  from another hand object
     *
     * @param ahand - a hand object
     */
    public Hand(Hand ahand){
        for(Card acard : ahand.cards) {
            histogram[acard.getRank().ordinal() % 13] [acard.getSuit().ordinal() % 4] += 1;
            hValue += acard.getRank().value;
        }
        this.cards = ahand.cards;
    }

    /** Method used to add
     *  a card object to
     *  this hand, it also
     *  increases the hValue
     *  and adds 1 to the correct
     *  position in the histogram
     *
     * @param acard - a card object
     */
    public void add(Card acard){
        histogram[acard.getRank().ordinal() % 13] [acard.getSuit().ordinal() % 4] += 1;
        hValue += acard.getRank().value;
        this.cards.add(acard);
    }



    /** Method use to add a
     *  collection of cards to
     *  this hand object, it also
     *  increases the hValue
     *  and adds 1 to the correct
     *  positions in the histogram
     *
     * @param cards
     */
    public void add(Collection<Card> cards) {
        for(Card acard : cards){
            histogram[acard.getRank().ordinal() % 13] [acard.getSuit().ordinal() % 4] += 1;
            hValue += acard.getRank().value;
        }
        this.cards.addAll(cards);
    }

    /** Method adds card objects from
     *  another hand to this hand it also
     *  increases the hValue
     *  and adds 1 to the correct
     *  positions in the histogram
     *
     * @param ahand - a hand object
     */
    public void add(Hand ahand) {
        for(Card acard : ahand.cards){
            histogram[acard.getRank().ordinal() % 13] [acard.getSuit().ordinal() % 4] += 1;
            hValue += acard.getRank().value;
        }
        this.cards.addAll(ahand.cards);
    }

    /** Method removes a given card
     *  from this hand, it also
     *  decreases the hValue
     *  and decrements the correct
     *  position in the histogram
     *
     * @param acard - a card object
     * @return      - true if succesful
     *                false otherwise
     */
    public boolean remove( Card acard) {
        if (this.cards.contains(acard)) {
            histogram[acard.getRank().ordinal() % 13] [acard.getSuit().ordinal() % 4] -= 1;
            hValue -= acard.getRank().value;
            this.cards.remove(acard);
            return true;
        }
        else return false;
    }

    /** ASK ABOUT METHOD§
     *
     * @param ahand
     * @return
     */
    public boolean remove( Hand ahand) {
        if (this.cards.containsAll(ahand.cards)){
            for(Card acard : ahand.cards) {
                histogram[acard.getRank().ordinal() % 13] [acard.getSuit().ordinal() % 4] -= 1;
                hValue -= acard.getRank().value;
                this.remove(acard);
            }
            return true;
        }
        return false;
    }

    /** Method to remove a card from
     *  this hand at specified index
     *
     * @param index - position in the cards list
     * @return  - a card object (present at given index)
     *          - null if index higher than number of cards
     */
    public Card remove (int index) {
        if (this.cards.size() < index) {
            return null;
        }
        Card acard = this.cards.get(index);
        histogram[acard.getRank().ordinal() % 13] [acard.getSuit().ordinal() % 4] -= 1;
        hValue -= acard.getRank().value;
        this.cards.remove(index);
        return acard;
    }

    /** ASK ABOUT HAVING TO DO OWN SORT FUNCTION
     *  Method used to sort cards in ascending
     *  order of rank and then by suit using
     *  the overridden compareTo method in card class
     *
     */
    public void sortAscending() {
        Collections.sort(this.cards);
    }

    /** ASK ABOUT HAVING TO DO OWN SORT FUNCTION
     *  Method used to sort cards in descending
     *  order of rank and then by suit using
     *  the comparator CompareDescending class
     *
     */
    public void sortDescending() {
        Card.CompareDescending descending = new Card.CompareDescending();
        this.cards.sort(descending);
    }
    /** Method used to count the number of suits
     *  of a given suit present in this hand
     *
     * @param asuit - a given Suit to search for
     * @return      - int representing number
     *                of cards of given Suit;
     */
    public int countSuit(Card.Suit asuit) {
        int count = 0;
        for(Card acard : this.cards) {
            if(acard.getSuit().compareTo(asuit)==0){
                count++;
            }
        }
        return count;
    }

    /** Method used to count the number of ranks
     *  of a given rank present in this hand
     *
     * @param arank - a given Rank to search for
     * @return      - int representing the number of
     *                cards of given Rank
     */
    public int countRank(Card.Rank arank) {
        int count = 0;
        for(Card acard : this.cards) {
            if(acard.getRank().compareTo(arank)==0){
                count++;
            }
        }
        return count;
    }

    /** Basic getter used to
     *  get the value of this hand
     *
     * @return  - int representing
     *            the value of this hand
     */
    public int handValue() {
        return this.hValue;
    }

    /** Overrides toString method
     *
     * @return - Textual representation
     *           of this hand object
     */
    public String toString() {
        String text ="";
        for(Card acard : this.cards) {
            text += (acard);
        }
        return text;
    }

    /** Method checks if this hand object
     *  has a flush ie. all the cards present
     *  are of the same suit
     *
     * @return - true if has flush
     *           false otherwise
     */
    public boolean isFlush() {
        Card check = this.cards.get(0);
        for(Card acard : this.cards) {
            if(acard.getSuit().compareTo(check.getSuit()) != 0)
                return false;
        }
        return true;
    }

    /** MAYBE REDO WITHOUT SORTING IF TIME
     *  Method checks if this hand object
     *  has a straight ie. all the cards present
     *  are in order eg. TWO,THREE,FOUR,FIVE,SIX
     *  however no duplicates are allowed
     *
     * @return  - true if has straight
     *            false otherwise or if only 1 card
     */
    public boolean isStraight() {
        if (this.cards.size() == 1)
            return false;
        Collections.sort(this.cards);
        int check = this.cards.get(0).getRank().ordinal();
        for (Card acard : this.cards) {
            check++;
            if(acard.getRank().getNext().ordinal() != check )
                return false;
        }
        return true;
    }
    /** TO DO DEFAULT ITERATOR THAT TRANSVERSES LIST IN ORDER THEY WHERE ADDED
     *
     * @return
     */
    @Override
    public Iterator<Card> iterator() {
        return null;
    }
}
