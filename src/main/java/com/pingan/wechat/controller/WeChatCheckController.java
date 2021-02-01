package com.pingan.wechat.controller;

import com.pingan.wechat.util.Decript;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author zhouzhiong@beyondsoft.com
 * @version 1.0
 * @date 2021/1/13 8:41
 */
@RestController
@Slf4j
public class WeChatCheckController {

    /**
     * 开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，
     * 则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
     *
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping("/wechat")
    public String checkWeChat(@RequestParam String signature, @RequestParam String timestamp,
                              @RequestParam String nonce, @RequestParam String echostr) {
        String token = "d41cfb37b29f";
        //排序
        String sortString = sort(token, timestamp, nonce);
        //加密
        String mytoken = Decript.sha1(sortString);
        //校验签名
        if (mytoken != null && mytoken != "" && mytoken.equals(signature)) {
            log.info("签名校验通过。");
            return echostr;
        } else {
            log.info("签名校验失败。");
            return null;
        }

    }

    /**
     * 排序方法
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String sort(String token, String timestamp, String nonce) {
        String[] strArray = { token, timestamp, nonce };
        Arrays.sort(strArray);

        StringBuilder sbuilder = new StringBuilder();
        for (String str : strArray) {
            sbuilder.append(str);
        }

        return sbuilder.toString();
    }
}
