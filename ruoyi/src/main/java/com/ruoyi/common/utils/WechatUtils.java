package com.ruoyi.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.config.WechatConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aibal.He
 * @date 2019/12/26 1:45 PM
 */
public class WechatUtils {

    public static String getOpenId(String code){
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> params=new HashMap<>();
        params.put("appid", WechatConfig.getAppId());  //
        params.put("secret",WechatConfig.getSecret());
        params.put("code",code);
        params.put("grant_type","authorization_code");
        //https://api.weixin.qq.com/sns/jscode2session?appid=wxe1ec741ded29e43b&secret=2c57a238fb1fcc4937ca5b1318e05df6&js_code=001w3ZgH0pUc6g2mm0eH0jRJgH0w3ZgE&grant_type=authorization_code
        ResponseEntity<String> responseEntity=restTemplate.getForEntity("https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type={grant_type}",String.class,params);
        JSONObject result = JSONObject.parseObject(responseEntity.getBody());
        String id = null;
        if(result.get("openid") ==null){
            id="1";
        }else{
            id =result.get("openid").toString();
        }
        System.out.println(" getInfo from db, id: {}======"+id);
        return id;

    }
}
