package com.eureka.doumi.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

//自定义ES配置类
@Configuration
@PropertySource("classpath:/es.properties")
public class EsConfig {

    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    //配置es的ip地址
    @Value("${es.host}")
    private String host;

    //配置es的端口号
    @Value("${es.port}")
    private int port;

    //创建es客户端
    @Bean(name = "transportClient")
    public TransportClient getClient(){
        System.out.println(host+"===============>"+port);
        TransportClient transportClient = null;
        try {
            /*new InetSocketTransportAddress(InetAddress.getByName(host),port)*/
            transportClient=new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host),port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return transportClient;
    }
}
