package splitwise.strategy;

import splitwise.models.Expense;
import splitwise.models.User;

import java.util.HashMap;
import java.util.Map;

public class EqualSplitStrategy implements DefaultSplitStrategy{
    @Override
    public void splitMoney(Expense expense) {
        int totalUser = expense.getUsersList().size();
        Map<User,Double> shareMap = new HashMap<>();
        for(int i = 0; i < totalUser; i++) {
            shareMap.put(expense.getUsersList().get(i), expense.getAmount()/totalUser);
        }
        expense.setShareMap(shareMap);
    }
}
