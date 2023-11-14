package com.gxcy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@TableName("sys_department")
@ApiModel(value = "Department对象", description = "")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("部门电话")
    private String phone;

    @ApiModelProperty("部门地址")
    private String address;

    @ApiModelProperty("所属部门编号")
    private Long pid;

    @ApiModelProperty("所属部门名称")
    private String parentName;

    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("是否删除(0-未删除 1-已删除)")
    private Integer isDelete;


    @ApiModelProperty("是否展开")
    @TableField(exist = false)
    private Boolean open;

    @ApiModelProperty("子部门")
    @TableField(exist = false)
    private List<Department> children = new ArrayList<>();

}
