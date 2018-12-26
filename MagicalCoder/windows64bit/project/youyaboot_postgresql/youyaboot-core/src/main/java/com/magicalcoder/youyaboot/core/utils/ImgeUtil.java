package com.magicalcoder.youyaboot.core.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Created by magicalcoder.com.com on 2016/2/23.
 */
public class ImgeUtil {

    //文件后缀
    public static String suffix(String imgUrl){
        if(StringUtil.isNotBlank(imgUrl)){
            int lidx =  imgUrl.lastIndexOf(".");
            return imgUrl.substring(lidx+1);
        }
        return null;
    }

    public static BufferedImage requestRemoteImg(String imgUrl) {
        if(StringUtil.isBlank(imgUrl)){
            return null;
        }
        InputStream returnResponse;
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(imgUrl);
        //针对一般服务器Circular redirect 可以加上.setCircularRedirectsAllowed(true)
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(4000).setConnectTimeout(4000).setCircularRedirectsAllowed(true).build();//设置请求和传输超时时间
        get.setConfig(requestConfig);
        get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:25.0) Gecko/20100101 Firefox/25.0");
        get.addHeader("Content-type", "text/html;charset=UTF-8");
        //创建HttpGet实例
        HttpResponse response;
        try {
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            returnResponse = entity.getContent();
            BufferedImage sourceImg = ImageIO.read(returnResponse);
            EntityUtils.consume(entity);
            return sourceImg;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            get.releaseConnection();
            get.abort();
        }
        return null;
    }
}
