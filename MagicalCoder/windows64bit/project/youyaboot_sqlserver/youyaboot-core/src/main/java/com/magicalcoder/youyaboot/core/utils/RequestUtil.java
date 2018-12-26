package com.magicalcoder.youyaboot.core.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RequestUtil {
    /**
     * 将request所有参数都存放到map中
     *
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParamsMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        @SuppressWarnings("rawtypes")
        Map requestParams = request.getParameterMap();
        for (@SuppressWarnings("rawtypes")
             Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }

}