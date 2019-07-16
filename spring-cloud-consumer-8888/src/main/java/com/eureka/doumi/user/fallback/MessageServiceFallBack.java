package com.eureka.doumi.user.fallback;

import com.eureka.doumi.user.feign.MessageFeignClient;
import com.eureka.doumi.utils.Response;
import com.eureka.doumi.utils.ResponseApi;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceFallBack<T> extends ResponseApi<T> implements MessageFeignClient {

    @Override
    public Response<Boolean> sendSms(String phone) {
        return setWait(false);
    }
}
