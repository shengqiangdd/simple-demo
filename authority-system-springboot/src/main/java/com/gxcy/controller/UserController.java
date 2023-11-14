package com.gxcy.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxcy.config.exception.CustomerAuthenticationException;
import com.gxcy.config.redis.RedisService;
import com.gxcy.config.security.handler.LoginFailureHandler;
import com.gxcy.config.security.handler.LoginSuccessHandler;
import com.gxcy.config.security.service.CustomerUserDetailService;
import com.gxcy.dto.UserRoleDTO;
import com.gxcy.entity.Permission;
import com.gxcy.entity.Role;
import com.gxcy.entity.User;
import com.gxcy.entity.UserInfo;
import com.gxcy.service.BaseService;
import com.gxcy.service.RoleService;
import com.gxcy.service.UserService;
import com.gxcy.utls.JwtUtils;
import com.gxcy.utls.MenuTree;
import com.gxcy.utls.Result;
import com.gxcy.vo.*;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Api(tags = "用户登录")
@RestController
@RequestMapping("/api/sysUser")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private CustomerUserDetailService userDetailService;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisService redisService;
    @Resource
    private BaseService baseService;
    @Resource
    private RoleService roleService;

    /**
     * 查询用户列表
     * @param userQueryVo
     * @return
     */
    @GetMapping("/list")
    public Result list(UserQueryVo userQueryVo) {
        //创建分页对象
        IPage<User> page = new Page<>(userQueryVo.getPageNo(),userQueryVo.getPageSize());
        //调用分页查询方法
        userService.findUserListByPage(page,userQueryVo);
        //返回数据
        return Result.ok(page);
    }

    /**
     * 用户登录
     * @param user 用户
     * @return
     */
    @ApiOperation(value="用户登录",notes="用户登录")
    @PostMapping("/login")
    public void login(@RequestBody LoginVo user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = user.getUsername();//用户名
        String password = user.getPassword();//密码
        //获取用户信息
        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        try {
            if(userDetails == null) {
                throw new CustomerAuthenticationException("用户不存在");
            }
            boolean matches = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
            if (!matches) {
                throw new CustomerAuthenticationException("密码不正确");
            }
            //创建身份验证对象
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,
                            null,userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //设置到Spring Security上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            loginSuccessHandler.onAuthenticationSuccess(request,response,SecurityContextHolder.getContext().getAuthentication());
        } catch (AuthenticationException e) {
            loginFailureHandler.onAuthenticationFailure(request,response,e);
        }
    }

    /**
     * 用户退出
     * @param request
     * @param response
     * @return
     */
    @PostMapping("logout")
    public Result logout(HttpServletRequest request,HttpServletResponse response) {
        //获取token
        String token = request.getParameter("token");
        //如果没有从头部获取token，那么从参数里面获取
        if(ObjectUtils.isEmpty(token)) {
            token = request.getHeader("token");
        }
        //获取用户相关信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            //清空用户信息
            new SecurityContextLogoutHandler().logout(request,response,authentication);
            //清空redis里面的token
            String key = "token_" + token;
            redisService.del(key);
        }
        return Result.ok().message("用户退出成功");
    }

    /**
     * 查询所有用户列表
     * @return
     */
    @ApiOperation(value="获取用户列表",notes="用户登录")
    @GetMapping("/listAll")
    public Result listAll(){
        return Result.ok(userService.list());
    }

    /**
     * 刷新token
     * @param request
     * @return
     */
    @ApiOperation(value="刷新token",notes="刷新token")
    @PostMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request) {
        //从Header中获取前端提交的token
        String token = request.getHeader("token");
        //如果header中没有token，则从参数中获取
        if(ObjectUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        //从Spring Security上下文获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取身份信息
        UserDetails details = (UserDetails) authentication.getPrincipal();
        //重新生成token
        String reToken = "";
        //验证原来的token是否合法
        if(jwtUtils.validateToken(token,details)) {
            //生成新的token
            reToken = jwtUtils.refreshToken(token);
        }
        //获取本次token的到期时间，交给前端做判断
        long expireTime = Jwts.parser().setSigningKey(jwtUtils.getSecret())
                .parseClaimsJws(reToken.replace("jwt_",""))
                .getBody().getExpiration().getTime();
        //清除原来的token信息
        String oldTokenKey = "token_" + token;
        redisService.del(oldTokenKey);
        //存储新的token
        String newTokenKey = "token_" + reToken;
        redisService.set(newTokenKey,reToken, jwtUtils.getExpiration() / 1000);
        //创建TokenVo对象
        TokenVo tokenVo = new TokenVo(expireTime,reToken);
        //返回数据
        return Result.ok(tokenVo).message("token生成成功");
    }

    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation(value="获取用户信息",notes="获取用户信息")
    @GetMapping("/getInfo")
    public Result getInfo() {
        //获取用户信息
        User user = baseService.getCurrentUser();
        //判断用户是否存在
        if(user == null) {
            return Result.error().message("用户信息查询失败");
        }
        //用户权限集合
        List<Permission> permissionList = user.getPermissionList();
        //获取角色权限编码字段
        Object[] roles = permissionList.stream()
                .filter(Objects::nonNull)
                .map(Permission::getCode).toArray();
        //创建用户信息对象
        UserInfo userInfo = new UserInfo(user.getId(),user.getNickName(),
                user.getAvatar(),null,roles);
        //返回数据
        return Result.ok(userInfo).message("用户信息查询成功");
    }

    /**
     * 获取菜单数据
     * @return
     */
    @ApiOperation(value="获取菜单数据",notes="获取菜单数据")
    @GetMapping("/getMenuList")
    public Result getMenuList(){
        //获取用户信息
        User user = baseService.getCurrentUser();
        //获取相应的权限
        List<Permission> permissionList = user.getPermissionList();
        //筛选目录和菜单
        List<Permission> collect = permissionList.stream()
                .filter(item -> item != null && item.getType() != 2)
                .collect(Collectors.toList());
        //生成路由数据
        List<RouterVo> routerList = MenuTree.makeRouter(collect,0L);
        //返回数据
        return Result.ok(routerList).message("菜单数据获取成功");
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:user:add')")
    public Result add(@RequestBody User user) {
        //查询用户
        User item = userService.findUserByUserName(user.getUsername());
        //判断对象是否为空
        if(item != null) {
            return Result.error().message("该登录名称已被使用，请重新输入！");
        }
        //密码加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //调用保存用户信息的方法
        if(userService.save(user)) {
            return Result.ok().message("用户添加成功");
        }
        return Result.error().message("用户添加失败");
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    public Result update(@RequestBody User user) {
        //查询用户
        User item = userService.findUserByUserName(user.getUsername());
        //判断对象是否为空，且查询到的用户ID不等于当前编辑的用户ID，表示该名称被占用
        if(item != null && item.getId() != user.getId()) {
            return Result.error().message("登录名称已被占用！");
        }
        //调用修改用户信息的方法
        if(userService.updateById(user)) {
            return Result.ok().message("用户修改成功");
        }
        return Result.error().message("用户修改失败");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public Result delete(@PathVariable Long id) {
        //调用删除用户信息的方法
        if(userService.deleteById(id)) {
            return Result.ok().message("用户删除成功");
        }
        return Result.error().message("用户删除失败");
    }

    /**
     * 读取分配角色列表
     * @param roleQueryVo
     * @return
     */
    @GetMapping("/getRoleListForAssign")
    @PreAuthorize("hasAuthority('sys:user:assign')")
    public Result getRoleListForAssign(RoleQueryVo roleQueryVo) {
        //创建分页对象
        IPage<Role> page = new Page<>(roleQueryVo.getPageNo(),roleQueryVo.getPageSize());
        //调用查询方法
        roleService.findRoleListByUserId(page,roleQueryVo);
        //返回数据
        return Result.ok(page);
    }

    /**
     * 根据用户ID查询该用户拥有的角色列表
     * @param userId
     * @return
     */
    @GetMapping("/getRoleByUserId/{userId}")
    @PreAuthorize("hasAuthority('sys:user:assign')")
    public Result getRoleByUserId(@PathVariable Long userId) {
        //调用根据用户ID查询该用户拥有的角色ID的方法
        List<Long> roleIds = roleService.findRoleIdByUserId(userId);
        return Result.ok(roleIds);
    }

    /**
     * 分配角色
     * @param userRoleDTO
     * @return
     */
    @PostMapping("/saveUserRole")
    @PreAuthorize("hasAuthority('sys:user:assign')")
    public Result saveUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        if(userService.saveUserRole(userRoleDTO.getUserId(),userRoleDTO.getRoleIds())) {
            return Result.ok().message("角色分配成功");
        }
        return Result.error().message("角色分配失败");
    }
}
