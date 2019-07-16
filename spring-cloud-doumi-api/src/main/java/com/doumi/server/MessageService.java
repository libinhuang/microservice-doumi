package com.doumi.server;

import com.eureka.doumi.utils.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface MessageService {

    @GetMapping("/sendSms/{phone}")
    Response<Boolean> sendSms(@PathVariable String phone);
}
