package com.gxcy.service;

import com.gxcy.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxcy.vo.PermissionQueryVo;
import com.gxcy.vo.RolePermissionVo;

import java.util.List;


public interface PermissionService extends IService<Permission> {
    /**
     * 根虎用户名ID查询权限列表
     * @param userId
     * @return
     */
    List<Permission> findPermissionListByUserId(Long userId);

    /**
     * 查询菜单列表
     * @param permissionQueryVo
     * @return
     */
    List<Permission> findPermissionList(PermissionQueryVo permissionQueryVo);

    /**
     * 查询上级菜单列表
     * @return
     */
    List<Permission> findParentPermissionList();

    /**
     * 检查菜单是否有子菜单
     * @param id
     * @return
     */
    boolean hasChildrenOfPermission(Long id);

    /**
     * 查询分配权限树列表
     * @param userId
     * @param roleId
     * @return
     */
    RolePermissionVo findPermissionTree(Long userId,Long roleId);
}
