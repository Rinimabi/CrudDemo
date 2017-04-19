package com.cruddemo.service;

import com.cruddemo.enity.Department;
public interface DepartmentService {

	/**
	 * 增加部门
	 * 
	 * @param department
	 *            一个包含数据的对象。 返回一个string（success or failure）。
	 */
    public String save(Department department);
	/**
	 * 查询ID为XX的部门
	 * 
	 * @param pageid
	 *            部门的ID（String类型）。查询一个ID=pageid的部门。 返回一个部门类的集合。
	 */
	public Department findById(Integer id);

	/**
	 * 按ID删除部门。
	 * 
	 * @param ID
	 *            部门ID。
	 */
	public String delete(int ID);

	/**
	 * 按ID更新部门
	 * 
	 * @param department
	 *            一个包含数据的部门对象。
	 */
	public String updata(Department department);
}
