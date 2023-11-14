import http from "@/utils/request";

const apiPrefix = "/api/department";

export default {
  /**
   * 查询部门列表
   * @param param
   */
  async getDepartmentList(param) {
    return await http.get(`${apiPrefix}/list`, param);
  },
  /**
   * 获取所属部门列表
   */
  async getParentTreeList() {
    return await http.get(`${apiPrefix}/parent/list`);
  },
  /**
   * 添加部门
   */
  async addDept(params) {
    return await http.post(`${apiPrefix}/add`, params);
  },
  /**
   * 修改部门
   */
  async updateDept(params) {
    return await http.put(`${apiPrefix}/update`,params)
  },
  /**
   * 检查部门下是否存在子部门
   */
  async checkDepartment(params) {
    return await http.getRestApi(`${apiPrefix}/check`,params);
  },
  /**
   * 删除部门
   */
  async deleteById(params) {
    return await http.delete(`${apiPrefix}/delete`,params)
  }
};
