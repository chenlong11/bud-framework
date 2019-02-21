package org.bud.framework.service.flow.process;

import org.bud.framework.domain.flow.TaskRepresentation;
import org.flowable.bpmn.model.FlowElement;

import java.io.InputStream;
import java.util.List;

/**
 * Created by chenlong
 * Date：2017/9/22
 * time：9:53
 */
public interface ProcessService {

    InputStream getProcessImageView(String deploymentId);

    List<FlowElement> getFlowElementsByDeploymentId(String deploymentId);

    boolean startFlow(String processDefKey, String businessId, String userId);

    List<TaskRepresentation> getCandidateTasks(String userId);

    List<TaskRepresentation> getAssigneeTasks(String userId);

    boolean claimTask(String taskId, String userId);

    List<FlowElement> getNextNodes(String taskId);

    boolean toNext(String taskId);
}
