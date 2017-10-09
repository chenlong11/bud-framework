package org.bud.framework.service.flow.process;

import com.alibaba.fastjson.JSON;
import org.flowable.bpmn.model.*;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by chenlong
 * Date：2017/9/22
 * time：9:54
 */
@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public InputStream getProcessImageView(String deploymentId) {
        String defId =  repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult().getId();
        ProcessDefinition processDefinition = repositoryService.getProcessDefinition(defId);
        String source = processDefinition.getDiagramResourceName();
        return repositoryService.getResourceAsStream(deploymentId,source);
    }

    @Override
    public List<FlowElement> getFlowElementsByDeploymentId(String deploymentId) {
        String defId = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult().getId();
        BpmnModel model = repositoryService.getBpmnModel(defId);
        List list = new ArrayList<FlowElement>();
        if(model != null) {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for (FlowElement flowElement : flowElements) {
                if (!(flowElement instanceof SequenceFlow)&&!(flowElement instanceof EndEvent)){//只获取节点
                    list.add(flowElement);
                }
            }
        }
        return list;
    }
}
