package org.bud.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by chenlong
 * Date：2017/6/5
 * time：21:33
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 功能描述
     * @return
     */
    public String value() default "";

    /**
     * 关联ID
     * @return
     */
    public String associateId() default "";

}
