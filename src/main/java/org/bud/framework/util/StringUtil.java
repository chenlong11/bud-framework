package org.bud.framework.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Optional;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by chenlong
 * Date：2017/6/17
 * time：19:01
 */
public class StringUtil {

    /**
     * obj转str，不存在则返回空字符串
     *
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        Optional optional = Optional.fromNullable(obj);
        if (optional != null && optional.isPresent()) {//不为空
            return optional.get().toString();
        } else {
            return "";
        }
    }

    public static boolean isNotEmpty(Object obj) {
        if (StringUtils.isNotEmpty(toString(obj))) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Object obj) {
        if (null != obj) {
            return true;
        }
        return false;
    }

}
