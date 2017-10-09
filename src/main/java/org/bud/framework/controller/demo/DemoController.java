package org.bud.framework.controller.demo;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.bud.framework.po.demo.Demo;
import org.bud.framework.repository.demo.DemoDao;
import org.bud.framework.util.IdGenerateUtil;
import org.bud.framework.vo.demo.DemoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by chenlong
 * Date：2017/9/6
 * time：15:52
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoDao demoDao;

    /**
     * 保存demo
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Integer save() {
        Demo demo = new Demo(Long.valueOf(IdGenerateUtil.generate16Id()),"字符串2",LocalDateTime.now(),19L);
        return demoDao.save(demo);
    }

    /**
     * 分页demo
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageInfo list(HttpServletRequest request){

        System.out.println(JSON.toJSONString(request.getParameterMap()));

        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");

        if (pageNum == null) {
            pageNum = "1";
        }

        if (pageSize == null) {
            pageSize = "1";
        }

        PageHelper.startPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
        List<DemoVo> list = demoDao.findAllDemo();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查询数量
     * @return
     */
    @RequestMapping("/count")
    @ResponseBody
    public Long count() {
        return PageHelper.count(() -> demoDao.findAllDemo());
    }


    @RequestMapping("/findByParams")
    @ResponseBody
    public List<DemoVo> findByParams() {
        DemoVo demoVo = new DemoVo();
        demoVo.setDemoStr("字符串");
        return demoDao.findByParams(demoVo);
    }

    public static void main(String[] args) {
        System.out.println(StringEscapeUtils.escapeHtml4("<script>张三</script>"));
    }

}
