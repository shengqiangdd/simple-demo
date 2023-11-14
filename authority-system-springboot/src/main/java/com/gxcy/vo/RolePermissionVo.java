package com.gxcy.vo;

import com.gxcy.entity.Permission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RolePermissionVo{
    @ApiModelProperty("菜单数据")
    private List<Permission> permissionList = new ArrayList<>();
    @ApiModelProperty("该角色原有分配的菜单数据")
    private Object[] checkList;
}
