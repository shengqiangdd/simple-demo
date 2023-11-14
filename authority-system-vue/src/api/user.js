import http from '@/utils/request'

const apiPrefix = "/api/sysUser";

export default {
    /**
   * 查询用户列表
   * @param params 
   * @returns 
   */
    async getUserList(params) {
      return await http.get(`${apiPrefix}/list`,params);
    },
    /**
     * 添加用户
     * @param params 
     * @returns 
     */
    async addUser(params) {
      return await http.post(`${apiPrefix}/add`,params);
    },
    /**
     * 修改用户
     * @param params 
     * @returns 
     */
    async updateUser(params) {
      return await http.put(`${apiPrefix}/update`,params);
    },
    /**
     * 删除用户
     * @param params 
     * @returns 
     */
    async deleteUser(params) {
      return await http.delete(`${apiPrefix}/delete`,params);
    },
    /**
     * 查询用户角色列表
     * @param params 
     * @returns 
     */
    async getAssignRoleList(params) {
      return await http.get(`${apiPrefix}/getRoleListForAssign`,params);
    },
    /**
     * 获取分配角色列表数据
     * @param params 
     * @returns 
     */
    async getRoleIdByUserId(params) {
      return await http.getRestApi(`${apiPrefix}/getRoleByUserId`,params);
    },
    /**
     * 分配角色
     * @param params 
     * @returns 
     */
    async assignRoleSave(params) {
      return await http.post(`${apiPrefix}/saveUserRole`,params);
    },
}

/**
   * 用户登录
   * @returns 
   */
export async function login(data) {
  return await http.login(`${apiPrefix}/login`,data)
}

/**
 * 获取用户信息和权限信息
 * @returns
 */
export async function getInfo() {
  return await http.get(`${apiPrefix}/getInfo`)
}

/**
 * 退出登录
 * @returns
 */
export async function logout(param) {
  return await http.post(`${apiPrefix}/logout`,param)
}

/**
 * 读取菜单数据
 */
export async function getMenuList() {
  return await http.get(`${apiPrefix}/getMenuList`);
}

/**
 * 刷新token
 */
export async function refreshTokenApi(params) {
  return await http.post(`${apiPrefix}/refreshToken`,params);
}