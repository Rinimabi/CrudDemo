package com.cruddemo.service;
import java.util.List;

import javax.sql.DataSource;

import com.cruddemo.enity.Tree;

public interface TreeService {
	/**
	 * ����JDBCģ��ӳ��
	 * 
	 * @param ds
	 *            ӳ����Դ
	 */
	public void setDatasource(DataSource ds);

	/**
	 * �����ڵ��ѯչ������ӽڵ㲿����Ϣ
	 * 
	 * @param treenode
	 *            ���ڵ�ID������ID��
	 */
	public List<Tree> allDepartmentTree(String treenode);

	/**
	 * ��ѯ������Ա��Ϣ
	 */
	public List<Tree> allPersonTree();

	/**
	 * �����ڵ��ѯչ���������Ա������Ϣ
	 * 
	 * @param treenode
	 *            ���ڵ�ID������ID��
	 */
	public List<Tree> allPersonTree(String treenode);

	/**
	 * �����Ա����������
	 * 
	 * @param personid
	 *            ��ԱID
	 * @param personname
	 *            ��Ա����
	 * @param departmentid
	 *            ����ID
	 */
	public String InputConnect(String personid, String personname, String departmentid);

	/**
	 * �Ƴ���Ա����������
	 * 
	 * @param personid
	 *            ��ԱID
	 * @param personname
	 *            ��Ա����
	 * @param departmentid
	 *            ����ID
	 */
	public String RemoveConnect(String personid, String departmentid);

	/**
	 * ͨ�������������ź���Ա
	 * 
	 * @param searchname
	 *            Ҫ����������
	 */
	public List<Tree> allTreeBySearch(String searchname);

	/**
	 * ͨ������������Ա
	 * 
	 * @param personname
	 *            Ҫ��������Ա����
	 */
	public List<Tree> SeachPerson(String personname);
}
