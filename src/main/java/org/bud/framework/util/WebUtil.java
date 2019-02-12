package org.bud.framework.util;

import org.bud.framework.constants.AppConstants;
import org.bud.framework.domain.system.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by chenlong
 * Date：2017/9/10
 * time：11:50
 */
public class WebUtil {

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static User getCurrentUser() {
        User user = (User)getSession().getAttribute(AppConstants.APP_LOGIN_IDM);
        if (user == null) {
            user = new User();
            user.setId("1113543923510881");
            user.setName("Admin");
        }
        return user;
    }

}
