package com.cruddemo.service;
import java.util.List;

import javax.sql.DataSource;

import com.cruddemo.enity.Tree;

public interface TreeService {
	/**
	 * 设置JDBC模板映射
	 * 
	 * @param ds
	 *            映射资源
	 */
	public void setDatasource(DataSource ds);

	/**
	 * 按树节点查询展开后的子节点部门信息
	 * 
	 * @param treenode
	 *            树节点ID（部门ID）
	 */
	public List<Tree> allDepartmentTree(String treenode);

	/**
	 * 查询所有人员信息
	 */
	public List<Tree> allPersonTree();

	/**
	 * 按树节点查询展开后的子人员部门信息
	 * 
	 * @param treenode
	 *            树节点ID（部门ID）
	 */
	public List<Tree> allPersonTree(String treenode);

	/**
	 * 添加人员（到中立表）
	 * 
	 * @param personid
	 *            人员ID
	 * @param personname
	 *            人员名称
	 * @param departmentid
	 *            部门ID
	 */
	public String InputConnect(String personid, String personname, String departmentid);

	/**
	 * 移除人员（到中立表）
	 * 
	 * @param personid
	 *            人员ID
	 * @param personname
	 *            人员名称
	 * @param departmentid
	 *            部门ID
	 */
	public String RemoveConnect(String personid, String departmentid);

	/**
	 * 通过名字搜索部门和人员
	 * 
	 * @param searchname
	 *            要搜索的名字
	 */
	public List<Tree> allTreeBySearch(String searchname);

	/**
	 * 通过名字搜索人员
	 * 
	 * @param personname
	 *            要搜索的人员名字
	 */
	public List<Tree> SeachPerson(String personname);
}
