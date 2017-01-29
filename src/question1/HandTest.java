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
        Card [] cards = new Card[4];
        cards[0] = (new Card(Card.Rank.ACE,Card.Suit.CLUB));
        cards[1] = (new Card(Card.Rank.ACE,Card.Suit.CLUB));
        cards[2] = (new Card(Card.Rank.ACE,Card.Suit.CLUB));
        cards[3] = (new Card(Card.Rank.ACE,Card.Suit.CLUB));
        Hand ahand = new Hand(cards);
        System.out.println(ahand.isStraight());
        System.out.println(ahand.getHistogram().toString());
        for (int i=0;i < ahand.getHistogram().length;i++) {
                System.out.println(ahand.getHistogram()[i]);
        }
        System.out.println(ahand.gethValue());
        System.out.println(ahand);
        System.out.println(ahand.isStraight());

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