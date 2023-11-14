package com.gxcy.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenVo {
    @ApiModelProperty("过期时间")
    private Long expireTime;//过期时间

    @ApiModelProperty("token")
    private String token;//token
}
