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
    void test() {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card.Rank arank : Card.Rank.values()) {
            for (Card.Suit asuit : Card.Suit.values()) {
                Card acard = new Card(arank, asuit);
                cards.add(acard);
            }
        }

        Collections.shuffle(cards);
        System.out.println("Printing shuffled cards: \n" + cards);
        Card.CompareDescending compdesc = new Card.CompareDescending();
        Card.CompareSuit compsuit = new Card.CompareSuit();
        cards.sort(compdesc);
        System.out.println("Printing cards: sorted descending \n" + cards);
        cards.sort(compsuit);
        System.out.println("Printing cards: sorted by suit \n" + cards);
        Collections.sort(cards);
        System.out.println("Printing cards: sorted ascending \n" + cards);

        Card acard = new Card(Card.Rank.TWO,Card.Suit.DIAMOND);
        Card bcard = new Card(Card.Rank.ACE,Card.Suit.DIAMOND);
        System.out.println("First Card is : " + acard);
        System.out.println("Second Card is : " + bcard);
        System.out.println("Testing difference: \n Difference is :" + Card.difference(acard,bcard));
        System.out.println("Testing difference in value: \n" +
                " Difference in value is :" + Card.differenceValue(acard,bcard));
    }
}