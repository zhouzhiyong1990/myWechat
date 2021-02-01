//package com.pingan.wechat.util;
//
//import com.alibaba.fastjson.JSONObject;
//import com.pansky.common.util.PropertiesFileUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
///**
// * 延安公共服务接口调用工具类
// */
//public class PublicServicePortUtil {
//    private static Logger _log = LoggerFactory.getLogger(PublicServicePortUtil.class);
////    private static String url = "http://10.190.134.131:9090/pansky/hydx";	//长天提供的服务地址
////    private static String appId = "SLRS_PC"; //长天提供的账户
////    private static String password = "254ee75dfaa3599f";	//长天提供的账户密钥
//    private static String url = PropertiesFileUtil.getInstance("pansky-upms-client").get("pansky.upms.gatway.port.url");
//    private static String appId = PropertiesFileUtil.getInstance("pansky-upms-client").get("pansky.upms.gatway.port.appId"); //长天提供的账户
//    private static String password = PropertiesFileUtil.getInstance("pansky-upms-client").get("pansky.upms.gatway.port.password");;	//长天提供的账户密钥
//
//
//    public static JSONObject getResult(String differentiationPath, String interfaceName, JSONObject json) throws Exception{
//        JSONObject params = new JSONObject();
//        params.put("appId", appId);
//        params.put("interfaceName", interfaceName);
//
//        String sig = AESUtil.encrypt(json.toString(), password);
//
//        params.put("sig", sig);
//        System.out.println(url+differentiationPath+"/"+interfaceName+"?"+params);
//        String result = new String(HttpUtil.post(url+differentiationPath, "params="+params.toString()).getBytes(),"UTF-8");
//        System.out.println(result);
//        //解密
//        JSONObject results = JSONObject.parseObject(result);
//        System.out.println(AESUtil.decrypt(results.getString("msg"), password, "UTF-8"));
//        return  AESUtil.decrypt(results.getString("msg"), password, "UTF-8");
//    }
//
//    /**
//     * 短信发送
//     * @return
//     * @throws Exception
//     */
//    public static JSONObject sendMsg() throws Exception {
//        JSONObject json = new JSONObject();
//        json.put("content", "验证码：123456，请您在5分钟内填写。如非本人操作，请忽略本短信。");//短信内容
//        json.put("mobile", "18602900515");//接受短信的手机号
//        JSONObject jsonstr =getResult(ConstantsCommon.DUANXIN_HYDX,"sendMsg_HY", json);
//        System.out.println(jsonstr);
//        return jsonstr;
//    }
//
//    public static void main(String[]args){
//        try {
//            PublicServicePortUtil.sendMsg();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
