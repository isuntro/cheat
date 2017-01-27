package question1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by tiberiusimionvoicu on 26/01/2017.
 */
class DeckTest {
    @Test
    void test() {
        ArrayList<Card> cards = new ArrayList<>();
        Deck adeck = new Deck();
        System.out.println(adeck);
        Iterator<Card> oddeven = new Deck.OddEvenIterator(cards);
        while(oddeven.hasNext()){
            System.out.print(oddeven.next());
        }
    }
    @Test
    void shuffle() {

    }

    @Test
    void size() {

    }

    @Test
    void newDeck() {

    }

    @Test
    void deal() {

    }

    @Test
    void iterator() {

    }

}