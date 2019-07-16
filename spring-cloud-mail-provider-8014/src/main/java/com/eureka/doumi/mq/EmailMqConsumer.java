package com.eureka.doumi.mq;

import com.alibaba.fastjson.JSON;
import com.doumi.pojo.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class EmailMqConsumer {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @RabbitListener(queues = "emailQueue")
    public void process(Message message, Channel channel){
        try {
            String content = new String(message.getBody());
            System.out.println("emailQueue====>"+content);
            User user = JSON.parseObject(content,User.class);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject("恭喜你,注册成功");
            simpleMailMessage.setText("aaaaaaaaaaaaaaaaaaaaa");
            javaMailSender.send(simpleMailMessage);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
