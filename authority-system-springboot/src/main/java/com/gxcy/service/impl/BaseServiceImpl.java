package com.gxcy.service.impl;

import com.gxcy.entity.User;
import com.gxcy.service.BaseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {

    @Override
    public User getCurrentUser() {
        //从Spring Security上下文获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户信息
        return (User) authentication.getPrincipal();
    }
}
