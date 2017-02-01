package question2;

import question1.Hand;
import java.util.Random;
import question1.*;

/**
 * Created by tiberiusimionvoicu on 30/01/2017.2
 */
public class ThinkerStrategy implements Strategy {
    // Hand containing all the cards
    // that this player has seen
    // it's faulty as the longer the game progresses
    // it believes it knows more cards than it actually should
    // as they can't be taken out of discards when a player picks them
    private Hand discards = new Hand();


    @Override
    public boolean cheat(Bid b, Hand h) {
        double cheatChance = Math.random();
        // if this player has no cards of given
        // or next rank in bid then cheat
        if ( h.countRank(b.getRank()) == 0
                && h.countRank(b.getRank().getNext()) == 0
                || cheatChance < 0.18){
            return true;
        }
        return false;
    }

    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Hand handBid = new Hand();
        Card.Rank rankBid;
        Random rad = new Random();
        double chance;
        if(cheat){
            int index = h.size();
            // do pick a card while hand is empty
            do {
                // iterate through hand cards picking a random card
                // with higher probability for higher ranked cards
                    chance = rad.nextInt(index);
                    Card acard = h.getCards().get((int)chance);
                    chance = Math.random();
                    if (acard.getRank().ordinal() >= 5 && chance > 0.6
                            && !acard.getRank().equals(b.getRank())
                            && !acard.getRank().equals(b.getRank().getNext())) {
                        handBid.add(acard);
                    }
                    else if(acard.getRank().ordinal() < 5 && chance < 0.2
                            && !acard.getRank().equals(b.getRank())
                            && !acard.getRank().equals(b.getRank().getNext())){
                        handBid.add(acard);
                    }
            }
            while(handBid.size() == 0);
            chance = rad.nextInt(10);
            rankBid = b.getRank();
            h.remove(handBid);
            // roughly 50-50 to putting bid on current rank or next
            if(chance >= 5)
                rankBid = b.getRank().getNext();
            discards.add(handBid);
            System.out.println("CHEATING !!!!!!!");
            return new Bid (handBid, rankBid);
        }
        rankBid = b.getRank();

        if (h.countRank(b.getRank()) == 0) {
            rankBid = b.getRank().getNext();
        }
        int playNo=h.countRank(rankBid);  // playNo = max number of cards
        chance = rad.nextInt(10); // chance of not playing all cards
        if(chance < 2 ){
            playNo = rad.nextInt(h.countRank(rankBid))+1; // playNo = random number between 1 and max
        }
        int count = 0;
        // check logic
        for (Card acard : h.getCards()) {
            if (acard.getRank().equals(rankBid)) {
                handBid.add(acard);
                count++;
            }
            if(count> playNo){
                break;
            }
        }
        h.remove(handBid);
        discards.add(handBid);
        System.out.println("NOT CHEATING !!!!");
        return new Bid (handBid,rankBid);
    }

    @Override
    public boolean callCheat(Hand h, Bid b) {
        // nrRank = number of known cards of given bid rank
        int nrRank = h.countRank(b.getRank())+b.getCount()
                +discards.countRank(b.getRank());
        double p;
        double chance = Math.random();
        if (discards.countRank(b.getRank()) == 4) {
            p = 0.40;
        }
        else if(discards.countRank(b.getRank()) == 3) {
            p = 0.30;
        }
        else if(discards.countRank(b.getRank()) == 2) {
            p = 0.20;
        }else p = 0.15;
        // small probability it wont call cheat
        // to try and avoid a deadlock
        if ( chance < 0.05) {
            return false;
        }
        if(nrRank > 4 || chance < p)
            return true;
        else
        return false;
    }
}
