package com.magicalcoder.youyaboot.admin.rmp.util;

import com.magicalcoder.youyaboot.admin.rmp.constant.PermissionConstant;
import com.magicalcoder.youyaboot.core.utils.ListUtil;

import java.util.*;

public class HttpReqUtil {

    /**
     * 根据首字母排序http入参顺序
     * @param reqMap
     * @param dropParamNames
     * @return
     */
    public static String sortParams(Map<String,String[]> reqMap,List<String> dropParamNames){
        StringBuffer sb = new StringBuffer();
        List<String> paramNames = new ArrayList<>(reqMap.size());
        paramNames.addAll(reqMap.keySet());
        if(ListUtil.isNotBlank(dropParamNames)){
            for(String dropParam:dropParamNames){
                paramNames.remove(dropParam);
            }
        }
        if(ListUtil.isBlank(paramNames)){
            return sb.toString();
        }
        Collections.sort(paramNames);
        for(String paramName : paramNames){
            String[] pv = reqMap.get(paramName);
            for(String v:pv){
                sb.append("&").append(paramName).append("=").append(v);
            }
        }
        return sb.substring(1);
    }

    public static void main(String[] args) {
        Map<String,String[]> reqMap = new HashMap<>();
        reqMap.put("hello",new String[]{"你好","很好"});
        reqMap.put("able",new String[]{"true"});
        reqMap.put("test",new String[]{"测试"});
        reqMap.put("date",new String[]{"日期"});
        System.out.println(sortParams(reqMap,Collections.singletonList("date")));
    }
    //寻找moduleName
    public static String moduleName(String reqUrl){
        if(reqUrl.startsWith(PermissionConstant.ADMIN_PREFIX)){// /admin/tableName/....
            String[] add = reqUrl.split("/");
            if(add.length>=3){
                return add[2];
            }
        }
        return null;
    }
    public static String operateType(String reqUrl){
        if(reqUrl.startsWith(PermissionConstant.ADMIN_PREFIX)){// /admin/tableName/....
            String[] add = reqUrl.split("/");
            if(add.length>=4){
                return add[3];
            }
        }
        return null;
    }
}
