/**
 *
 */
package com.magicalcoder.youyaboot.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 *
 * @author www.magicalcoder.com
 * @time 2017年5月27日-下午1:19:44
 */
public class Md5Util {

	private Md5Util() {}

	private static Logger logger = LoggerFactory.getLogger(Md5Util.class);

	/** 16进制的字符数组 */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

    public static void main(String[] args) {
        System.out.println(md5Encode("12345678he 单独9012345678901234567890"));
    }

	/**
	 *
	 *
	 * @param source
	 *            需要加密的原字符串
	 * @return
	 */
	public static String md5Encode(String source) {
		try {
			return md5Encode(source.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return md5Encode(source.getBytes());
		}
	}
	public static String md5Encode(byte[] source) {
		String result = null;
		try {
			// 获得MD5摘要对象
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组更新摘要信息
			messageDigest.update(source);
			// messageDigest.digest()获得16位长度
			result = byteArrayToHexString(messageDigest.digest());
		} catch (Exception e) {
			logger.error("Md5Utils Exception!", e);
		}
		return result;
	}

	/**
	 * 转换字节数组为16进制字符串
	 *
	 * @param bytes
	 *            字节数组
	 * @return
	 */
	private static String byteArrayToHexString(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte tem : bytes) {
			stringBuilder.append(byteToHexString(tem));
		}
		return stringBuilder.toString();
	}

	/**
	 * 转换byte到16进制
	 *
	 * @param b
	 *            要转换的byte
	 * @return 16进制对应的字符
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

}
