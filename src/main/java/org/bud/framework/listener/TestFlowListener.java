package org.bud.framework.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

import java.util.Arrays;

public class TestFlowListener  implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String assigneeListStr = "jingli,zongjian";
        delegateTask.setVariable("assigneeList", Arrays.asList(assigneeListStr.split(",")));
    }
}
