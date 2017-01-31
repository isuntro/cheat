package question2;

//import solution_part2.*;
import question1.*;

public class Bid {
    Hand h;
    Card.Rank r;
    public Bid(){
        h=new Hand();
        // changed from ace to two
        // as stated in specification
        // otherwise after someone calls cheat
        // new bid will be ACE
        r=Card.Rank.TWO;
    }
    public Bid(Hand hand,Card.Rank bidRank){
        h=hand;
        r=bidRank;
    }
    public void setHand(Hand hand){ h=hand;}
    public void setRank(Card.Rank rank){ r=rank;}
		
    public Hand getHand(){ return h;}
    public int getCount(){ return h.getCards().size();}
    public Card.Rank getRank(){ return r;}
    public String toString(){
			return h.getCards().size()+" x "+r;
		}
		
}	
