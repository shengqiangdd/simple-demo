package com.gxcy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("登录名称(用户名)")
    private String username;

    @ApiModelProperty("登录密码")
    private String password;

    @ApiModelProperty("帐户是否过期(1-未过期，0-已过期)")
    private boolean isAccountNonExpired;

    @ApiModelProperty("帐户是否被锁定(1-未过期，0-已过期)")
    private boolean isAccountNonLocked;

    @ApiModelProperty("密码是否过期(1-未过期，0-已过期)")
    private boolean isCredentialsNonExpired;

    @ApiModelProperty("帐户是否可用(1-可用，0-禁用)")
    private boolean isEnabled;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("所属部门ID")
    private Long departmentId;

    @ApiModelProperty("所属部门名称")
    private String departmentName;

    @ApiModelProperty("性别(0-男，1-女)")
    private Integer gender;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("是否是管理员(1-管理员)")
    private Integer isAdmin;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("是否删除(0-未删除，1-已删除)")
    private Integer isDelete;

    @ApiModelProperty("权限列表")
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;

    @ApiModelProperty("用户权限列表")
    @TableField(exist = false)
    private List<Permission> permissionList;
}
