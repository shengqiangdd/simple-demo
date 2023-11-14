package com.gxcy.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo {
    @ApiModelProperty("路由地址")
    private String path;//路由地址
    @ApiModelProperty("路由对应的组件")
    private String component;//路由对应的组件
    @ApiModelProperty("是否显示")
    private boolean alwaysShow;//是否显示
    @ApiModelProperty("路由名称")
    private String name;//路由名称
    @ApiModelProperty("路由meta信息")
    private Meta meta;//路由meta信息

    @Data
    @AllArgsConstructor
    public class Meta {
        @ApiModelProperty("标题")
        private String title;//标题
        @ApiModelProperty("图标")
        private String icon;//图标
        @ApiModelProperty("角色列表")
        private Object[] roles;//角色列表
    }

    @ApiModelProperty("子路由")
    private List<RouterVo> children = new ArrayList<>();//子路由
}
