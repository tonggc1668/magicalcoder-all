package com.magicalcoder.youyaboot.core.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class HttpRequestBuildUrlUtil {

    public static String buildUrl(String baseUrl, Map<String, String> param) {
        StringBuilder sb = new StringBuilder(baseUrl + "?");
        for (String key : param.keySet()) {
            sb.append(key + "=" + param.get(key) + "&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    public static String buildCCXSign(Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            sb.append(entry.getKey() + entry.getValue());
        }
        return sb.toString();
    }

    public static String getCtx(HttpServletRequest request){
        String path = request.getContextPath();
        String CTX = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return CTX;
    }
}
