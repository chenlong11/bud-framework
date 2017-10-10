package org.bud.framework.controller.app.es;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenlong
 * Date：2017/10/10
 * time：15:41
 */
@Controller
@RequestMapping("/app/es6")
public class ESController {

    /**
     * 变量赋值
     * @return
     */
    @RequestMapping("assignment")
    public String assignment() {
        return "/app/es6/assignment";
    }

}
