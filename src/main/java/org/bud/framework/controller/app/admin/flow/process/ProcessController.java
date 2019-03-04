package org.bud.framework.controller.app.admin.flow.process;

import cn.hutool.core.util.RandomUtil;
import org.bud.framework.domain.flow.TaskRepresentation;
import org.bud.framework.service.flow.process.ProcessService;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by chenlong
 * Date：2017/9/21
 * time：16:24
 */
@Controller
@RequestMapping("/app/admin/flow/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @Autowired
    private RuntimeService runtimeService;

    @RequestMapping(value = "/{deploymentId}/config", method = RequestMethod.GET)
    public String config(Model model,@PathVariable String deploymentId) {

        model.addAttribute("deploymentId", deploymentId);
        List<FlowElement> flowElements = processService.getFlowElementsByDeploymentId(deploymentId);
        model.addAttribute("flowElements", flowElements);
        return "/app/admin/flow/process/processConfig";
    }

    @RequestMapping(value = "/{deploymentId}/image", method = RequestMethod.GET)
    public void image(@PathVariable String deploymentId, HttpServletResponse response) {
        InputStream in = processService.getProcessImageView(deploymentId);
        try {
            OutputStream out = response.getOutputStream();
            // 把图片的输入流程写入resp的输出流中
            byte[] b = new byte[1024];
            for (int len = -1; (len = in.read(b)) != -1; ) {
                out.write(b, 0, len);
            }
            // 关闭流
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{processDefKey}/start",method = RequestMethod.GET)
    @ResponseBody
    public void start(@PathVariable String processDefKey) {
        String userId = "yuangong";
        String businessId = RandomUtil.simpleUUID();
        processService.startFlow(processDefKey, businessId, userId);
    }

    @RequestMapping(value = "/{userId}/candidateTasks",method = RequestMethod.GET)
    @ResponseBody
    public List<TaskRepresentation> candidateTasks(@PathVariable String userId){
        return processService.getCandidateTasks(userId);
    }

    @RequestMapping(value = "/{userId}/AssigneeTasks",method = RequestMethod.GET)
    @ResponseBody
    public List<TaskRepresentation> AssigneeTasks(@PathVariable String userId){
        return processService.getAssigneeTasks(userId);
    }

    @RequestMapping(value = "/claimTask",method = RequestMethod.GET)
    @ResponseBody
    public boolean claimTask(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String taskId = request.getParameter("taskId");
        return processService.claimTask(taskId, userId);
    }

    @RequestMapping(value = "/{taskId}/nextNodes",method = RequestMethod.GET)
    @ResponseBody
    public String nextNodes(@PathVariable String taskId){
        List<FlowElement> nextNodes = processService.getNextNodes(taskId);
        String res = "";
        for (int i=0; i<nextNodes.size(); i++) {
            res += (i + 1) + ") " + nextNodes.get(i).getName() + "-" + nextNodes.get(i).getId();
        }
        return res;
    }

    @RequestMapping(value = "/{taskId}/toNext",method = RequestMethod.GET)
    @ResponseBody
    public boolean toNext(@PathVariable String taskId){
        return processService.toNext(taskId);
    }

    @RequestMapping(value = "/{procInstId}/getBusinessKey",method = RequestMethod.GET)
    @ResponseBody
    public String getBusinessKey(@PathVariable String procInstId){
        return processService.getBusinessKey(procInstId);
    }

}
