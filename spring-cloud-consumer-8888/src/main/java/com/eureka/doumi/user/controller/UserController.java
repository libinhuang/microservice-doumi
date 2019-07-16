package com.eureka.doumi.user.controller;

import com.doumi.pojo.User;
import com.eureka.doumi.user.feign.MessageFeignClient;
import com.eureka.doumi.user.feign.UserFeginClient;
import com.eureka.doumi.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Api(value = "用户接口")
public class UserController {
    @Autowired
    private UserFeginClient userFeginClient;

    @Autowired
    private MessageFeignClient messageFeignClient;

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user",value = "用户实体",required = true,dataType = "User"),
            @ApiImplicitParam(name = "code",value = "手机验证码",required = true,dataType = "String")
    })
    public Response<Boolean> addUser(User user,String code){
        System.out.println(user.getPwd());
        return userFeginClient.addUser(user,code);
    }

    @GetMapping("/sendSms/{phone}")
    @ApiOperation(value = "获取手机验证码")
    @ApiImplicitParam(name = "phone",value = "手机号码",required = true,dataType = "String",paramType = "path")
    public Response<Boolean> sendSms(@PathVariable String phone){
        return messageFeignClient.sendSms(phone);
    }

    @RequestMapping("/testCors")
    @ApiOperation(value = "测试Cors跨域")
    public String testCors(){
//        System.out.println("测试Cors跨域问题");
//        Map<String,String> map = new HashMap<>();
//        map.put("key1","value1");
//        map.put("key2","value2");
//        return JSON.toJSONString(map);
        return "";
    }
}
