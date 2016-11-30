package com.qq.weixin;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WexinTool {
	private static final Logger logger = LoggerFactory
			.getLogger(WexinTool.class);
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	static {
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
	}

	/**
	 * 微信公众号消息 接入验证
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param token
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp,
			String nonce, String token) {
		String[] strs = new String[] { timestamp, nonce, token };
		Arrays.sort(strs);
		String str = strs[0] + strs[1] + strs[2];
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(str.getBytes("UTF-8"));
			String signatures = byteToHex(crypt.digest());
			if (signature.equals(signatures)) {
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("WeChat", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("WeChat", e);
		}
		return false;
	}

	/**
	 * 签名
	 * 
	 * @param obj
	 * @param key
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String sign(Object obj, String key)
			throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();

		Arrays.sort(fields, new Comparator<Field>() {

			@Override
			public int compare(Field o1, Field o2) {
				char[] char1 = o1.getName().toCharArray();
				char[] char2 = o2.getName().toCharArray();
				for (int i = 0; i < char1.length && i < char2.length; i++) {
					if (char1[i] - char2[i] != 0) {
						return char1[i] - char2[i];
					}
				}
				return char1.length - char2.length;
			}
		});
		StringBuffer buffer = new StringBuffer();
		for (Field field : fields) {
			field.setAccessible(true);
			Object value = field.get(obj);
			if (value != null && !value.toString().trim().equals("")) {
				buffer.append(field.getName());
				buffer.append("=");
				buffer.append(value);
				buffer.append("&");
			}
		}
		buffer.append("key=");
		buffer.append(key);
		return md5(buffer.toString()).toUpperCase();
	}

	/**
	 * md5算法
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		byte[] btInput = str.getBytes();
		// 获得MD5摘要算法的 MessageDigest 对象
		MessageDigest mdInst;
		try {
			mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			StringBuffer buffer = new StringBuffer();
			for (byte b : md)
				buffer.append(String.format("%02X", b));
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("WeChat", e);
		}
		return "";

	}

	/**
	 * 北京时间格式化--yyyyMMddHHmmss
	 * 
	 * @param date
	 * @return
	 */
	public static String getBJTime(Date date) {
		return sdf.format(date);
	}

	/**
	 * 随机数
	 * 
	 * @param count
	 * @return
	 */
	public static String getNonceStr(int count) {
		return org.apache.commons.lang3.RandomStringUtils
				.randomAlphabetic(count);
	}

	public static String getTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	public static Map<String, String> jsApiSign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = getTimestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;
		logger.debug("js api sign={}", string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("WeChat", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("WeChat", e);
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	@SuppressWarnings("deprecation")
	public static String getWebCodeUrl(String APPID, String REDIRECT_URI,
			String SCOPE, String STATE) {
		return String
				.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
						APPID, URLEncoder.encode(REDIRECT_URI), SCOPE, STATE);
	}

}
