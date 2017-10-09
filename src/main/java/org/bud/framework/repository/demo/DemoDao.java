package org.bud.framework.repository.demo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.bud.framework.po.demo.Demo;
import org.bud.framework.vo.demo.DemoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenlong
 * Date：2017/9/4
 * time：17:50
 */
@Repository
public interface DemoDao {

    @Insert({"insert into test_demo (id,demo_str,demo_date,demo_number) values(#{id},#{demoStr},#{demoDate},#{demoNumber})"})
    Integer save(Demo demo);

    @Select({"select id,demo_str demoStr,demo_date demoDate,demo_number demoNumber from test_demo order by demo_date desc "})
    List<DemoVo> findAllDemo();

    @SelectProvider(type = DemoProvider.class, method = "findDemoByParams")
    List<DemoVo> findByParams(DemoVo demoVo);

}
