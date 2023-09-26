package splitwise.models;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String userId;
    private String name;

    private String emailId;

    private Map<String, Double> balance;

    // user -> money
    // user  -> pair<money, currency>
    private Map<User, Map<String,Double>> userBalanceMap;

    public User(String name, String emailId) {
        this.name = name;
        this.emailId = emailId;
        this.userBalanceMap = new HashMap<>();
        this.balance = new HashMap<>();
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

    public Map<String, Double> getBalance() {
        return balance;
    }

    public void setBalance(Map<String, Double> balance) {
        this.balance = balance;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Map<User, Map<String, Double>> getUserBalanceMap() {
        return userBalanceMap;
    }

    public void setUserBalanceMap(Map<User, Map<String, Double>> userBalanceMap) {
        this.userBalanceMap = userBalanceMap;
    }
}
