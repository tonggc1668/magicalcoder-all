package com.magicalcoder.youyaboot.core.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.magicalcoder.youyaboot.core.utils.ImgeUtil;
import com.magicalcoder.youyaboot.core.utils.StringUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
public class JsonOutWriter {
    static final int SUCCESS = CommonReturnCode.SUCCESS.getKey();//成功

    static final SerializerFeature[] features = new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect};
    static final SerializerFeature[] simpleFeatures = new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect};
    static final SerializeConfig config = new SerializeConfig();
    static final SerializeConfig highChartsConfig = new SerializeConfig();
    static {
        //普通数据使用的 日期
        config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        //统计图表使用的方法 自定义日期
        highChartsConfig.put(Date.class,new MyDateSerializer());
    }

    /**
     * 正常情况时
     * 前端快速返回json
     * @param response
     */
    public void toWebSuccess(HttpServletResponse response){
        toJsonData(response,new JsonBuilder.Builder(null).flag(true).code(SUCCESS).build());
    }
    public void toWebSuccess(HttpServletResponse response,Object data){
        toJsonData(response,new JsonBuilder.Builder(data).flag(true).code(SUCCESS).build());
    }
    public void toWebShortSuccess(HttpServletResponse response,Object data){
        toJsonData(response,new JsonBuilder.Builder(data).flag(true).code(SUCCESS).writeNull(false).build());
    }
    /**
     * 验证错误返回
     * @param response
     * @param keyValuePair
     */
    public void toWebFail(HttpServletResponse response, KeyValuePair keyValuePair){
        String desc = keyValuePair.getDescr();
        if(StringUtil.isBlank(desc)){
            desc = keyValuePair.getValue();
        }
        toJsonData(response,new JsonBuilder.Builder(null).code(keyValuePair.getKey()).flag(false).desc(desc).build());
    }

    /**
     * 比较全的
     * @param response
     * @param keyValuePair
     */
    public void toWeb(HttpServletResponse response, KeyValuePair keyValuePair,Object data){
        if(keyValuePair == null){
            keyValuePair = new KeyValuePair(0,null,null);
        }
        String desc = keyValuePair.getDescr();
        if(StringUtil.isBlank(desc)){
            desc = keyValuePair.getValue();
        }
        boolean flag = true;
        if(keyValuePair.getKey() != SUCCESS){
            flag = false;
        }
        toJsonData(response,new JsonBuilder.Builder(data).code(keyValuePair.getKey()).flag(flag).desc(desc).build());
    }
    /**
     * 所有web版使用此方法
     * @param response
     * @param jsonBuilder
     */
    private void toJsonData(HttpServletResponse response, JsonBuilder jsonBuilder){
        String encode = "UTF-8";
        if(jsonBuilder.getEncode()!=null){
            encode = jsonBuilder.getEncode();
        }
        resWrite(response, jsonBuilder, encode, config);
    }

    private void resWrite(HttpServletResponse response, JsonBuilder jsonBuilder, String encode, SerializeConfig highChartsConfig) {
        try {
            byte[] bytes;
            if (StringUtil.isBlank(jsonBuilder.getCallback())) {
                if (jsonBuilder.getWriteNull() != null && jsonBuilder.getWriteNull()) {
                    bytes = JSON.toJSONString(jsonBuilder, highChartsConfig, features).getBytes(encode);
                } else {
                    bytes = JSON.toJSONString(jsonBuilder, highChartsConfig, simpleFeatures).getBytes(encode);
                }
            } else {
                if (jsonBuilder.getWriteNull() != null && jsonBuilder.getWriteNull()) {
                    bytes = ("/**/;" + jsonBuilder.getCallback() + "(" + JSON.toJSONString(jsonBuilder, highChartsConfig, features) + ")").getBytes(encode);
                } else {
                    bytes = ("/**/;" + jsonBuilder.getCallback() + "(" + JSON.toJSONString(jsonBuilder, highChartsConfig, simpleFeatures) + ")").getBytes(encode);
                }
            }
            response.setCharacterEncoding(encode);
            response.setContentType("text/json;charset=" + encode);
            response.setContentLength(bytes.length);
            OutputStream writer = response.getOutputStream();
            writer.write(bytes);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片流输出
     * @param response
     * @param imgUrl 图片url
     */
    public void toImge(HttpServletResponse response,String imgUrl){
        if(StringUtil.isBlank(imgUrl)){
            return;
        }
        String sufix = ImgeUtil.suffix(imgUrl);
        BufferedImage bi = ImgeUtil.requestRemoteImg(imgUrl);
        //1.设置响应头通知浏览器以图片的形式打开
        response.setContentType("image/jpeg");//等同于response.setHeader("Content-Type", "image/jpeg");
        //2.设置响应头控制浏览器不要缓存
/*        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");*/
        //3.将图片写给浏览器
        try {
            ImageIO.write(bi, sufix, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件流输出
     * @param response
     * @param targetFilePath 文件地址
     */
    public void toFile(HttpServletResponse response,String targetFilePath){
        File file = new File(targetFilePath);
        toFile(response,file);
    }
    /**
     * 文件流输出
     * @param response
     * @param file 文件
     */
    public void toFile(HttpServletResponse response, File file) {
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(file);
            int len = 0;
            byte[] buffer = new byte[256];
            out = response.getOutputStream();
            while((len = in.read(buffer)) > 0) {
                out.write(buffer,0,len);
            }
        }catch(Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(in != null) {
                try {
                    in.close();
                }catch(Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
