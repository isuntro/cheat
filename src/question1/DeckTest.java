package question1;

import org.junit.jupiter.api.Test;

import java.io.*;
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
        Iterator<Card> oddeven = adeck.oddeven();
        while(oddeven.hasNext()){
            System.out.print(oddeven.next());
        }

    }

    @Test
    void shuffle() {

    }

    @Test
    void size() throws IOException, ClassNotFoundException {
        Deck cards = new Deck();
        cards.shuffle();
        try {
            FileOutputStream fis = new FileOutputStream("/Users/tiberiusimionvoicu/NetBeansProjects/cheat/src/question2/deck.ser");
            ObjectOutputStream out = new ObjectOutputStream(fis);
            out.writeObject(cards);
            out.flush();
            System.out.println("Done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream("/Users/tiberiusimionvoicu/NetBeansProjects/cheat/src/question2/deck.ser"));
        Deck savedeck = (Deck)oin.readObject();
        System.out.println(cards);
        System.out.println("Loaded object" + savedeck);

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