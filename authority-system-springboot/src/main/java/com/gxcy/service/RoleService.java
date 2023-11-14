package com.gxcy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxcy.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxcy.vo.RoleQueryVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface RoleService extends IService<Role> {
    /**
     * 根据用户查询角色列表
     * @param page
     * @param roleQueryVo
     * @return
     */
    IPage<Role> findRoleListByUserId(IPage<Role> page, RoleQueryVo roleQueryVo);

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean saveRolePermission(Long roleId, List<Long> permissionIds);

    /**
     * 查询角色是否有用户绑定
     * @param roleId
     * @return
     */
    boolean hasUserOfRole(Long roleId);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    void deleteRolePermission(Long roleId);

    /**
     * 根据用户ID查询该用户拥有的角色ID
     * @param userId
     * @return
     */
    List<Long> findRoleIdByUserId(Long userId);
}
