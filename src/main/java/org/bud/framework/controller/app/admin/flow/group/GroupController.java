package org.bud.framework.controller.app.admin.flow.group;

import org.bud.framework.domain.flow.GroupRepresentation;
import org.bud.framework.domain.flow.ResultListDataRepresentation;
import org.bud.framework.domain.flow.UserRepresentation;
import org.bud.framework.service.flow.idm.GroupService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/app/admin/flow/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(method = RequestMethod.GET)
    public List<GroupRepresentation> getGroups(@RequestParam(required = false) String filter) {
        List<GroupRepresentation> result = new ArrayList<GroupRepresentation>();
        for (Group group : groupService.getGroups(filter)) {
            result.add(new GroupRepresentation(group));
        }
        return result;
    }

    @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
    public GroupRepresentation getGroup(@PathVariable String groupId) {
        return new GroupRepresentation(groupService.getGroup(groupId));
    }

    @RequestMapping(value = "/{groupId}/users", method = RequestMethod.GET)
    public ResultListDataRepresentation getGroupUsers(@PathVariable String groupId,
                                                      @RequestParam(required = false) String filter,
                                                      @RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer pageSize) {

        List<User> users = groupService.getGroupUsers(groupId, filter, page, pageSize);
        List<UserRepresentation> userRepresentations = new ArrayList<UserRepresentation>(users.size());
        for (User user : users) {
            userRepresentations.add(new UserRepresentation(user));
        }

        ResultListDataRepresentation resultListDataRepresentation = new ResultListDataRepresentation(userRepresentations);
        resultListDataRepresentation.setStart(page * pageSize);
        resultListDataRepresentation.setSize(userRepresentations.size());
        resultListDataRepresentation.setTotal(groupService.countTotalGroupUsers(groupId, filter, page, pageSize));
        return resultListDataRepresentation;
    }

    @RequestMapping(method = RequestMethod.POST)
    public GroupRepresentation createNewGroup(@RequestBody GroupRepresentation groupRepresentation) {
        return new GroupRepresentation(groupService.createNewGroup(groupRepresentation.getId(), groupRepresentation.getName(), groupRepresentation.getType()));
    }

    @RequestMapping(value = "/{groupId}", method = RequestMethod.PUT)
    public GroupRepresentation updateGroup(@PathVariable String groupId, @RequestBody GroupRepresentation groupRepresentation) {
        return new GroupRepresentation(groupService.updateGroupName(groupId, groupRepresentation.getName()));
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
    public void deleteGroup(@PathVariable String groupId) {
        groupService.deleteGroup(groupId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/{groupId}/members/{userId}", method = RequestMethod.POST)
    public void addGroupMember(@PathVariable String groupId, @PathVariable String userId) {
        groupService.addGroupMember(groupId, userId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/{groupId}/members/{userId}", method = RequestMethod.DELETE)
    public void deleteGroupMember(@PathVariable String groupId, @PathVariable String userId) {
        groupService.deleteGroupMember(groupId, userId);
    }

}
