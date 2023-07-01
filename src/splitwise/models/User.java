package splitwise.models;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String userId;
    private String name;

    private String emailId;

    private double balance;

    private Map<User, Double> userBalanceMap;

    public User(String name, String emailId) {
        this.name = name;
        this.emailId = emailId;
        this.userBalanceMap = new HashMap<>();
        this.balance = 0;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Map<User, Double> getUserBalanceMap() {
        return userBalanceMap;
    }

    public void setUserBalanceMap(Map<User, Double> userBalanceMap) {
        this.userBalanceMap = userBalanceMap;
    }
}
