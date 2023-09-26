package splitwise.strategy;

public class SplitFactory {
//signleton factory
    // map <String, DefaultSplitStrategy>

    public DefaultSplitStrategy getSplitStrategy(String splitType) {

        if(splitType.equalsIgnoreCase("exact")) {
            return new ExactSplitStrategy();

        } else if(splitType.equalsIgnoreCase("equal")) {
            return new EqualSplitStrategy();
        } else if(splitType.equalsIgnoreCase("share")) {
            return new ShareSplitStategy();
        }
        return new EqualSplitStrategy();
    }
}
