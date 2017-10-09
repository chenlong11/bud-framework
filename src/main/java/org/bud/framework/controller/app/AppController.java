package org.bud.framework.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenlong
 * Date：2017/9/8
 * time：9:19
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("userName","chen long");
        return "/app/index";
    }

}
