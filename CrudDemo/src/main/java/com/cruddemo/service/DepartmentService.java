package com.cruddemo.service;

import com.cruddemo.enity.Department;
public interface DepartmentService {

	/**
	 * ���Ӳ���
	 * 
	 * @param department
	 *            һ���������ݵĶ��� ����һ��string��success or failure����
	 */
    public String save(Department department);
	/**
	 * ��ѯIDΪXX�Ĳ���
	 * 
	 * @param pageid
	 *            ���ŵ�ID��String���ͣ�����ѯһ��ID=pageid�Ĳ��š� ����һ��������ļ��ϡ�
	 */
	public Department findById(Integer id);

	/**
	 * ��IDɾ�����š�
	 * 
	 * @param ID
	 *            ����ID��
	 */
	public String delete(int ID);

	/**
	 * ��ID���²���
	 * 
	 * @param department
	 *            һ���������ݵĲ��Ŷ���
	 */
	public String updata(Department department);
}
