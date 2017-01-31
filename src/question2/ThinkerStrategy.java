package question2;

import question1.Hand;
import java.util.Random;
import question1.*;

/**
 * Created by tiberiusimionvoicu on 30/01/2017.2
 */
public class ThinkerStrategy implements Strategy {
    private Hand discards;

    @Override
    public boolean cheat(Bid b, Hand h) {
        Random rad = new Random();

        int cheatChance = rad.nextInt(20);
        // if this player has no cards of given
        // or next rank in bid then cheat
        if ( h.countRank(b.getRank()) == 0
                && h.countRank(b.getRank().getNext()) == 0
                || cheatChance < 5){
            return true;
        }
        return false;
    }

    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {

        if(cheat){
            Card picked = null;
            Random rad = new Random();
            int index = h.size();
            do {
                for (Card acard : h.getCards()) {
                    int chance = rad.nextInt(index+10);
                    if (acard.getRank().ordinal() >= 7 && chance > 13) {
                        picked = acard;
                    }
                    else if(acard.getRank().ordinal() < 7 && chance < 4){
                        picked = acard;
                    }
                }
            }
            while(picked == null);
        }

        return null;
    }

    @Override
    public boolean callCheat(Hand h, Bid b) {
        return false;
    }
}
