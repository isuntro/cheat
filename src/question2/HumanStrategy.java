package question2;

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
        System.out.println("Your current hand is : \n" + h);
        System.out.println("Last players bid is : \n" + b);
        System.out.println("Would you like to cheat ? (Type yes or no)");
        return yesno(h,b);
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
        if(cheat){
            

        }
        return null;
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
        String answer = scan.next();
        while (!answer.equals("yes") || !answer.equals("no")) {
            System.out.println(" Type yes or no ");
            answer = scan.next();
        }
        if (answer.equals("yes")) {
            return true;
        }
        return false;
    }

}