package com.magicalcoder.youyaboot.admin.rmp.common;

import com.magicalcoder.youyaboot.core.serialize.CommonReturnCode;
import com.magicalcoder.youyaboot.core.serialize.KeyValuePair;

/**
 * author: www.magicalcoder.com
 * date:2018/7/10
 * function:
 */
public class SecurityReturnCode extends CommonReturnCode {

    public static final KeyValuePair LOGIN_FAIL = new KeyValuePair(70404, "用户名或密码错误", "用户名或密码错误");
    public static final KeyValuePair RE_LOGIN = new KeyValuePair(70404, "暂无权限，请重新登陆", "暂无权限，请重新登陆");
    public static final KeyValuePair NO_LOGIN = new KeyValuePair(70404, "获取用户id为null,请重新登陆", "获取用户id为null,请重新登陆");

    public static final KeyValuePair NO_PERMISSION = new KeyValuePair(70403, "暂无权限", "暂无权限");
    public static final KeyValuePair URL_ADMIN_PREFIX_NOT_EXIST_USER = new KeyValuePair(70504, "url格式必须以/admin/开头，否则无法找到moduleName", "url格式必须以/admin/开头，否则无法找到moduleName");
    public static final KeyValuePair  PASSWORD_NULL = new KeyValuePair(70501, "密码不能为空", "密码不能为空");

}
