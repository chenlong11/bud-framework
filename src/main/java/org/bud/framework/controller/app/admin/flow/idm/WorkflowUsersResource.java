/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bud.framework.controller.app.admin.flow.idm;

import org.bud.framework.domain.flow.ResultListDataRepresentation;
import org.bud.framework.domain.flow.UserRepresentation;
import org.bud.framework.service.flow.idm.RemoteIdmService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.identitylink.api.IdentityLink;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Rest resource for managing users, specifically related to tasks and processes.
 */
@RestController
@RequestMapping("/app/admin/flow")
public class WorkflowUsersResource {

    @Autowired
    private RemoteIdmService remoteIdmService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/workflow-users", method = RequestMethod.GET)
    public ResultListDataRepresentation getUsers(@RequestParam(value = "filter", required = false) String filter,
                                                 @RequestParam(value = "excludeTaskId", required = false) String excludeTaskId,
                                                 @RequestParam(value = "excludeProcessId", required = false) String excludeProcessId) {

        List<? extends User> matchingUsers = remoteIdmService.findUsersByNameFilter(filter);

        // Filter out users already part of the task/process of which the ID has been passed
        if (excludeTaskId != null) {
            filterUsersInvolvedInTask(excludeTaskId, matchingUsers);
        } else if (excludeProcessId != null) {
            filterUsersInvolvedInProcess(excludeProcessId, matchingUsers);
        }

        List<UserRepresentation> userRepresentations = new ArrayList<UserRepresentation>(matchingUsers.size());
        for (User user : matchingUsers) {
            userRepresentations.add(new UserRepresentation(user));
        }

        return new ResultListDataRepresentation(userRepresentations);

    }

    protected void filterUsersInvolvedInProcess(String excludeProcessId, List<? extends User> matchingUsers) {
        Set<String> involvedUsers = getInvolvedUsersAsSet(
                runtimeService.getIdentityLinksForProcessInstance(excludeProcessId));
        removeinvolvedUsers(matchingUsers, involvedUsers);
    }

    protected void filterUsersInvolvedInTask(String excludeTaskId, List<? extends User> matchingUsers) {
        Set<String> involvedUsers = getInvolvedUsersAsSet(taskService.getIdentityLinksForTask(excludeTaskId));
        removeinvolvedUsers(matchingUsers, involvedUsers);
    }

    protected Set<String> getInvolvedUsersAsSet(List<IdentityLink> involvedPeople) {
        Set<String> involved = null;
        if (involvedPeople.size() > 0) {
            involved = new HashSet<String>();
            for (IdentityLink link : involvedPeople) {
                if (link.getUserId() != null) {
                    involved.add(link.getUserId());
                }
            }
        }
        return involved;
    }

    protected void removeinvolvedUsers(List<? extends User> matchingUsers, Set<String> involvedUsers) {
        if (involvedUsers != null) {
            // Using iterator to be able to remove without ConcurrentModExceptions
            Iterator<? extends User> userIt = matchingUsers.iterator();
            while (userIt.hasNext()) {
                if (involvedUsers.contains(userIt.next().getId())) {
                    userIt.remove();
                }
            }
        }
    }

}
