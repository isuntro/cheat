package question1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

/**
 * Created by tiberiusimionvoicu on 24/01/2017.
 */
class CardTest {
    @Test
    void test(){
        ArrayList<Card> cards = new ArrayList<>();
        for(Card.Rank arank : Card.Rank.values()) {
            for(Card.Suit asuit : Card.Suit.values()) {
                Card acard = new Card(arank,asuit);
                cards.add(acard);
            }
        }
        Collections.shuffle(cards);
        Card.CompareDescending compdesc = new Card.CompareDescending();
        Card.CompareSuit compsuit = new Card.CompareSuit();
        //cards.sort(compdesc);
        cards.sort(compsuit);
        //Collections.sort(cards);
        System.out.println(cards.toString());


    }
    @Test
    void getSuit() {

    }
    @Test
    void getRank() {

    }

    @Test
    void setSuit() {

    }

    @Test
    void setRank() {

    }

    @Test
    void difference() {

    }

    @Test
    void differenceValue() {

    }

    @Test
    public String toString() {

        return null;
    }

    @Test
    void compareTo() {

    }

}