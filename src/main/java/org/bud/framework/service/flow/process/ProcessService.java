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

    /**
     * 根据发布id获取流程预览图
     * @param deploymentId
     * @return
     */
    InputStream getProcessImageView(String deploymentId);

    List<FlowElement> getFlowElementsByDeploymentId(String deploymentId);

    /**
     * 根据流程定义key启动流程
     * @param processDefKey 流程定义key
     * @param businessKey 业务key
     * @param userId 流程发起人id
     * @return
     */
    boolean startFlow(String processDefKey, String businessKey, String userId);

    /**
     * 查询待办事项
     * @param userId
     * @return
     */
    List<TaskRepresentation> getCandidateTasks(String userId);

    /**
     * 查询在办事项
     * @param userId
     * @return
     */
    List<TaskRepresentation> getAssigneeTasks(String userId);

    /**
     * 认领待办事项
     * @param taskId
     * @param userId
     * @return
     */
    boolean claimTask(String taskId, String userId);

    List<FlowElement> getNextNodes(String taskId);

    /**
     * 根据processId获取业务id
     * @param processId
     * @return
     */
    String getBusinessKey(String processId);

    /**
     * 发送到下个节点
     * @param taskId
     * @return
     */
    boolean toNext(String taskId);

    boolean toNext(String taskId, String userId);
}
