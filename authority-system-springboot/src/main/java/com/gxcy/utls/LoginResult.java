package com.gxcy.utls;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    @ApiModelProperty("用户编号")
    private Long id;//用户编号
    @ApiModelProperty("状态码")
    private int code;//状态码
    @ApiModelProperty("token令牌")
    private String token;//token令牌
    @ApiModelProperty("token过期时间")
    private Long expireTime;//token过期时间
}
