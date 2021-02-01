package com.pingan.wechat.util;

import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public class AESUtil {
	// /** 算法/模式/填充 **/
	private static final String CipherMode = "AES/CBC/PKCS5Padding";
	
	// /** 解密字节数组 **/
		public static byte[] decrypt(byte[] content, String password, String iv) {
			try {
				SecretKeySpec key = createKey(password);
				Cipher cipher = Cipher.getInstance(CipherMode);
				cipher.init(Cipher.DECRYPT_MODE, key, createIV(iv));
				byte[] result = cipher.doFinal(content);
				return result;
			} catch (Exception e) {
				return null;
			}
		}

		// /** 解密 **/
		public static JSONObject decrypt(String content, String password, String iv) {
			JSONObject result = new JSONObject();
			result.put("isPass", false);
			byte[] data = null;
			
			try {
				data = new Base64().decode(content);// 先用base64解密
				data = decrypt(data, password, iv);
				if (null == data) {
					result.put("isPass", false);
					return result;
				}
				String results = null;
				results = new String(data, "UTF-8");
				result.put("isPass", true);
				result.put("datas", results);
			} catch (Exception e) {
				result.put("isPass", false);
			}
			return result;
		}

	/**
	 * 
	 * @param content 业务数据
	 * @param password 密钥
	 * @return 返回密文
	 */
	public static String encrypt(String content, String password) {
		byte[] data = null;
		try {
			data = content.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		data = encrypt(data, password, "UTF-8");
		String result = new Base64().encode(data);
		return result;
	}

	public static byte[] encrypt(byte[] content, String password, String iv) {
		try {
			SecretKeySpec key = createKey(password);
			Cipher cipher = Cipher.getInstance(CipherMode);
			cipher.init(Cipher.ENCRYPT_MODE, key, createIV(iv));
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static SecretKeySpec createKey(String key) {
		byte[] data = null;
		if (key == null) {
			key = "";
		}
		StringBuffer sb = new StringBuffer(16);
		sb.append(key);
		while (sb.length() < 16) {
			sb.append("0");
		}
		if (sb.length() > 16) {
			sb.setLength(16);
		}

		try {
			data = sb.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new SecretKeySpec(data, "AES");
	}
	
	private static IvParameterSpec createIV(String password) {
		byte[] data = null;
		if (password == null) {
			password = "";
		}
		StringBuffer sb = new StringBuffer(16);
		sb.append(password);
		while (sb.length() < 16) {
			sb.append("0");
		}
		if (sb.length() > 16) {
			sb.setLength(16);
		}

		try {
			data = sb.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new IvParameterSpec(data);
	}

	


	public static void main(String[] args) {
		System.out.println(decrypt("cxnedXj407ExI/61w1/So2Zy7XtX95230nbTtmPSpRO3dV1wFb0ErPbvTc1iJo1+A0O4Gv9eZ6l3uAOOjJDMYA==", "pansky", "UTF-8"));
	}
}
