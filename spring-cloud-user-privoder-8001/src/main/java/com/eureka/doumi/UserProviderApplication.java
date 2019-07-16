package com.eureka.doumi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.eureka.doumi.mapper")
public class UserProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class,args);
    }
}
