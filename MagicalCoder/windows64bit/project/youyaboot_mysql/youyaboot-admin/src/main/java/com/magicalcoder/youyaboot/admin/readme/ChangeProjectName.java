package com.magicalcoder.youyaboot.admin.readme;


import com.magicalcoder.youyaboot.core.common.file.FileHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by www.magicalcoder.com on 2016/3/22.
 * 799374340@qq.com
 * 更改项目名称 大家根据自家的项目自行改名称吧
 * 包名称大家重命名吧
 */
public class ChangeProjectName {
    private static String[] source = {"com.magicalcoder.youyamvc","youyamvc"};//com.magicalcoder.youyamvc youyamvc
    private static String[] target = {"com.xx.cmsearch","cmsearch"};//com.xx.cmsearch           cmsearch
    private static String filePath = "E:\\temp\\cmsearch\\";//你下载的youyamvc的路径 youyamvc文件名请自行修改吧
    public static void main(String[] args) {
        File file = new File(filePath);
        List<File> fileList = new ArrayList<File>();
        findAllFile(fileList,file);

        for(File f:fileList){
            String text = FileHelper.fastReadAllLineFile(f,"UTF-8");
            if(text.contains(source[0])){
                System.out.println(f.getName());
                text = text.replace(source[0],target[0]);
                FileHelper.fastWriteFileUTF8(f,text);
            }
            if(text.contains(source[1])){
                System.out.println(f.getName());
                text = text.replace(source[1],target[1]);
                FileHelper.fastWriteFileUTF8(f,text);
            }
        }
    }

    static void findAllFile(List<File> fileList,File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f:files){
                findAllFile(fileList,f);
            }
        }else{
            fileList.add(file);
        }
    }
}
