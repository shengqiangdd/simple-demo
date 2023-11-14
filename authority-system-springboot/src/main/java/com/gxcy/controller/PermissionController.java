package com.gxcy.controller;


import com.gxcy.entity.Permission;
import com.gxcy.service.PermissionService;
import com.gxcy.utls.Result;
import com.gxcy.vo.PermissionQueryVo;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "权限控制器")
@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    /**
     * 查询菜单列表
     * @param permissionQueryVo
     * @return
     */
    @GetMapping("/list")
    public Result getMenuList(PermissionQueryVo permissionQueryVo) {
        //查询菜单列表
        List<Permission> permissionList = permissionService.findPermissionList(permissionQueryVo);
        //返回数据
        return Result.ok(permissionList);
    }

    /**
     * 查询上级菜单列表
     * @return
     */
    @GetMapping("/parent/list")
    public Result getParentList() {
        //查询上级菜单列表
        List<Permission> permissionList = permissionService.findParentPermissionList();
        //返回数据
        return Result.ok(permissionList);
    }

    /**
     * 根据id查询菜单信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getMenuById(@PathVariable Long id) {
        return Result.ok(permissionService.getById(id));
    }

    /**
     * 添加菜单
     * @param permission
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:menu:add')")
    public Result add(@RequestBody Permission permission) {
        if(permissionService.save(permission)) {
            return Result.ok().message("菜单添加成功");
        } else {
            return Result.error().message("菜单添加失败");
        }
    }

    /**
     * 修改菜单
     * @param permission
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    public Result update(@RequestBody Permission permission) {
        if(permissionService.updateById(permission)) {
            return Result.ok().message("菜单修改成功");
        } else {
            return Result.error().message("菜单修改失败");
        }
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public Result delete(@PathVariable Long id) {
        if(permissionService.removeById(id)) {
            return Result.ok().message("菜单删除成功");
        } else {
            return Result.error().message("菜单删除失败");
        }
    }

    /**
     * 检查菜单下是否有子菜单
     * @param id
     * @return
     */
    @GetMapping("/check/{id}")
    public Result check(@PathVariable Long id) {
        //判断菜单是否有子菜单
        if(permissionService.hasChildrenOfPermission(id)) {
            return Result.exist().message("该菜单下有子菜单，无法删除");
        }
        return Result.ok();
    }
}
