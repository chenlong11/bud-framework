package org.bud.framework.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * Created by chenlong
 * Date：2017/9/26
 * time：17:58
 */
public class JsonUtil {

    public Map<String,String> jsonStr2Map(String str) {
        return JSONObject.parseObject(str, new TypeReference<Map<String, String>>(){});
    }

}
