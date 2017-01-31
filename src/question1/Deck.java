package question1;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**~
 * Created by tiberiusimionvoicu on 19/01/2017.
 */
public class Deck implements Serializable,Iterable<Card> {

    private static final long serialVersionUID = 101;
    protected ArrayList<Card> cards;
    /** Constructor initializes this deck object
     *  with a full deck of 52 cards with no duplicates
     *
     */
    public Deck(){
        cards = new ArrayList<Card>();
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
    private class OddEvenIterator implements Iterator<Card> {
        private int index = 0;
        private boolean flag = false;

        public OddEvenIterator() {
        }

        /** Function that checks whether any cards
         *  are left in the deck
         *
         * @return  true if there are cards left
         *          false otherwise
         */
        @Override
        public boolean hasNext() {
            return (cards.size() != index);
        }

        /**
         *
         * @return
         */
        @Override
        public Card next() {
            Card acard = cards.get(index);
            // get last even, set index to cards.size
            // so hasnext will be false and return
            if(index == cards.size()-1){
                index = cards.size();
                return cards.get(cards.size()-1);
            }
            index+=2;
            // if last card is reached
            // go back to beginning starting from 1
            // to get evens
            if(cards.size() == index) {
                index = 1;
                flag = true;
            }
            return acard;
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
    public Iterator<Card> oddeven() {
        return new OddEvenIterator();
    }
    private void writeObject(ObjectOutputStream out) throws IOException{
        Iterator<Card> it = oddeven();
        ArrayList<Card> saved = new ArrayList<>();
        while(it.hasNext()){
            Card acard = it.next();
            saved.add(acard);
        }
        out.writeObject(saved);
    }

}
