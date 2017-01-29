package question2;

import question1.*;
import java.util.Random;

/**
 * Created by tiberiusimionvoicu on 27/01/2017.
 */
public class BasicStrategy implements Strategy {
    /**
     * Decides on whether to cheat or not
     *
     * @param b the bid this player has to follow (i.e the
     *          bid prior to this players turn.
     * @param h The players current hand
     * @return true if the player will cheat, false if not
     */
    @Override
    public boolean cheat(Bid b, Hand h) {
        // if this player has no cards of given
        // or next rank in bid then cheat
        if ( h.countRank(b.getRank()) == 0
                && h.countRank(b.getRank().getNext()) == 0){
            return true;
        }
        return false;
    }

    /**
     * @param b     the bid the player has to follow.
     * @param h     The players current hand
     * @param cheat true if the Strategy has decided to cheat (by call to cheat())
     * @return a Bid with the cards to pass to the game and the Rank. This will be
     * different to the rank of thecards if the player is cheating!
     */
    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        Bid playerBid = new Bid();
        Hand handBid = new Hand();
        if(cheat) {
            Random rad = new Random();
            Card acard = h.remove(rad.nextInt(h.getCards().size()));
            handBid.add(acard);
            playerBid.setHand(handBid);
            playerBid.setRank(b.getRank());
            return playerBid;
        }
        Card.Rank rankBid = b.getRank();
        if(h.countRank(b.getRank()) < h.countRank(b.getRank().getNext()))
            rankBid = b.getRank().getNext();
        for( Card acard : h.getCards()) {
            if( acard.getRank() == rankBid) {
                handBid.add(acard);
            }
        }
        h.remove(handBid);
        playerBid.setHand(handBid);
        playerBid.setRank(rankBid);

        return playerBid;
    }

    /**
     * @param h
     * @param b the current bid
     * @return true if this player is going to call cheat  on the last play b
     */
    @Override
    public boolean callCheat(Hand h, Bid b) {
        // if this player holds 4 cards of last bids rank
        // then return true
        if (h.countRank(b.getRank())+b.getCount() > 4)
            return true;
        return false;
    }
}
