package com.doumi.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    @ApiModelProperty(name = "用户id")
    private int id;
    @ApiModelProperty(name = "用户名称")
    private String name;
    @ApiModelProperty(name = "用户密码")
    private String pwd;
    @ApiModelProperty(name = "用户电话")
    private String phone;
    @ApiModelProperty(name = "用户邮箱")
    private String email;
}
