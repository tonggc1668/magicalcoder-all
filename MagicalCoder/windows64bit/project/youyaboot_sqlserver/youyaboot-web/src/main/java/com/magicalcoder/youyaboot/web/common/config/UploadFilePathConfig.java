package com.magicalcoder.youyaboot.web.common.config;

import com.magicalcoder.youyaboot.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by magicalcoder.com on 2018/8/5.
 * 文件上传 虚拟路径映射 因为springboot默认不支持文件上传 慎重选择
 */
@Configuration
public class UploadFilePathConfig extends WebMvcConfigurerAdapter {

    @Value("${magicalcoder.file.upload.mapping.uploadDiskFolder:}")
    private String uploadDiskFolder;
    @Value("${magicalcoder.file.upload.mapping.requestPrefix:}")
    private String requestPrefix;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        uploadDiskFolder = lastUploadPath(uploadDiskFolder);
        if(!requestPrefix.endsWith("/")){
            requestPrefix = requestPrefix+"/";
        }
        registry.addResourceHandler(requestPrefix+"**").addResourceLocations("file:" + uploadDiskFolder);
    }

    private String lastUploadPath(String uploadDiskFolder){
        if(StringUtil.isBlank(uploadDiskFolder)){
            uploadDiskFolder = UploadFilePathConfig.class.getResource("/").getPath()+"/upload/";
        }
        return uploadDiskFolder;
    }

    public String getUploadDiskFolder() {
        return uploadDiskFolder;
    }

    public String getRequestPrefix() {
        return requestPrefix;
    }
}
