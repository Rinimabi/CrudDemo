package com.cruddemo.service;
import java.util.List;


import com.cruddemo.enity.Persons;

public interface PersonsService {

	/**
	 * 按ID查询所有下级人员
	 * 
	 * @param pageid
	 *            部门ID
	 */
	public List<Persons> fineAllPersons(String pageid);

	/**
	 * 查询一页人员
	 * 
	 * @param pagenumber
	 *            表单的页码
	 * @param pagesize
	 *            表单一页的数量
	 * @param pageid
	 *            人员ID
	 */
	public List<Persons> finePagePersons(int pagenumber, int pagesize, String pageid);

	/**
	 * 按中文名字查询人员
	 * 
	 * @param pagenumber
	 *            表单的页码
	 * @param pagesize
	 *            表单一页的数量
	 * @param searchname
	 *            搜索名
	 */
	public List<Persons> finePagePersonsByName(int pagenumber, int pagesize, String searchname);

	/**
	 * 增加人员
	 * 
	 * @param persons
	 *            人员
	 */
	String addPerson(Persons persons);

	/**
	 * 删除人员
	 * 
	 * @param id
	 *            人员ID
	 */
	String deletePerson(int id);

	/**
	 * 更新人员
	 * 
	 * @param persons
	 *            人员
	 */
	String updataPerson(Persons persons);
}
