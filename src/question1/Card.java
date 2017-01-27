/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question1;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author tiberiusimionvoicu
 */
public class Card
        implements Serializable,Comparable<Card> {
    private static final long serialVersionUID = 102;

    /**
     *  enum containing the Suit (CLUB,DIAMOND
     *                            ,HEARH,SPADE)
     */
    enum Suit {CLUB,DIAMOND,HEART,SPADE}

    /**
     *  enum containing the Rank
     *  from TWO - ACE
     *  TEN - Ace value = 10
     *  TWO - NINE value = the number
     *  Rank.ordinal = place in list
     */
    public enum Rank {

        TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),
        EIGHT(8),NINE(9),TEN(10),JACK(10),QUEEN(10),KING(10),ACE(11);

        protected final int value;

        /** Constructor
         *  initializes enum with given nr;
         *
         * @param nr - number to initialize enum
         */
        Rank(int nr){ this.value = nr; }
        /** Method to return
         *  next Rank of this
         *  rank object
         *
         * @return  - return next rank of this object
         */
        public Rank getNext() {
            Rank [] ranks = Rank.values();
            int ordinal = this.ordinal();
            if (ordinal == 12) { return ranks[0]; }
            else ordinal = ++ordinal % ranks.length;
            return ranks[ordinal];
        }
    }

    public Rank rank;
    public Suit suit;


    /** Constructor initializes
     *  both fields of card
     *
     * @param suit - suit of the Card
     * @param rank - rank of the Card
     */
    public Card(Rank rank, Suit suit){
        this.suit = suit;
        this.rank = rank;
    }

    /** Basic getter
     *
     * @return suit of the card
     */
    public Suit getSuit(){ return this.suit; }

    /** Basic getter
     *
     * @return rank of the card
     */
    public Rank getRank(){ return this.rank; }

    /** Basic setter
     *  set the value of card.suit
     *
     * @param suit
     */
    public void setSuit(Suit suit){ this.suit = suit; }

    /** Basic setter
     *  Set the value of card.rank
     *
     * @param rank
     */
    public void setRank(Rank rank){ this.rank = rank; }

    /** Method difference
     *  use to get the difference
     *  in ranks of two card objects
     *
     * @param first     - first card object
     * @param second    - second card object
     * @return          - difference in rank
     */
    public static int difference (Card first, Card second){
        int difference = first.rank.ordinal() - second.rank.ordinal();
        return difference;
    }

    /** Method differenceValue
     *  use to get the difference
     *  in rank VALUES of two
     *  cards taken as parameters
     *
     * @param first     - First card object
     * @param second    - Second card object
     * @return          - Return difference in rank(Value)
     */
    public static int differenceValue (Card first, Card second) {
        int diffVal = first.rank.value - second.rank.value;
        if (diffVal < 0) {diffVal = diffVal * -1; }
        return diffVal;
    }

    /** Give string representation
     *  of a card Object
     *  overrides toString method
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Rank : ").append(this.rank).append(" ");
        str.append("Suit : ").append(this.suit).append("\n");
        return str.toString();
    }

    /** Static class CompareDescending
     *  used to compare two cards
     *  and can be used to sort cards
     *  in ascending order by rank
     *  then by suit
     *
     *  implements Comparator<Card>
     *  so it will only take card objects
     *
     */
    public static class CompareDescending implements Comparator<Card> {
        /**Compare method
         * used to compare
         * two card objects
         * received as parameters
         * returns result of comparison
         *
         * overrides compare method
         * in comparator
         * accepts only card objects
         *
         * @param o1    - First card object
         * @param o2    - Second card object
         * @return
         */
        @Override
        public int compare(Card o1, Card o2) {
            if(o1.getRank().ordinal() < o2.getRank().ordinal())
                return 1;
            else if (o1.getRank().ordinal() == o2.getRank().ordinal())
                return o1.getSuit().compareTo(o2.getSuit());
            return -1;
        }
    }

    /** Static class CompareSuit
     *  used to compare two cards
     *  and can be used to sort cards
     *  in descending order by suit
     *  then by rank
     *
     *  implements Comparator<Card>
     *  so it will only take card objects
     *
     */
    public static class CompareSuit implements Comparator<Card> {
        /**Compare method
         * used to compare
         * two card objects
         * received as parameters
         *
         * overrides compare method
         * in comparator
         * accepts only card objects
         * @param o1    - first card object
         * @param o2    - second card object
         * @return      - return result of comparison
         */
        @Override
        public int compare(Card o1, Card o2) {
            if (o1.getSuit().ordinal() < o2.getSuit().ordinal())
                return -1;
            else if (o1.getSuit().ordinal() == o2.getSuit().ordinal())
                return o1.getRank().compareTo(o2.getRank());
            return 1;
        }
    }

    /** Compare to method
     *  used to compare acard
     *  give as parameter to
     *  this cards object
     *  it overrides compareTo
     *  method in comparable interface
     *  takes only card objects
     *
     *  can be used to sort cards
     *  in ascending order by rank
     *  then by suit
     *
     * @param acard - a card object
     * @return result of comparison
     */
    @Override
    public int compareTo(Card acard) {
        if(this.getRank().ordinal() < acard.getRank().ordinal())
            return -1;
        else if (this.getRank().ordinal() == acard.getRank().ordinal())
            return this.getSuit().compareTo(acard.getSuit());
        return 1;
    }
}
