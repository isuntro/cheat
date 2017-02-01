package question2;

import question1.Hand;
import java.util.Random;
import question1.*;
/**
 * Created by tiberiusimionvoicu on 30/01/2017.
 */
public class MyStrategy implements Strategy {
    // if cheat false increase percentage to cheat
    // if cheat noCheats back to 0.5
    private double pnoCheats = 0.5;
    private double pnoCalls = 0.35;
    private Hand discards = new Hand();
    @Override
    public boolean cheat(Bid b, Hand h) {
        double cheatChance = Math.random();
        double p =0.18*pnoCheats;
        // if first play of new round
        // higher chance to cheat
        if (b.getCount() == 0){
            p = 0.40*pnoCheats;
        }
        // also higher chance to cheat if
        // rank is high
        else if(b.getRank().ordinal() > 7){
            p = 0.30*pnoCheats;
        }
        // if this player has no cards of given
        // or next rank in bid then cheat
        if ( h.countRank(b.getRank()) == 0
                && h.countRank(b.getRank().getNext()) == 0
                || cheatChance < p){
            pnoCheats = 0.5;
            return true;
        }
        pnoCheats += 0.1;
        return false;
    }
    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        double p = 0.1;
        double chance = Math.random();
        Hand handBid = new Hand();
        Card.Rank rankBid;
        if(cheat){
            System.out.println(" CHEATING !!!");
            Random rad = new Random();
            int noPlay = 1;
            if(b.getCount() == 0){
                p=0.25;
            }
            // if first bid of round more likely to cheat with more cards
            // if not less likely
            if(chance < p){
                // number of cards to play 1-4
                noPlay = rad.nextInt(h.size())+1;
            }
            while(handBid.size() < noPlay && handBid.size() < 4) {
                chance = rad.nextInt(h.size());
                Card acard = h.getCards().get((int)chance);
                chance = Math.random();
                if (chance > 0.4 && !acard.getRank().equals(b.getRank())
                        && !acard.getRank().equals(b.getRank().getNext())) {
                    handBid.add(acard);
                }
            }
            // roughly 50/50 chance of rank of Bid or next Rank
            rankBid = b.getRank();
            h.remove(handBid);
            if(chance >= 0.5){
                rankBid = b.getRank().getNext();
            }
            discards.add(handBid);
            return new Bid(handBid,rankBid);
        }
        else {
            System.out.println(" NOT CHEATING !!!");
            rankBid = b.getRank();
            if(h.countRank(rankBid) == 0){
                rankBid = rankBid.getNext();
            }
            for(Card acard : h.getCards()){
                if(acard.getRank().equals(rankBid)){
                    handBid.add(acard);
                }
            }
            h.remove(handBid);
            discards.add(handBid);
            return new Bid(handBid,rankBid);
        }
    }

    @Override
    public boolean callCheat(Hand h, Bid b) {
        int nrRanks = h.countRank(b.getRank())
                + b.getCount() + discards.countRank(b.getRank());
        double p;
        double chance = Math.random();
        if (discards.countRank(b.getRank()) == 4) {
            p = 0.40 * pnoCalls;
        }
        else if(discards.countRank(b.getRank()) == 3) {
            p = 0.25 * pnoCalls;
        }
        else if(discards.countRank(b.getRank()) == 2) {
            p = 0.20 * pnoCalls;
        }else p = 0.15 * pnoCalls;
        // small probability it wont call cheat
        // to try and avoid a deadlock
        if ( chance < 0.05 * pnoCalls) {
            pnoCalls = 0.35;
            return false;
        }
        if(nrRanks > 4 || chance < p) {
            pnoCalls += 0.5;
            return true;
        }
        pnoCalls = 0.35;
        return false;
    }
}
