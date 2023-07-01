package splitwise.services;

import splitwise.Utils.Util;
import splitwise.models.Expense;
import splitwise.models.Group;
import splitwise.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseServiceImpl implements ExpenseService {

    static Map<String, Expense> expenseMap = null;
    GroupService groupService;
    UserService userService;

    public ExpenseServiceImpl( GroupService groupService, UserService userService) {

        if (expenseMap == null) {
            synchronized (ExpenseServiceImpl.class) {
                if (expenseMap == null) {
                    expenseMap = new HashMap<>();
                }
            }
        }

        this.userService = userService;
        this.groupService = groupService;
    }

    @Override
    public Expense addExpense(Expense expense) {
        String expenseId = Util.uuidGenerator();
        expense.setExpenseId(expenseId);

        expenseMap.put(expenseId, expense);

        // createShareMap with splitMap;

        if (expense.getExpenseType().toString().equalsIgnoreCase("Group")) {
            Group group = groupService.getGroup(expense.getGroupId());
            addBalanceToGroup(group, expense);
            groupService.updateGroup(group);
        } else {
            addBalanceToUser(expense);

            userService.updateUser(expense.getCreditor());
            userService.updateUser(expense.getUsersList().get(0));
        }
        return expense;
    }

    private void addBalanceToUser(Expense expense) {
        User creditor = expense.getCreditor();
        User debitor = expense.getUsersList().get(0);

        Double creditorBalance = creditor.getBalance();
        Double debitroBalance  = debitor.getBalance();

        Map<User,Double> shareMap = expense.getShareMap();

        creditorBalance -= shareMap.getOrDefault(creditor, 0.0);
        debitroBalance -= shareMap.getOrDefault(debitor, 0.0);

        creditorBalance += expense.getAmount();

        creditor.setBalance(creditorBalance);
        double balance = creditor.getUserBalanceMap().getOrDefault(debitor,0.0);
        balance += shareMap.getOrDefault(debitor, 0.0);;
        creditor.getUserBalanceMap().put(debitor, balance);


        debitor.setBalance(debitroBalance);
        balance = debitor.getUserBalanceMap().getOrDefault(creditor,0.0);
        balance += shareMap.getOrDefault(debitor, 0.0)*-1;

        debitor.getUserBalanceMap().put(creditor, balance);

    }

    private void addBalanceToGroup(Group group, Expense expense) {
        List<User> userList = group.getUserList();
        Map<User, Double> shareMap = expense.getShareMap();

        for (User user : userList) {
            if(shareMap.containsKey(user)) {
                Double currentBalance = group.getUserBalanceMap().getOrDefault(user.getUserId(), 0.0);
                currentBalance -= shareMap.get(user);
                group.getUserBalanceMap().put(user.getUserId(), currentBalance);
            }
        }

        User creditor  = expense.getCreditor();
        Double creditorBalance = group.getUserBalanceMap().getOrDefault(creditor.getUserId(), 0.0);
        creditorBalance += expense.getAmount();
        group.getUserBalanceMap().put(creditor.getUserId(), creditorBalance);

    }

    @Override
    public List<Expense> getExpenses() {
        return null;
    }

    @Override
    public Expense getExpense(String expenseId) {
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public void deleteExpense(String expenseId) {
        Expense expense = expenseMap.get(expenseId);

        if (expense.getExpenseType().toString().equalsIgnoreCase("Group")) {
            Group group = groupService.getGroup(expense.getGroupId());
            deleteExpenseForGroup(expense, group);
        } else {
            deleteExpenseForUser(expense);
        }
    }

    private void deleteExpenseForUser(Expense expense) {
        User creditor = expense.getCreditor();
        User debitor = expense.getUsersList().get(0);

        Double creditorBalance = creditor.getBalance();
        Double debitroBalance  = debitor.getBalance();

        Map<User,Double> shareMap = expense.getShareMap();

        creditorBalance += shareMap.getOrDefault(creditor, 0.0);
        debitroBalance += shareMap.getOrDefault(debitor, 0.0);

        creditorBalance -= expense.getAmount();

        creditor.setBalance(creditorBalance);
        double balance = creditor.getUserBalanceMap().getOrDefault(debitor,0.0);
        balance -= shareMap.getOrDefault(debitor, 0.0);
       if(balance != 0)  creditor.getUserBalanceMap().put(debitor, balance);
       else creditor.getUserBalanceMap().remove(debitor);


        debitor.setBalance(debitroBalance);
        balance = debitor.getUserBalanceMap().getOrDefault(creditor,0.0);
        balance -= shareMap.getOrDefault(debitor, 0.0)*-1;

        if(balance != 0)  debitor.getUserBalanceMap().put(creditor, balance);
        else debitor.getUserBalanceMap().remove(creditor);
    }

    private void deleteExpenseForGroup(Expense expense, Group group) {

        List<User> userList = group.getUserList();
        Map<User, Double> shareMap = expense.getShareMap();

        for (User user : userList) {
            if(shareMap.containsKey(user)) {
                Double currentBalance = group.getUserBalanceMap().getOrDefault(user.getUserId(), 0.0);
                currentBalance += shareMap.get(user);
                group.getUserBalanceMap().put(user.getUserId(), currentBalance);
            }
        }

        User creditor  = expense.getCreditor();
        Double creditorBalance = group.getUserBalanceMap().getOrDefault(creditor.getUserId(), 0.0);
        creditorBalance -= expense.getAmount();
        group.getUserBalanceMap().put(creditor.getUserId(), creditorBalance);
    }
}
