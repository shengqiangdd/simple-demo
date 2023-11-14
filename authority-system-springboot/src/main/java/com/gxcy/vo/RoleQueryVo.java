package com.gxcy.vo;

import com.gxcy.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleQueryVo extends Role {
    @ApiModelProperty("当前页码")
    private Long pageNo = 1L;//当前页码
    @ApiModelProperty("每页显示数量")
    private Long pageSize = 10L;//每页显示数量
    @ApiModelProperty("用户ID")
    private Long userId;//用户ID
}
