package com.eureka.doumi.mq;

import com.alibaba.fastjson.JSON;
import com.doumi.pojo.User;
import com.eureka.doumi.utils.SMSUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageMqConsumer {

    @RabbitListener(queues = "messageQueue")
    public void process(Message message, Channel channel){
        try {
            String content = new String(message.getBody());
            System.out.println(content);
            User user = JSON.parseObject(content,User.class);
            Map<String,String> params = new HashMap<>();
            params.put("name",user.getName());
            boolean result = SMSUtils.sendSms(user.getPhone(),JSON.toJSONString(params));
            if (result){
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }else{
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
