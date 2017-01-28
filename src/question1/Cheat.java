/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question1;
import java.util.*;


/**
 *
 * @author tiberiusimionvoicu
 */
public class Cheat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Card a = new Card(Card.Rank.FIVE,Card.Suit.CLUB);
        Card b = new Card(Card.Rank.TWO, Card.Suit.DIAMOND);

        System.out.println("Difference in order :" + Card.difference(a,b));
        System.out.println("Difference in value :" + Card.differenceValue(a,b));
        ArrayList<Card> cards = new ArrayList();
        Deck adeck = new Deck();
        //System.out.println(adeck.toString());
        adeck.shuffle();
        //System.out.println(adeck.toString());
        //System.out.println(adeck.deal());
        //adeck.newDeck();
        System.out.println(adeck);
        Iterator<Card> i = adeck.iterator();
        while(i.hasNext()){
            Card acard = i.next();
            System.out.println(acard);
        }
        
    }
    
}
