package com.pingan.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.pingan.wechat.entity.WeChatSendBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@RestController
@Slf4j
public class WeChatMessageController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 微信发送模板消息接口
     * 1.微信通过如下get接口获取access_token
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxbbd6917123bbca1b&secret=783e5a090751b6803cf47263d8e15417
     */
    private static final String SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" +
            "41_qew65R4gy783p9VAH9kca1M2GvEhlc4wqBdj6dO6KPuuNEbiw9gTwaBUbShpX0gauvoPl-AZ7ZiFBweo5MYhz3jLdW2LomMK2sVm98kOSBl4l-ghiJD1QX-weH6hzAbeY-V0_YQapnc4ucV1ESUjAGALZB";

    /**
     * 2.微信发送模板消息接口
     * 用户办事儿成功后为用户推送通知消息
     * @param weChatSendBean
     */
    @PostMapping("/wechat/message/send")
    public void sendMessage(@RequestBody WeChatSendBean weChatSendBean) {
        String jsonString = JSON.toJSONString(weChatSendBean);
        log.info("jsonString is {}", jsonString);
        HttpEntity<String> entity = new HttpEntity<>(jsonString);
        Map resultMap = restTemplate.postForObject(SEND_URL, entity, Map.class);
        log.info("--------------------resultMap is {}", resultMap);
    }
}
