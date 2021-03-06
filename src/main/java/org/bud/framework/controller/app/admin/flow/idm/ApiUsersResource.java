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

import org.bud.framework.domain.flow.GroupRepresentation;
import org.bud.framework.domain.flow.UserInformation;
import org.bud.framework.domain.flow.UserRepresentation;
import org.bud.framework.service.flow.exception.NotFoundException;
import org.bud.framework.service.flow.idm.UserService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app/admin/flow")
public class ApiUsersResource {

    @Autowired
    protected UserService userService;

    @RequestMapping(value = "/idm/users/{userId}", method = RequestMethod.GET, produces = { "application/json" })
    public UserRepresentation getUserInformation(@PathVariable String userId) {
        UserInformation userInformation = userService.getUserInformation(userId);
        if (userInformation != null) {
            UserRepresentation userRepresentation = new UserRepresentation(userInformation.getUser());
            if (userInformation.getGroups() != null) {
                for (Group group : userInformation.getGroups()) {
                    userRepresentation.getGroups().add(new GroupRepresentation(group));
                }
            }
            if (userInformation.getPrivileges() != null) {
                for (String privilege : userInformation.getPrivileges()) {
                    userRepresentation.getPrivileges().add(privilege);
                }
            }
            return userRepresentation;
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/idm/users", method = RequestMethod.GET, produces = { "application/json" })
    public List<UserRepresentation> findUsersByFilter(@RequestParam("filter") String filter) {
        List<User> users = userService.getUsers(filter, null, null);
        List<UserRepresentation> result = new ArrayList<UserRepresentation>();
        for (User user : users) {
            result.add(new UserRepresentation(user));
        }
        return result;
    }

}
