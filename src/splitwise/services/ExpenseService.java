package splitwise.services;

import splitwise.models.Expense;

import java.util.List;

public interface ExpenseService {
    public Expense addExpense(Expense expense);

    public List<Expense> getExpenses();

    public Expense getExpense(String expenseId);

    public Expense updateExpense(Expense expense);

    public void deleteExpense(String expenseId);

}
