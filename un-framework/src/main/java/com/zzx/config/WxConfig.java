package com.zzx.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: UNMinProgram
 * @Description 小程序的配置类
 * @Author: 那个小楠瓜
 * @create: 2022-03-03 11:56
 **/
@Data
@Configuration
@Component
public class WxConfig {

    /**
     * 请求的网址
     */
    @Value("${wx.url}")
    private String url;
    /**
     * 小程序的appId
     */
    @Value("${wx.appId}")
    private String appid;
    /**
     * 小程序的secret
     */
    @Value("${wx.secret}")
    private String secret;
    /**
     * 固定参数
     */
    @Value("${wx.grantType}")
    private String grantType;
}


