package com.eureka.doumi.user.fallback;

import com.doumi.pojo.User;
import com.eureka.doumi.user.feign.UserFeginClient;
import com.eureka.doumi.utils.Response;
import com.eureka.doumi.utils.ResponseApi;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallBack<T> extends ResponseApi<T> implements UserFeginClient {
    @Override
    public Response<Boolean> addUser(User user, String code) {
        return setWait(null);
    }
}
