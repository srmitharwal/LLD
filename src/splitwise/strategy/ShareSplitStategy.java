package splitwise.strategy;

import splitwise.models.Expense;
import splitwise.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShareSplitStategy implements DefaultSplitStrategy{
    @Override
    public void splitMoney(Expense expense) {
        Map<User,Double> splitMap = expense.getSplitMap();
        double total = 0.0;

        List<Double>split = splitMap.values().stream().toList();
        for(int i  = 0; i < split.size(); i++) {
            total += split.get(i);
        }

        Map<User, Double> shareMap = new HashMap<>();

        for(int i = 0; i < expense.getUsersList().size(); i++) {
            double amount = (expense.getAmount()*splitMap.get(expense.getUsersList().get(i)))/total;
            shareMap.put(expense.getUsersList().get(i), amount);
        }

        expense.setShareMap(shareMap);
    }
}
