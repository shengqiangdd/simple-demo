package com.gxcy.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    @ApiModelProperty("用户ID")
    private Long id;//用户ID
    @ApiModelProperty("用户名称")
    private String name;//用户名称
    @ApiModelProperty("头像")
    private String avatar;//头像
    @ApiModelProperty("介绍")
    private String introduction;//介绍
    @ApiModelProperty("角色权限集合")
    private Object[] roles;//角色权限集合
}
