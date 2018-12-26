package com.magicalcoder.youyaboot.admin.rmp.common.filter;

import com.magicalcoder.youyaboot.admin.rmp.constant.PermissionConstant;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

/**
 *
 */
@Service
public class MyInvocationSecurityMetadataSourceService  implements
        FilterInvocationSecurityMetadataSource {

    //必须有返回，才会进一步返回结果给decide 做进一步判断筛选，否则decide不执行
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = ((FilterInvocation) object);
        String serverReqUrl = filterInvocation.getRequestUrl();
        if(!serverReqUrl.startsWith(PermissionConstant.ADMIN_PREFIX)){
            return null;//放行所有非 /admin/开头的接口 统统不需要过滤
        }
        return Collections.singleton(null);//返回一个实例 到decide方法再详细判断
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
