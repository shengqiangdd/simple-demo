package com.gxcy.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gxcy.config.redis.RedisService;
import com.gxcy.entity.User;
import com.gxcy.utls.JwtUtils;
import com.gxcy.utls.LoginResult;
import com.gxcy.utls.ResultCode;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 登录认证成功处理类
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
    HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //设置客户端的响应内容类型
        response.setContentType("application/json;charset=utf-8");
        //获取当前登录用户信息
        User user = (User) authentication.getPrincipal();
        //生成token
        String token = jwtUtils.generateToken(user);
        //设置token签名秘钥及过期时间
        long expireTime = Jwts.parser() //获取DefaultJwtParser对象
                .setSigningKey(jwtUtils.getSecret()) //设置签名的秘钥
                .parseClaimsJws(token.replace("jwt_",""))
                .getBody().getExpiration().getTime(); //获取token过期时间
        //创建登录对象结果
        LoginResult loginResult = new LoginResult(user.getId(),
                ResultCode.SUCCESS,token,expireTime);
        //把生成的token存到redis
        String tokenKey = "token_" + token;
        redisService.set(tokenKey,token,jwtUtils.getExpiration() / 1000);
        //消除循环引用
        String result = JSON.toJSONString(loginResult,
                SerializerFeature.DisableCircularReferenceDetect);
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
