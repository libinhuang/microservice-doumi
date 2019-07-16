package com.eureka.doumi.user.feign;

import com.doumi.server.MessageService;
import com.eureka.doumi.user.fallback.MessageServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "message-provider",fallback = MessageServiceFallBack.class)
public interface MessageFeignClient extends MessageService {
}
