package question2;

import question1.Hand;
import java.util.Random;
/**
 * Created by tiberiusimionvoicu on 30/01/2017.
 */
public class MyStrategy implements Strategy {
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
        return null;
    }

    @Override
    public boolean callCheat(Hand h, Bid b) {
        return false;
    }
}
