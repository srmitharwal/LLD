package splitwise.services;

import splitwise.models.Group;

import java.util.List;

public interface GroupService {

    public Group createGroup(Group group);

    public List<Group> getGroups();

    public Group getGroup(String groupId);

    public Group updateGroup(Group group);
}
