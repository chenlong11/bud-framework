package org.bud.framework.mapper.demo;

import org.bud.framework.util.StringUtil;
import org.bud.framework.vo.demo.DemoVo;

/**
 * Created by chenlong
 * Date：2017/9/9
 * time：14:09
 */
public class DemoProvider {

    public String findDemoByParams(DemoVo demoVo) {
        StringBuilder builder = new StringBuilder("select id,demo_str demoStr,demo_date demoDate,demo_number demoNumber " +
                " from test_demo a where 1=1 ");

        if(StringUtil.isNotEmpty(demoVo.getDemoStr())){
            builder.append(" and demo_str like concat('%',#{demoStr},'%') ");
        }

        return builder.toString();
    }

}
