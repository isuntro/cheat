package question1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**~
 * Created by tiberiusimionvoicu on 19/01/2017.
 */
public class Deck implements Serializable,Iterable<Card> {

    private static final long serialVersionUID = 101;
    private ArrayList<Card> cards = new ArrayList<>();

    /** Constructor initializes this deck object
     *  with a full deck of 52 cards with no duplicates
     *
     */
    public Deck(){
        for (Card.Rank arank : Card.Rank.values()) {
            for (Card.Suit asuit : Card.Suit.values()) {
                Card acard = new Card(arank, asuit);
                cards.add(acard);
            }
        }
        this.cards = cards;
    }

    /** Method used to shuffle the
     *  cards in this deck object
     *
     */
    public void shuffle() {
        Random rad = new Random();
        for (int i = this.cards.size() - 1; i > 0; i--) {
            int place = rad.nextInt(i);
            Card acard = this.cards.get(place);
            Card swap = this.cards.get(i);
            this.cards.set(i,acard);
            this.cards.set(place,swap);
        }
    }

    /** Basic getter used to get
     *  the amount of cards in this
     *  deck object
     *
     * @return - int representing number of
     *           cards in this hand
     */
    public int size() { return cards.size(); }

    /** Method used to clear this hand object
     *  and readd 52 cards into this object
     *
     */
    public final void newDeck() {
        this.cards.clear();
        for (Card.Rank arank : Card.Rank.values()) {
            for (Card.Suit asuit : Card.Suit.values()) {
                Card acard = new Card(arank, asuit);
                cards.add(acard);
            }
        }
    }

    /** Method used to get the top card
     *  in the deck and remove it
     *
     * @return - top card in the deck
     */
    public Card deal(){
        Card topCard = cards.get(0);
        cards.remove(0);
        return topCard;
    }

    /** Method used to return textual
     *  representation of this deck object
     *
     * @return  - string
     */
    @Override
    public String toString(){
        StringBuilder text = new StringBuilder();
        for (Card acard : cards){
            text.append(acard.toString()).append("\n");
        }

        return text.toString();
    }

    /**
     *
     */
    protected static class OddEvenIterator implements Iterator<Card> {
        private ArrayList<Card> cards;
        private static int index = 0;
        private static boolean flag = true;

        public OddEvenIterator(ArrayList<Card> cards) {
            this.cards = cards;
        }

        /** Function that checks whether any cards
         *  are left in the deck
         *
         * @return  true if there are cards left
         *          false otherwise
         */
        @Override
        public boolean hasNext() {
            return !(cards.size() == index);
        }

        /**
         *
         * @return
         */
        @Override
        public Card next() {
            if((index+2) == cards.size()) {
                flag = false;
                return (cards.get(index));
            }
            if(flag){
                if(!(index % 2 == 0)) {
                    index++;
                }
                return (cards.get(index++));
            }
            if((index % 2 == 0)) {
                index--;
            }
            if ((index-1) < 0 )
                index = cards.size();
            return (cards.get(index--));

        }
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<Card> iterator() {
        Iterator<Card> deflt = new Iterator<Card>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return (index != cards.size());
            }

            @Override
            public Card next() {
                return cards.get(index++);
            }
            @Override
            public void remove() {
                 cards.remove(index);
            }

        };
        return deflt;
    }

}
