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

import org.bud.framework.domain.flow.UserRepresentation;
import org.bud.framework.service.flow.exception.NotFoundException;
import org.bud.framework.service.flow.idm.RemoteIdmService;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * REST controller for managing users.
 */
@RestController
public class UserResource {

    @Autowired
    protected RemoteIdmService remoteIdmService;

    @RequestMapping(value = "/app/admin/flow/users/{userId}", method = RequestMethod.GET, produces = "application/json")
    public UserRepresentation getUser(@PathVariable String userId, HttpServletResponse response) {
        User user = remoteIdmService.getUser(userId);

        if (user == null) {
            throw new NotFoundException("User with id: " + userId + " does not exist or is inactive");
        }

        return new UserRepresentation(user);
    }

}
