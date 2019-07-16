package com.eureka.doumi.user.feign;

import com.doumi.server.UserService;
import com.eureka.doumi.user.fallback.UserServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-provider",fallback = UserServiceFallBack.class)
public interface UserFeginClient extends UserService {
}
