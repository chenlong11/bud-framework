package org.bud.framework.service.flow.process;

import org.bud.framework.domain.flow.TaskRepresentation;
import org.flowable.bpmn.model.*;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

/**
 * Created by chenlong
 * Date：2017/9/22
 * time：9:54
 */
@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    public InputStream getProcessImageView(String deploymentId) {
        String defId = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult().getId();
        ProcessDefinition processDefinition = repositoryService.getProcessDefinition(defId);
        String source = processDefinition.getDiagramResourceName();
        return repositoryService.getResourceAsStream(deploymentId, source);
    }

    @Override
    public List<FlowElement> getFlowElementsByDeploymentId(String deploymentId) {
        String defId = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult().getId();
        BpmnModel model = repositoryService.getBpmnModel(defId);
        List list = new ArrayList<FlowElement>();
        if (model != null) {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for (FlowElement flowElement : flowElements) {
                if (!(flowElement instanceof SequenceFlow) && !(flowElement instanceof EndEvent)) {//只获取节点
                    list.add(flowElement);
                }
            }
        }
        return list;
    }

    @Override
    public boolean startFlow(String processDefKey, String businessId, String userId) {
        try {
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("businessId", businessId);
            Authentication.setAuthenticatedUserId(userId);
            runtimeService.startProcessInstanceByKey(processDefKey, variables);
            Authentication.setAuthenticatedUserId(null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public List<TaskRepresentation> getCandidateTasks(String userId) {
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userId).list();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @Override
    public List<TaskRepresentation> getAssigneeTasks(String userId) {
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).list();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @Override
    public boolean claimTask(String taskId, String userId) {
        try {
            taskService.claim(taskId, userId);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<FlowElement> getNextNodes(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        ExecutionEntity ee = (ExecutionEntity) runtimeService.createExecutionQuery()
                .executionId(task.getExecutionId()).singleResult();
        // 当前审批节点
        String crruentActivityId = ee.getActivityId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(crruentActivityId);
        List<SequenceFlow> outFlows = flowNode.getOutgoingFlows();

        List<FlowElement> flowElements = new ArrayList<FlowElement>();
        for (int i = 0; i < outFlows.size(); i++) {
            FlowElement targetFlow = outFlows.get(i).getTargetFlowElement();
            flowElements.add(targetFlow);
        }
        return flowElements;
    }

    @Override
    public boolean toNext(String taskId) {
        try {
            taskService.complete(taskId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
