package org.bud.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenlong
 * Date：2017/9/6
 * time：17:47
 */
@Controller
public class IndexController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
