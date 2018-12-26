package com.magicalcoder.youyaboot.core.okhttp;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.message.BasicNameValuePair;

import javax.net.ssl.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by magicalcoder.com on 2018/2/26.
 */
public class OkHttpsClientUtils {

    private static OkHttpClient okHttpClient = null;

    public static boolean success200(String status) {
        return "200".equals(status);
    }

    /**
     * 获取OkHttpClient
     *
     * @return OkHttpClient
     */
    private static OkHttpClient getUnsafeOkHttpClient() {
        long timeOut = 30;
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);

            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return builder.connectTimeout(timeOut,TimeUnit.SECONDS).readTimeout(timeOut,TimeUnit.SECONDS).writeTimeout(timeOut,TimeUnit.SECONDS).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String[] doGetNoCert(String httpUrl, List<BasicNameValuePair> requestParams) {
        String status = "";
        String returnResponse = "";
        if(okHttpClient == null){
            okHttpClient = getUnsafeOkHttpClient();
        }
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        //创建"调用" 对象
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();//执行
            status = response.code()+"";
//            if (response.isSuccessful()) {
                returnResponse = response.body().string();
//            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "Exception";
        }
        return new String[]{status, returnResponse};
    }

    public static void main(String[] args) {
    }
}
