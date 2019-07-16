package com.eureka.doumi.impl;

import com.alibaba.fastjson.JSON;
import com.doumi.server.MessageService;
import com.eureka.doumi.utils.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MessageServiceImpl<T> extends ResponseApi<T> implements MessageService {
    @Override
    public Response<Boolean> sendSms(String phone) {
        //正则表达式验证手机号码是否正确
        if(RegUtils.checkMobile(phone)){
            //获取6位随机验证码
            String code = StringUtils.randomNumbers(6);
            //创建map集合
            Map<String,String> map = new HashMap<>();
            //将验证码存入map集合
            map.put("code",code);
            //发送验证码
            boolean result = SMSUtils.sendSms(phone, JSON.toJSONString(map));
            System.out.println(result);
            if (result){
                //将验证码放入redis(手机号码为key,验证码为value)
                RedisUtils.set(phone,code);
                //设置key过期时间
                RedisUtils.expire(phone,300);
            }
            return setSuccess(result);
        }
        return setError(false);
    }
}
