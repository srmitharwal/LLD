package splitwise.services;

import splitwise.Utils.Util;
import splitwise.models.Group;
import splitwise.strategy.DefaultPaymentGraphStrategy;
import splitwise.strategy.PaymentGraphStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupServiceImpl implements GroupService {

    static Map<String, Group> groupMap = null;

    private PaymentGraphStrategy paymentGraphStrategy;

    public GroupServiceImpl() {
        if(groupMap == null) {
            synchronized (GroupServiceImpl.class) {
                if(groupMap == null ){
                    groupMap = new HashMap<>();
                }
            }
        }

        paymentGraphStrategy = new DefaultPaymentGraphStrategy();
    }

    @Override
    public Group createGroup(Group group) {
        String groupId = Util.uuidGenerator();
        group.setGroupId(groupId);

        groupMap.put(groupId, group);

        return group;
    }

    @Override
    public List<Group> getGroups() {
        return null;
    }

    @Override
    public Group getGroup(String groupId) {
        return groupMap.get(groupId);
    }

    @Override
    public Group updateGroup(Group group) {
        groupMap.put(group.getGroupId(), group);

        // update User info
        paymentGraphStrategy.makePaymentGraph(group);

        //update User info;
        return group;
    }
}
