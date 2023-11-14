package com.gxcy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@TableName("sys_permission")
@ApiModel(value = "Permission对象", description = "")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("权限名称")
    private String label;

    @ApiModelProperty("父权限ID")
    private Long parentId;

    @ApiModelProperty("父权限名称")
    private String parentName;

    @ApiModelProperty("授权标识符")
    private String code;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("路由名称")
    private String name;

    @ApiModelProperty("授权路径")
    private String url;

    @ApiModelProperty("权限类型(0-目录 1-菜单 2-按钮)")
    private Integer type;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("是否删除(0-未删除，1-已删除)")
    private Integer isDelete;

    @ApiModelProperty("子菜单列表")
    @JsonInclude(JsonInclude.Include.NON_NULL) //属性值为null不进行序列化操作
    @TableField(exist = false)
    private List<Permission> children = new ArrayList<>();

    @ApiModelProperty("用于前端判断是菜单、目录或按钮")
    @TableField(exist = false)
    private String value;

    @ApiModelProperty("是否展开")
    @TableField(exist = false)
    private Boolean open;
}
