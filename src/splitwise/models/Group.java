package splitwise.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {

    public Group(String name, List<User> userList) {
        this.groupName = name;
        this.userList = userList;
        this.balanceMap = new HashMap<>();
        this.userBalanceMap = new HashMap<>();
    }

    private String groupId;

    private String groupName;

    private List<User> userList;



    private Map<String, List<UserBalanceInfo>> balanceMap;

    private Map<String, Double> userBalanceMap;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


    public Map<String, Double> getUserBalanceMap() {
        return userBalanceMap;
    }

    public void setUserBalanceMap(Map<String, Double> userBalanceMap) {
        this.userBalanceMap = userBalanceMap;
    }

    public Map<String, List<UserBalanceInfo>> getBalanceMap() {
        return balanceMap;
    }

    public void setBalanceMap(Map<String, List<UserBalanceInfo>> balanceMap) {
        this.balanceMap = balanceMap;
    }
}
