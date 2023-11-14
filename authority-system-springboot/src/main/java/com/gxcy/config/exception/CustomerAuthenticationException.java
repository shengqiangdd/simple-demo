package com.gxcy.config.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义验证异常雷
 */
public class CustomerAuthenticationException extends AuthenticationException {

    public CustomerAuthenticationException(String message) {
        super(message);
    }
}
