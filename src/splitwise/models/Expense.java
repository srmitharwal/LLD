package splitwise.models;

import java.util.List;
import java.util.Map;

public class Expense {

    private String expenseId;

    private String expenseName;

    private double amount;

    private ExpenseType expenseType;

    private String splitType;

    private List<User> usersList;

    private User creditor;

    private String groupId;

    private Map<User, Double> splitMap;

    private Map<User, Double> shareMap;


    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public String getSplitType() {
        return splitType;
    }

    public void setSplitType(String splitType) {
        this.splitType = splitType;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public User getCreditor() {
        return creditor;
    }

    public void setCreditor(User creditor) {
        this.creditor = creditor;
    }

    public Map<User, Double> getSplitMap() {
        return splitMap;
    }

    public void setSplitMap(Map<User, Double> splitMap) {
        this.splitMap = splitMap;
    }

    public Map<User, Double> getShareMap() {
        return shareMap;
    }

    public void setShareMap(Map<User, Double> shareMap) {
        this.shareMap = shareMap;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}


//id, name, amount, list<users>user, debitor,