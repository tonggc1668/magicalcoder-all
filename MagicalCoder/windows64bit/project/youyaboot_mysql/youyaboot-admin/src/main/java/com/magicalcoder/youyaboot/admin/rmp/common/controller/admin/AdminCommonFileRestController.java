package com.magicalcoder.youyaboot.admin.rmp.common.controller.admin;

import com.magicalcoder.youyaboot.admin.common.config.UploadFilePathConfig;
import com.magicalcoder.youyaboot.core.serialize.CommonReturnCode;
import com.magicalcoder.youyaboot.core.serialize.ResponseMsg;
import com.magicalcoder.youyaboot.core.utils.MapUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by magicalcoder.com on 2015/9/8.
 * 799374340@qq.com
 */
@RestController
@RequestMapping(value="/admin/common_file_rest/")
public class AdminCommonFileRestController {

    @Resource
    private UploadFilePathConfig uploadFilePathConfig;

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public ResponseMsg fileUpload(@RequestParam MultipartFile[] file,
                                  HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String realPath = uploadFilePathConfig.getUploadDiskFolder();
        //如果文件夹不存在就新建一个文件夹
        File dirPath = new File(realPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        String originalFilename = null;
        for (MultipartFile myfile : file) {
            if (!myfile.isEmpty()) {
                // 获取文件名
                originalFilename = myfile.getOriginalFilename();
                // 文件名后缀处理
                String suffix = originalFilename.substring(
                        originalFilename.lastIndexOf("."),
                        originalFilename.length());

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = df.format(new Timestamp(System.currentTimeMillis())) + "_"
                        + (int) (Math.random() * 900000 + 100000) + suffix;
                File storeFile = new File(realPath, newFileName);
                FileUtils.copyInputStreamToFile(
                        myfile.getInputStream(), storeFile);//上传文件到磁盘
                String prefix = uploadFilePathConfig.getRequestPrefix();
                if(prefix.startsWith("/")){
                    prefix = prefix.replaceFirst("/","");
                }
                return new ResponseMsg(MapUtil.buildMap("src",prefix+newFileName,"title",originalFilename));
            }
        }
        return new ResponseMsg(CommonReturnCode.FILE_UPLOAD_NO_FILE);
    }
}
