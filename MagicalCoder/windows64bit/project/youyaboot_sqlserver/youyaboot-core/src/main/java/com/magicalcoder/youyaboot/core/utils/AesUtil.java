package com.magicalcoder.youyaboot.core.utils;

/**
 * Created by hzwww.magicalcoder.com on 2015/10/16.
 * http://outofmemory.cn/code-snippet/35524/AES-with-javascript-java-csharp-python-or-php
 */
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AesUtil {

    private static final int IV_LEN = 16;
    public static String buildIvSub(String str){
        String template = "0000000000000000";
        if(str==null|| "".equals(str)){
            return template;
        }
        if(str.length()>=IV_LEN){
            return str.substring(0,IV_LEN);
        }
        return str+template.substring(0,IV_LEN-str.length());
    }


    public static void main(String[] args) {

    }

    /**
     *
     * @param source 原始字符串
     * @param password  秘钥 16位
     * @param iv 随机约定的 16位 一般用用户id cookie前16位不足16位补0 等等双方都知道的 但是又会变化的最安全
     * @return
     */
    public static String encrypt(String source,String password,String iv) {
        try {

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = source.getBytes("UTF-8");
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(password.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return new sun.misc.BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("加密异常");
        }
    }

    /**
     *
     * @param aesStr 加密串
     * @param password 秘钥 16位
     * @param iv 随机约定的 16位 一般用用户id cookie前16位不足16位补0 等等双方都知道的 但是又会变化的最安全
     * @return
     */
    public static String desEncrypt(String aesStr,String password,String iv) {
        try {
            byte[] encrypted1 = new sun.misc.BASE64Decoder().decodeBuffer(aesStr);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(password.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("解密异常");
        }
    }
    // 抄抄抄, https://blog.csdn.net/hbcui1984/article/details/5201247
    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
     */
    public static String encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //防止linux下 随机生成key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(password.getBytes("utf-8"));
            kgen.init(128, secureRandom);
           // kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return new sun.misc.BASE64Encoder().encode(result);
            //return result; // 加密
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | UnsupportedEncodingException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**解密
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static String decrypt(String content, String password) {
        try {
            byte[] encrypted = new sun.misc.BASE64Decoder().decodeBuffer(content);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            /***这段很关键，因为windows和linux这段random生成策略不一样，所有这里要指定一致***/
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes("utf-8"));
             /*****/
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(encrypted);
            return new String(result); // 加密
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
