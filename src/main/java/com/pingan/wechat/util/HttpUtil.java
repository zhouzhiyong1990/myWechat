package com.pingan.wechat.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Http 访问工具类
 * @author 
 *
 */
public class HttpUtil {
	

    /**
     * POST请求 默认是 UTF-8 编码
     * post("http://xxx.com/search", "a=bcd", "b=xxx");
     * @param url
     * @param params 请单个编写，内部会自动将等号后字符编码
     * @return
     */
    public static String post(String url, String... params){
		return post(url, Charset.forName("UTF-8"), params);
	}

    /**
     * POST请求 默认是 UTF-8 编码
     * post("http://xxx.com/search", "a=bcd", "b=xxx");
     * @param url
     * @param params 请单个编写，内部会自动将等号后字符编码
     * @return
     */
	public static String post(String url, Charset charset, String... params) {
		PrintWriter out = null;
		InputStream in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();

			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(300000);

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			out = new PrintWriter(conn.getOutputStream());
			out.print(encodeParams(charset, params));
			out.flush();

			in = conn.getInputStream();
			result = readInputStream(charset, in);

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				try {
					if (out != null) {
						out.close();
					}
				} finally {
					if (in != null) {
						in.close();
					}
				}
			} catch (Exception ex) {
			}
		}
		return result;
	}
	
	
	private static String readInputStream(Charset charset, InputStream in)
			throws IOException {
		byte[] bytes = new byte[1024];
		int length = -1;
		ByteArrayOutputStream byteOutput= new ByteArrayOutputStream();
		while ((length = in.read(bytes)) != -1) {
			byteOutput.write(bytes, 0, length);
		}
		return new String(byteOutput.toByteArray(), charset);
	}
	
    public static String encodeParams(String... params){
        return encodeParams(Charset.forName("UTF-8"), params);
    }

    /**
     * 将等号后面的字符串编码
     * @param params
     * @return
     */
    private static String encodeParams(Charset charset, String... params){
        if(params == null || params.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for(String str : params) {
            int index = str.indexOf("=");
            if(index == -1) {
                sb.append(str);
            }else {
                try {
                    sb.append(str.substring(0, index)).append("=").append(URLEncoder.encode(str.substring(index + 1), charset.name()));
                } catch (UnsupportedEncodingException e) {//肯定不会出现
                }
            }

            sb.append("&");
        }
        if(sb.length() > 0) {
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

}
