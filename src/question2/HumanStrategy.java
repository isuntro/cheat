package question2;

import question1.Card;
import question1.Hand;
import java.util.Scanner;

/**
 * Created by tiberiusimionvoicu on 28/01/2017.
 */
public class HumanStrategy implements Strategy {
    private static Scanner scan = new Scanner(System.in);

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
        Boolean cheat = false;
        System.out.println("Your current hand is : \n" + h);
        System.out.println("Last players bid is : \n" + b);
        System.out.println("Would you like to cheat ? If you dont have any " +
                "cards to play you will have to cheat " +
                "(Type yes or no)");
        cheat = yesno(h,b);
        if ( h.countRank(b.getRank()) == 0
                && h.countRank(b.getRank().getNext()) == 0){
            cheat = true;
        }
        if(cheat)
            System.out.println("Cheating!!!");
        return cheat;
    }

    /**
     * @param b     the bid the player has to follow
     * @param h     The players current hand
     * @param cheat true if the Strategy has decided to cheat (by call to cheat())
     * @return a Bid with the cards to pass to the game and the Rank. This will be
     * different to the rank of thecards if the player is cheating!
     */
    @Override
    public Bid chooseBid(Bid b, Hand h, boolean cheat) {
        int choice = 0;
        int chcount = 0;
        Bid playerBid = new Bid();
        Hand playing = new Hand();
        do {
            System.out.println("Your hand is : \n"+h);
            System.out.println("Type 1 - " + h.getCards().size() + " to chose a card");
            choice = scan.nextInt();


            /*  Choice is valid only if its a positive number
                less than number of cards in hand
                and rank is either the same with
                last players bid or next rank
             */
            if (choice > 0 && choice < h.getCards().size() && chcount < 5) {

                //If not cheating rank is either the same rank with
                //last players bid or next rank
                if (!cheat && (h.getCards().get(choice).getRank() == b.getRank()
                        || h.getCards().get(choice).getRank() == b.getRank().getNext())) {
                    Card acard = h.remove(choice - 1);
                    playing.add(acard);
                    chcount++;
                    // else choose whatever cards(cheating)
                } else {
                    Card acard = h.remove(choice - 1);
                    playing.add(acard);
                    chcount++;
                }

                System.out.println("If you're done picking cards type -1 ");
                if (playing.getCards().get(0).getRank() == b.getRank().getNext())
                    playerBid.setRank(b.getRank().getNext());
                else playerBid.setRank(b.getRank());
            }
        }
        while (choice != -1 && chcount < 4 && h.getCards().size() != 0);
        playerBid.setHand(playing);
        if(cheat) {
            System.out.println("You have chosen your play, now " +
                    "chose what rank u will state( 1 for last bids rank" +
                    ", 2 for next bids rank)");
            do {
                choice = scan.nextInt();
                if (choice == 1) {
                    playerBid.setRank(b.getRank());
                } else if (choice == 2) {
                    playerBid.setRank((b.getRank().getNext()));
                }
                System.out.println(" If you're done picking the rank type -1");
            }
            while (choice != -1);
        }
        return playerBid;

    }

    /**
     * @param h
     * @param b the current bid
     * @return true if this player is going to call cheat  on the last play b
     */
    @Override
    public boolean callCheat(Hand h, Bid b) {
        System.out.println("Your current hand is : \n" + h);
        System.out.println("Last players bid is : \n" + b);
        System.out.println("Would you like to call cheat ? (Type yes or no)");
        return yesno(h,b);
    }

    private static boolean yesno(Hand h, Bid b) {
        String answer = scan.next().toLowerCase();
        while (!(answer.equals("yes") || answer.equals("no"))) {
            System.out.println(" Type yes or no ");
            answer = scan.next().toLowerCase();
        }
        if (answer.equals("yes")) {
            return true;
        }
        return false;
    }

}