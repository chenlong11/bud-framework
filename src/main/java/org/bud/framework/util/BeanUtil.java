package org.bud.framework.util;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by chenlong
 * Date：2017/10/27
 * time：15:25
 */
public class BeanUtil {

    /**
     * 属性合并，以dest为准
     * @param src
     * @param dest
     */
    public static void merge(Object src ,Object dest){
        for (Field srcField : src.getClass().getDeclaredFields()) {
            try {
                PropertyDescriptor pd = new PropertyDescriptor(srcField.getName(), src.getClass());
                Method readMethod = pd.getReadMethod();//获得读方法
                Method writeMethod = pd.getWriteMethod();//获得写方法
                Object value = readMethod.invoke(dest);
                if(value == null){
                    continue;
                }
                if(value instanceof String && StringUtils.isEmpty((String)value)){
                    continue;
                }
                writeMethod.invoke(src, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * map转obj
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object map2object(Map<String,Object> map, Class<?> beanClass) throws Exception{
        if (map == null) {
            return null;
        }else {
            Object obj = beanClass.newInstance();
            BeanUtils.populate(obj, map);
            return obj;
        }
    }

    /**
     * obj转map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<?, ?> object2map(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        return new BeanMap(obj);
    }



}
