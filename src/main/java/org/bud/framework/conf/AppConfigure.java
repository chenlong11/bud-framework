package org.bud.framework.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenlong
 * Date：2017/9/7
 * time：15:00
 */
@Configuration
@MapperScan("org.bud.framework.mapper")//MyBatis的接口类都放在该文件夹下，这样每个接口都不用注解@Mapper了。
public class AppConfigure {

}
