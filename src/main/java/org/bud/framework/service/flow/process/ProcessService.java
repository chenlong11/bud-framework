package org.bud.framework.service.flow.process;

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

    void startFlow(String deploymentId, String businessId);
}
