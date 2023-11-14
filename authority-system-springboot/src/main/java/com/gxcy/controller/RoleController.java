package com.gxcy.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxcy.entity.Role;
import com.gxcy.service.PermissionService;
import com.gxcy.service.RoleService;
import com.gxcy.utls.Result;
import com.gxcy.dto.RolePermissionDTO;
import com.gxcy.vo.RolePermissionVo;
import com.gxcy.vo.RoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Api(tags = "角色控制器")
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    /**
     * 分页查询角色列表
     * @param roleQueryVo
     * @return
     */
    @GetMapping("/list")
    public Result list(RoleQueryVo roleQueryVo) {
        //创建分页对象
        IPage<Role> page = new Page<>(roleQueryVo.getPageNo(),roleQueryVo.getPageSize());
        //调用分页查询方法
        roleService.findRoleListByUserId(page,roleQueryVo);
        //返回数据
        return Result.ok(page);
    }

    /**
     * 分配权限-查询权限树数据
     * @param userId
     * @param roleId
     * @return
     */
    @GetMapping("/getAssignPermissionTree")
    public Result getAssignPermissionTree(Long userId,Long roleId) {
        //调用查询权限树数据的方法
        RolePermissionVo permissionTree = permissionService.findPermissionTree(userId, roleId);
        //返回数据
        return Result.ok(permissionTree);
    }

    /**
     * 分配权限-保存权限数据
     * @param rolePermissionDTO
     * @return
     */
    @PostMapping("/saveRoleAssign")
    public Result saveRoleAssign(@RequestBody RolePermissionDTO rolePermissionDTO) {
        if(roleService.saveRolePermission(rolePermissionDTO.getRoleId(),rolePermissionDTO.getList())) {
            return Result.ok().message("权限分配成功");
        } else {
            return Result.error().message("权限分配失败");
        }
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:role:add')")
    public Result add(@RequestBody Role role) {
        if(roleService.save(role)) {
            return Result.ok().message("角色添加成功");
        } else {
            return Result.error().message("角色添加失败");
        }
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    public Result update(@RequestBody Role role) {
        if(roleService.updateById(role)) {
            return Result.ok().message("角色修改成功");
        } else {
            return Result.error().message("角色修改失败");
        }
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public Result delete(@PathVariable Long id) {
        if(roleService.hasUserOfRole(id)) {
            return Result.error().message("该角色已被其他用户绑定，不可删除");
        } else {
            //删除角色
            if (roleService.removeById(id)) {
                //删除角色相关权限
                roleService.deleteRolePermission(id);
                return Result.ok().message("角色删除成功");
            }
        }
        return Result.error().message("角色删除失败");
    }
}
