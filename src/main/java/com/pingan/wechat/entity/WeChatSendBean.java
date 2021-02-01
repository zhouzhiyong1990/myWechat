package com.pingan.wechat.entity;

import lombok.Data;

import java.util.Map;

/**
 * @author zhouzhiong@beyondsoft.com
 * @version 1.0
 * @date 2021/1/13 9:07
 */
@Data
public class WeChatSendBean {

    /**
     * 接收者openid
     */
    private String touser;

    /**
     * 模板ID
     */
    private String template_id;

    /**
     * 模板跳转链接（海外帐号没有跳转能力）
     */
    private String url;

    /**
     * 跳小程序所需数据，不需跳小程序可不用传该数据
     */
    private Map<String, String> miniprogram;

    /**
     * 模板数据
     */
    private Map<String, Object> data;
}
