package splitwise.strategy;

import splitwise.models.Expense;

public interface DefaultSplitStrategy {
    public void splitMoney(Expense expense);
}
