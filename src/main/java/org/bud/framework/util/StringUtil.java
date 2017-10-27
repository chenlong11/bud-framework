package org.bud.framework.util;

import com.google.common.base.Optional;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

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

    public static String getRandomZHChar(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBk"); //转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

}
