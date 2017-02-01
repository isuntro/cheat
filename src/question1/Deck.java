package question1;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**~
 * Created by tiberiusimionvoicu on 19/01/2017.
 */
public class Deck implements Serializable,Iterable<Card> {

    private static final long serialVersionUID = 101;
    private ArrayList<Card> cards;
    /** Constructor initializes this deck object
     *  with a full deck of 52 cards with no duplicates
     *
     */
    public Deck(){
        cards = new ArrayList<>();
        for (Card.Rank arank : Card.Rank.values()) {
            for (Card.Suit asuit : Card.Suit.values()) {
                Card acard = new Card(arank, asuit);
                cards.add(acard);
            }
        }
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

    /** private inner class used to iterate
     * through deck object first cards in
     * odd positions and then even ones
     *
     */
    private class OddEvenIterator implements Iterator<Card> {
        private int index = 0;

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

        /** Next gives first odd then even
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
            }
            return acard;
        }
    }

    /** Creates and returns a default iterator
     *  iterating over deck in order from 0 - last
     *
     * @return default iterator
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

    /** Method simply returns an
     *  oddeven iterator
     *
     * @return iterator OddevenIterator
     */
    public Iterator<Card> oddeven() {
        return new OddEvenIterator();
    }

    /** Method overriden by the SDK automatically
     *  defines how the object will be serialized
     *
     * @param out           - Object output stream
     * @throws IOException  - file output issues
     */
    private void writeObject(ObjectOutputStream out) throws IOException{
        Iterator<Card> it = oddeven();
        ArrayList<Card> saved = new ArrayList<>();
        while(it.hasNext()){
            Card acard = it.next();
            saved.add(acard);
        }
        out.writeObject(saved);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Deck deck = new Deck();
        deck.shuffle();
        System.out.println(deck);
        /*
        String path ="";
        try {
            FileOutputStream fis = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fis);
            out.writeObject(deck);
            out.flush();
            System.out.println("Done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(path));
        Deck savedeck = (Deck)oin.readObject();
        System.out.println(deck);
        System.out.println("Loaded object \n" + savedeck);
        */

    }

}
