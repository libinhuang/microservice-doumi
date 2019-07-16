package com.eureka.doumi.impl;

import com.alibaba.fastjson.JSON;
import com.doumi.pojo.User;
import com.doumi.server.UserService;
import com.eureka.doumi.mapper.UserMapper;
import com.eureka.doumi.utils.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl<T> extends ResponseApi<T> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Response<Boolean> addUser(User user, String code) {
        //判断用户对象手机号码不能为空
        if(EmptyUtils.isNotEmpty(user)&&EmptyUtils.isNotEmpty(user.getPhone())){
            //根据手机号码在redis中查询验证码
            Object value= RedisUtils.get(user.getPhone());
            //判断是否查询到验证
            if(value!=null && value.toString().equals(code)){
                //密码使用md5 3次加密
                user.setPwd(SecurityUtils.md5Hex3(user.getPwd()));
                //将用户添加到数据库
                int count = userMapper.addUser(user);
                //判断是否成功
                if(count>0){
                    //将用户信息写入队列
                    CorrelationData correlationData = new CorrelationData(StringUtils.randomUUID());
                    rabbitTemplate.convertAndSend("fanoutExchange","", JSON.toJSONString(user),correlationData);
                    System.out.println("注册成功");
                    return setSuccess(true);
                }
            }
        }
        return setError(false);
    }
}
