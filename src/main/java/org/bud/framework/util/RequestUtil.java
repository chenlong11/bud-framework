package org.bud.framework.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chenlong
 * Date：2017/10/11
 * time：10:49
 */
public class RequestUtil {

    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        // 返回值Map
        Map params = request.getParameterMap();
        Map returnMap = new HashMap();
        Iterator entries = params.entrySet().iterator();
        Map.Entry entry;
        String name = "";

        while (entries.hasNext()) {
            String value = "";
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value += values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;

    }
}
