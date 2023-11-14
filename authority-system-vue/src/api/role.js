import http from "@/utils/request";

const apiPrefix = "/api/role";

export default {
  /**
   * 查询角色列表
   * @param param
   */
  async getRoleList(param) {
    return await http.get(`${apiPrefix}/list`, param);
  },
  async getAssignTreeApi(params) {
    return await http.get(`${apiPrefix}/getAssignPermissionTree`, params);
  },
  /**
   * 添加角色
   */
  async addRole(params) {
    return await http.post(`${apiPrefix}/add`, params);
  },
  /**
   * 修改角色
   */
  async updateRole(params) {
    return await http.put(`${apiPrefix}/update`, params);
  },
  /**
   * 删除角色
   */
  async deleteById(params) {
    return await http.delete(`${apiPrefix}/delete`, params);
  },
    /**
   * 分配权限
   */
    async assignSaveApi(params) {
      return await http.post(`${apiPrefix}/saveRoleAssign`, params);
    },
};
