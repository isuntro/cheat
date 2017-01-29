package question2;

import question1.Card;
import question1.Hand;

/**
 * Created by tiberiusimionvoicu on 27/01/2017.
 */
public class BasicPlayer implements Player {
    private Hand pHand = new Hand();
    private Strategy pStrategy;
    private CardGame game;

    public BasicPlayer(Strategy astrategy, CardGame agame) {
        this.pStrategy = astrategy;
        this.game = agame;
    }

    /**
     * add to the players hand
     *
     * @param c : Card to add
     */
    @Override
    public void addCard(Card c) {
        this.pHand.add(c);
    }

    /**
     * add all the cards in h to the players hand
     *
     * @param h : hand to add
     */
    @Override
    public void addHand(Hand h) {
        this.pHand.add(h);
    }

    /**
     * @return number of cards left in the players hand
     */
    @Override
    public int cardsLeft() {
        return this.pHand.getCards().size();
    }

    /**
     * @param g : the player should contain a reference to the game it is playing in
     */
    @Override
    public void setGame(CardGame g) {
        this.game = g;
    }

    /**
     * @param s : the player should contain a reference to its strategy
     */
    @Override
    public void setStrategy(Strategy s) {
        this.pStrategy = s;
    }

    /**
     * Constructs a bid when asked to by the game.
     *
     * @param b : the last bid accepted by the game. .
     * @return the players bid
     */
    @Override
    public Bid playHand(Bid b) {
        Boolean cheat = this.pStrategy.cheat(b,this.pHand);
        return this.pStrategy.chooseBid(b,this.pHand,cheat);
    }

    /**
     * @param b : the last players bid
     * @return true if calling the last player a cheat.
     */
    @Override
    public boolean callCheat(Bid b) {
        return this.pStrategy.callCheat(this.pHand,b);
    }
}
