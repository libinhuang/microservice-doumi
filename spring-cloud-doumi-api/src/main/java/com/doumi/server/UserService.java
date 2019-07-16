package com.doumi.server;

import com.doumi.pojo.User;
import com.eureka.doumi.utils.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    Response<Boolean> addUser(@RequestBody User user, @RequestParam String code);
}
