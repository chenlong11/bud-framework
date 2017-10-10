package org.bud.framework.controller.app.admin.flow.process;

import com.alibaba.fastjson.JSON;
import org.bud.framework.service.flow.process.ProcessService;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        System.out.println(JSON.toJSONString(flowElements));
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


    @RequestMapping(value = "/{deploymentId}/start",method = RequestMethod.POST)
    public void start(@PathVariable String deploymentId) {
        String businessId = "";
        processService.startFlow(deploymentId,businessId);
    }


}
