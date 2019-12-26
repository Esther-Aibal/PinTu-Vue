package com.ruoyi.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Aibal.He
 * @date 2019/12/26 1:49 PM
 */
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatConfig {

    public static String appId;
    public static String secret;

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        WechatConfig.appId = appId;
    }

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        WechatConfig.secret = secret;
    }
}
