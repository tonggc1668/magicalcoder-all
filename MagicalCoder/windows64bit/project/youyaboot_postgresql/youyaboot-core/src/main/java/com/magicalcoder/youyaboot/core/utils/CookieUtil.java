package com.magicalcoder.youyaboot.core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by www.magicalcoder.com on 2018/3/23.
 */
public class CookieUtil {
    /**
     * 添加cookie
     * @param res 目标httpResponse对象
     * @param key cookie名称
     * @param value cookie值
     */
    public static void addCookie(HttpServletResponse res, String key, String value) {
        Cookie cookie = cookieBuild(key, value, 60*60*24*365, "/", false);
        res.addCookie(cookie);
    }

    /**
     *
     * @param key cookie名称
     * @param value cookie值
     * @param maxAge cookie有效期
     * @param path Specifies a path for the cookie to which the client should return the cookie.
     * @param httpOnly Marks or unmarks this Cookie as HttpOnly.
     * @return Cookie
     */
    private static Cookie cookieBuild(String key, String value, Integer maxAge, String path, Boolean httpOnly){
        Cookie cookie;
        try {
            cookie = new Cookie(key, URLEncoder.encode(value,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            cookie = new Cookie(key, value);
        }
        //设置cookie过期时间为365天
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
		cookie.setHttpOnly(httpOnly);
		return cookie;
    }

    /**
     * 删除cookie
     * @param res 目标httpResponse对象
     * @param key cookie名称
     */
    public static void deleteCookie(HttpServletResponse res, String key) {
        Cookie cookie = new Cookie(key, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

    /**
     * 删除cookie
     * @param res
     * @param key
     * @param domain
     */
    public static void deleteCookie(HttpServletResponse res, String key, String domain) {
        Cookie cookie = new Cookie(key, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setDomain(domain);
        res.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String key){
        Map<String, String> cookies =  getCookies(request);
        if(cookies == null){
            return null;
        }
        try {
            return URLDecoder.decode(cookies.getOrDefault(key, ""), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return cookies.get(key);
        }
    }

    /**
     * 判断cookie是否存在
     * @param request
     * @param key
     * @return
     */
    public static boolean isCookieExists(HttpServletRequest request, String key) {
        Map<String, String> cookies =  getCookies(request);
        if ( cookies!=null ){
            return cookies.containsKey(key);
        }
        return false;
    }

    /**
     * 将cookies[]转为{cookieName: cookieValue}
     * 使用TreeMap<>(String.CASE_INSENSITIVE_ORDER)忽略name的大小写
     * @param request 目标request对象上的cookie
     * @return cookies
     */
    private static Map<String, String> getCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Map<String, String> cookieMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        if(null != cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }
}
