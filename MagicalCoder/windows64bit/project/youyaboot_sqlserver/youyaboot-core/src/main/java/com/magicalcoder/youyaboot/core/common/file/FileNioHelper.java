package com.magicalcoder.youyaboot.core.common.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by www.magicalcoder.com on 2015/6/25.
 * 799374340@qq.com
 * 遇到大文件 这个性能比较快
 */
public class FileNioHelper {
    public static String fastReadFile(String file){
        try {
            StringBuffer sb = new StringBuffer();
            RandomAccessFile accessFile = new RandomAccessFile(file,"r");
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
            while (true){
                int readableSize = fileChannel.read(byteBuffer);
                if(readableSize == -1){
                    break;
                }
                byte[] contentByte = new byte[readableSize];
                //准备读
                byteBuffer.flip();
                byteBuffer.get(contentByte,0,readableSize);
                String result = new String(contentByte);
                sb.append(result);
                //准备重写
                byteBuffer.clear();
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String fastReadFile2(String file){
        try {
            StringBuffer sb = new StringBuffer();
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
            while (true){
                int readableSize = fileChannel.read(byteBuffer);
                if(readableSize == -1){
                    break;
                }
                byte[] contentByte = new byte[readableSize];
                //准备读
                byteBuffer.flip();
                byteBuffer.get(contentByte,0,readableSize);
                String result = new String(contentByte);
                sb.append(result);
                //准备重写
                byteBuffer.clear();
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void fastWrite(String file,String content){
        try {
            FileOutputStream fileInputStream = new FileOutputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();
            byte[] bytes = content.getBytes();
            int cacheSize = 1024*1024;
            ByteBuffer byteBuffer = ByteBuffer.allocate(cacheSize);
            for(int i=0;i<bytes.length;i=i+cacheSize){
                int length = Math.min(cacheSize,bytes.length-i);
                byteBuffer.put(bytes,i,length);
                byteBuffer.flip();
                fileChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String file = "C:\\cn\\nio.txt";
        FileNioHelper nio = new FileNioHelper();
        Long a = System.currentTimeMillis();
//        String content = nio.fastReadFile(file);
//        for(int i=0;i<20;i++){
//            FileHelper.fastReadFile(file,"GBK");
//            nio.fastReadFile2(file);//5061
//            nio.fastReadFile(file);//5099
//        }

        StringBuffer sb = new StringBuffer();
        for(int i=0;i<1000000;i++){
            sb.append("1234567890");
        }
//        nio.fastWrite(file,sb.toString());
       FileHelper.fastWriteFileUTF8(file,sb.toString());

        System.out.println(System.currentTimeMillis() - a);
    }
}
