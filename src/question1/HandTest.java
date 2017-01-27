package question1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
/**
 * Created by tiberiusimionvoicu on 26/01/2017.
 */
class HandTest {
    @Test
    void test() {
        Card [] cards = new Card[5];
        cards[0] = (new Card(Card.Rank.TWO,Card.Suit.SPADE));
        cards[1] = (new Card(Card.Rank.QUEEN,Card.Suit.SPADE));
        cards[2] = (new Card(Card.Rank.KING,Card.Suit.SPADE));
        cards[3] = (new Card(Card.Rank.JACK,Card.Suit.SPADE));
        cards[4] = (new Card(Card.Rank.ACE,Card.Suit.SPADE));
        Hand ahand = new Hand(cards);
        System.out.println(ahand.isStraight());
        System.out.println(ahand.histogram.toString());
        for (int i=0;i < ahand.histogram.length;i++) {
            for(int j=0; j < 4; j++) {
                System.out.print(ahand.histogram[i][j]);
            }
            System.out.println();

        }
        System.out.println(ahand.hValue);
        System.out.println(ahand);

    }
    @Test
    void addCard() {

    }

    @Test
    void addCollCards() {

    }

    @Test
    void addHand() {

    }

    @Test
    void removeCard() {

    }

    @Test
    void removeHand() {

    }

    @Test
    void removeCindex() {

    }

    @Test
    void iterator() {

    }

}