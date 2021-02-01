package com.pingan.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouzhiong275
 * @version 1.0
 * @date 2021/1/25 16:11
 */
@Controller
@Slf4j
public class WeChatLoginController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.appid}")
    private String appId;
    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.accessTokenUrl}")
    private String accessTokenUrl;
    @Value("${wechat.userInfoUrl}")
    private String userInfoUrl;
    @Value("${wechat.baseUrl}")
    private String baseUrl;

    /**
     * 微信授权登录后的回调
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/redirect")
    public String redirectUrl(String code, String state) {
        log.info("redirectUrl code is {}, state is {}", code, state);
        Map<String, String> params = new HashMap<>();
        params.put("appid", appId);//todo appid明文存储风险
        params.put("secret", secret);//todo app密钥明文存储风险
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        log.info("appid is {}, secret is {}", appId, secret);
        String accessTokenStr = restTemplate.getForObject(accessTokenUrl + "?appid={appid}&secret={secret}&code={code}&grant_type={grant_type}", String.class, params);
        JSONObject accessToken = JSON.parseObject(accessTokenStr);
//        Map accessToken = JSON.parseObject(accessTokenStr, Map.class);
        log.info("--------------------accessToken is {}", accessToken.toString());
        // 如果 接口调用失败，则返回报错页面
        if (null != accessToken.get("errcode")) {
            return "error/error";
        } else {
            String userInfoStr = restTemplate.getForObject(userInfoUrl + "?access_token={access_token}&openid={openid}&lang=zh_CN", String.class, accessToken);
            JSONObject userInfo = JSON.parseObject(userInfoStr);
            log.info("redirectUrl userInfo is {}", userInfo.toJSONString());
            String returnUrl = String.format("redirect:" + baseUrl + "binding.html?accessToken=%s&openId=%s", accessToken.get("access_token"), accessToken.get("openid"));
            log.info("-----returnUrl is {}", returnUrl);
            return returnUrl;
        }


    }

    @PostMapping("/binding")
    @ResponseBody
    public JSONObject doBinding(String data) {
        JSONObject jsonData = JSON.parseObject(data);
        log.info("jsonObject is {}", jsonData.toJSONString());
        // 1.通过openid查询数据库
        // 2.如果有数据，则说明已经绑定，直接返回信息至首页
        // 3.如果查询不到数据
        //  a.有姓名身份证信息，则更新openid
        //  b.没有姓名身份证号信息，则新增数据
//        JSONObject json = new JSONObject();
//        json.put("AAC002", jsonData.getString("idCard"));
//        json.put("BAZ344", "");
//        json.put("AAZ500","");
//        json.put("AAE011", "");
//        json.put("AAE036", "");
//        try {
//            JSONObject jsonObject = PublicServicePortUtil.getResult(ConstantsCommon.SBK_PREFIX,ConstantsCommon.SBK_SSC108, json);
//            JSONObject dataObject = jsonObject.getJSONObject("datas");
////            String code = dataObject.getString("BAZ346");
//            log.info("dataObject is {}", dataObject);
//        } catch (Exception e) {
//            log.error("error");
//        }

        return null;
    }

    /**
     * 用户授权之后，微信获取用户信息接口
     * @param accessToken 调用获取accessToken接口获取
     * @param openid 公众号用户唯一id，调用获取accessToken接口获取
     * @return
     */
    @ResponseBody
    @GetMapping("/userInfo")
    public JSONObject getWeChatUserInfo(@RequestParam String accessToken, @RequestParam String openid) {
        String userInfoUrl = String.format(this.userInfoUrl + "?access_token=%s&openid=%s&lang=zh_CN", accessToken, openid);
        String userInfoStr = restTemplate.getForObject(userInfoUrl, String.class);
        JSONObject userInfo = JSON.parseObject(userInfoStr);
        log.info("redirectUrl userInfo is {}", userInfo.toJSONString());
        return userInfo;
    }
}
