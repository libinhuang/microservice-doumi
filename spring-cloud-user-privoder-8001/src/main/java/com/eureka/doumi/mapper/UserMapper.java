package com.eureka.doumi.mapper;

import com.doumi.pojo.User;
import org.apache.ibatis.annotations.Insert;

public interface UserMapper {

    @Insert("INSERT INTO `users`(username,PASSWORD,phone,email) VALUE(#{name},#{pwd},#{phone},#{email})")
    int addUser(User user);
}
