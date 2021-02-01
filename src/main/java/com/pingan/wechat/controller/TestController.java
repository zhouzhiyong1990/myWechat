package com.pingan.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.pingan.wechat.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author zhouzhiong275
 * @version 1.0
 * @date 2021/1/25 11:31
 */
@Controller
@Slf4j
public class TestController {

    @GetMapping("/test")
    public String test() throws UnsupportedEncodingException {

        log.info("redirect:http://192.168.1.165/test.html");
        System.out.println(URLEncoder.encode("刘备", "UTF-8"));
        return null;

    }

    public static void main(String[] args) {
        JSONObject jsonData = new JSONObject();
        String a = "grant_type=client_credential";
        String b = "appid=wxbbd6917123bbca1b";
        String c = "secret=783e5a090751b6803cf47263d8e15417";
        String post = HttpUtil.post("https://api.weixin.qq.com/cgi-bin/token", a,b,c);
        System.out.println(post);
    }
}
