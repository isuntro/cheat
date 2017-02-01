package question2;

/**
 * Created by tiberiusimionvoicu on 31/01/2017.
 */
public class StrategyFactory {
    public static Strategy getStrategy(String choice){
        switch (choice.toLowerCase()){
            case "basic":
            case "basicstrategy":{
                return new BasicStrategy();
            }
            case "human":
            case "humanstrategy":{
                return new HumanStrategy();
            }
            case "thinker":
            case "thinkerstrategy":{
                return new ThinkerStrategy();
            }
            case "my":
            case "mystrategy":{
                return new MyStrategy();
            }
            default: return null;// default could return basic as well
        }
    }
}
