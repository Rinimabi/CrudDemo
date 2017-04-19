package com.cruddemo.service;
import java.util.List;


import com.cruddemo.enity.Persons;

public interface PersonsService {

	/**
	 * ��ID��ѯ�����¼���Ա
	 * 
	 * @param pageid
	 *            ����ID
	 */
	public List<Persons> fineAllPersons(String pageid);

	/**
	 * ��ѯһҳ��Ա
	 * 
	 * @param pagenumber
	 *            ����ҳ��
	 * @param pagesize
	 *            ��һҳ������
	 * @param pageid
	 *            ��ԱID
	 */
	public List<Persons> finePagePersons(int pagenumber, int pagesize, String pageid);

	/**
	 * ���������ֲ�ѯ��Ա
	 * 
	 * @param pagenumber
	 *            ����ҳ��
	 * @param pagesize
	 *            ��һҳ������
	 * @param searchname
	 *            ������
	 */
	public List<Persons> finePagePersonsByName(int pagenumber, int pagesize, String searchname);

	/**
	 * ������Ա
	 * 
	 * @param persons
	 *            ��Ա
	 */
	String addPerson(Persons persons);

	/**
	 * ɾ����Ա
	 * 
	 * @param id
	 *            ��ԱID
	 */
	String deletePerson(int id);

	/**
	 * ������Ա
	 * 
	 * @param persons
	 *            ��Ա
	 */
	String updataPerson(Persons persons);
}
