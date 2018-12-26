package com.magicalcoder.youyaboot.core.common.file;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by www.magicalcoder.com on 14-3-27.
 * 799374340@qq.com
 */
public class FileHelper {
    /*原样读取文件*/
    public  static String fastReadAllLineFile(File file,String charType){
        try{
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader inr = new InputStreamReader(fis,charType);
                BufferedReader br = new BufferedReader(inr);
                //每行内容
                String lineStr="";
                StringBuilder sb = new StringBuilder();
                while((lineStr=br.readLine())!=null){
                    sb.append(lineStr).append("\n");
                }
                br.close();
                inr.close();
                fis.close();
                return sb.toString();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /*精简读取文件*/
    public  static String fastReadFile(File file,String charType){
        try{
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader inr = new InputStreamReader(fis,charType);
                BufferedReader br = new BufferedReader(inr);
                //每行内容
                String lineStr="";
                StringBuilder sb = new StringBuilder();
                while((lineStr=br.readLine())!=null){
                    if(lineStr!=null && !"".equals(lineStr)){
                        String str = lineStr.trim();
                        sb.append(str);
                    }
                }
                br.close();
                inr.close();
                fis.close();
                return sb.toString();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public  static String fastReadFileUTF8(InputStream fis){
        return fastReadFile(fis,"UTF-8");
    }
    /*精简读取文件*/
    public  static String fastReadFile(InputStream fis,String charType){
        try{
            InputStreamReader inr = new InputStreamReader(fis,charType);
            BufferedReader br = new BufferedReader(inr);
            //每行内容
            String lineStr="";
            StringBuilder sb = new StringBuilder();
            while((lineStr=br.readLine())!=null){
                if(lineStr!=null && !"".equals(lineStr)){
                    String str = lineStr.trim();
                    sb.append(str);
                }
            }
            br.close();
            inr.close();
            fis.close();
            return sb.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 快速读文件
     * @param fileSrc
     * @param charType 编码
     * @return
     */
    public  static String fastReadFile(String fileSrc,String charType){
         File file = new File(fileSrc);
        return fastReadFile(file,charType);
    }
    public  static String fastReadFileUTF8(File file){
        return fastReadFile(file,"UTF-8");
    }
    public  static String fastReadFileUTF8(String fileUrl){
        return fastReadFile(fileUrl,"UTF-8");
    }
    public static void fastWriteFile(String fileUrl,InputStream fileContent){
        fastWriteFile(new File(fileUrl),fileContent);
    }
    public static void fastWriteFile(File file,InputStream fileContent){
        //先创建目录
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            if(!file.exists()){
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            int c;
            while((c=fileContent.read())!=-1)
            {
                fos.write(c);
            }
            fos.flush();
            fileContent.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void fastWriteFile(File file,String fileContent,String charType){
        //先创建目录
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, charType);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(fileContent);
            bw.flush();
            bw.close();
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 写文件
     * @param fileSrc
     * @param fileContent
     * @param charType 字符集  UTF-8 GBK
     */
    public static void fastWriteFile(String fileSrc,String fileContent,String charType){
        File file = new File(fileSrc);
        fastWriteFile(file,fileContent,charType);
    }
    public static void fastWriteFileUTF8(String fileSrc,String fileContent){
        fastWriteFile(fileSrc,fileContent,"UTF-8");
    }
    public static void fastWriteFileUTF8(File file,String fileContent){
        fastWriteFile(file, fileContent, "UTF-8");
    }

    public static void main(String[] args) {
        File f = new File("C:\\nginx-1.5.11\\html\\rankcache\\");
        File[] fs = f.listFiles();
        long a = System.currentTimeMillis();
        for(File ff:fs){
            String s = fastReadFile(ff,"UTF-8");
            long a1 = System.currentTimeMillis();
//            fastWrite(ff,s);
            fastWriteFileUTF8(ff,s);
            long a2 = System.currentTimeMillis();
            System.out.println((a2-a1)+"ms");
        }
        long b=System.currentTimeMillis();
        System.out.println((b - a) + "ms");
    }

    public static void fastWrite(File f,String content){
        try {
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void copy(String source,String target){
        try {
            copy(new File(source), new File(target));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy(File source, File target) throws IOException {
        if(!source.exists()){
            return;
        }
        if(!target.exists()){
            if (!target.getParentFile().exists()) {
                target.getParentFile().mkdirs();
            }
            target.createNewFile();
        }
        // 创建文件输入输出流对象
        FileInputStream is = new FileInputStream(source);
        FileOutputStream os = new FileOutputStream(target);
        // 设定读取的字节数
        int count, n = 512;
        byte buffer[] = new byte[n];
        // 读取输入流
        count = is.read(buffer, 0, n);
        while (count != -1) {
            os.write(buffer, 0, count);
            count = is.read(buffer, 0, n);
        }
        // 关闭输入输出流
        is.close();
        os.close();
    }

    //删除文件或者文件夹下面所有的文件
    public static void deleteAllFiles(File rootFile){
        if(!rootFile.exists()){
            return;
        }
        if(rootFile.isFile()){
            rootFile.delete();
            return;
        }
        File[] allFiles = rootFile.listFiles();
        if(allFiles!=null && allFiles.length>0){
            for(File f:allFiles){
                if(f.isDirectory()){
                    deleteAllFiles(f);
                }else {
                    f.delete();
                }
            }
        }else {
            rootFile.delete();
        }
    }

    /**
     * 下载文件 默认按照文件名后缀保存 下载速度很快 比httpclient快很多
     * @param fileUrl 远程地址
     * @param savePath 保存目录
     */
    public static void download(String fileUrl,String savePath){
        try {
            String[] parr = fileUrl.split("/");
            String fileName = parr[parr.length - 1];
            URL url = new URL(fileUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath + fileName));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
