package org.bud.framework.conf;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.bud.framework.annotation.SysLog;
import org.bud.framework.util.IPAddressUtil;
import org.bud.framework.util.IdGenerateUtil;
import org.bud.framework.util.RequestUtil;
import org.bud.framework.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by chenlong
 * Date：2017/6/3
 * time：23:45
 */
@Component
@Aspect
public class AspectConfig {

    Logger logger = LoggerFactory.getLogger(AspectConfig.class);

    /**
     * 操作日志
     */
    @Pointcut(value = "(execution(* org.bud.framework..*.*(..)) && @annotation(org.bud.framework.annotation.SysLog))")
    public void operationLogPointCut() {}


    @Before("operationLogPointCut()&&@annotation(sysLog)")
    public void doOperationLogBefore(JoinPoint point, SysLog sysLog) {
        //获取request对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //获取日志记录参数
        String url = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        String uri = request.getRequestURI();
        String ip = IPAddressUtil.getIpAddr(request);
        Map<String, String> parameterMap = RequestUtil.getParameterMap(request);
        String associcateId = "-1";
        if(StringUtil.isNotEmpty(sysLog.associateId())&& StringUtil.isNotEmpty(parameterMap.get(sysLog.associateId()))){
            associcateId = parameterMap.get(sysLog.associateId());
        }
        String queryString = JSON.toJSONString(parameterMap);
        logger.info("请求开始, ip: {}, url: {}, method: {}, uri: {}, params: {}",ip, url, requestMethod, uri, queryString);
        String className = point.getTarget().getClass().toString();//获取目标类名
        String methodName = point.getSignature().getName();
        logger.info("类: {}, 方法: {} 开始执行操作->{}", className, methodName, sysLog.value());
        logger.info("associcateId: {}", associcateId);

        //数据库插入记录
        String recordId = IdGenerateUtil.generateUUID();
        //...
    }

    @AfterReturning("operationLogPointCut()")
    public void doOperationLogAfter(JoinPoint point) {
        logger.info("操作成功");
    }

    @AfterThrowing(value="operationLogPointCut()", throwing="ex")
    public void addLog(Exception ex){

        //获取request对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String recordId = StringUtil.toString(request.getAttribute("recordId"));
        logger.info("操作失败");
        StringWriter sw=new StringWriter();
        PrintWriter pw=new PrintWriter(sw);
        ex.printStackTrace(pw);
        //更新操作结果
        //...
        logger.info(sw.toString());
    }

}
