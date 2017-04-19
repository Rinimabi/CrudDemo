package com.cruddemo.service.serviceImpl;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruddemo.dao.ConnectDao;
import com.cruddemo.dao.DepartmentDao;
import com.cruddemo.enity.Connect;
import com.cruddemo.enity.Department;
import com.cruddemo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	private ConnectDao connectDao;
	public Department findById(Integer id){
		return departmentDao.findById(id);
	}

	public String save(Department department) {
		try{
			departmentDao.save(department);
			System.out.println("���ӳɹ�");
			return "Addition Success!";
		} catch (Exception e) {
			System.out.println("����ʧ��");
			return "Addition Failure!";
		}
	}

	public String delete(int ID) {
		try{
			Department department = departmentDao.findById(ID);
			List<Connect> list = (List<Connect>) connectDao.findAll();
			for(int i=0;i<list.size();i++){
				if(list.get(i).getDepartmentid()==ID){
					connectDao.delete(list.get(i));
				}
			}
			departmentDao.delete(department);
			System.out.println("ɾ���ɹ�");
			return "Delete Success!";
		}
		catch(Exception e){
			System.out.println("ɾ��ʧ��");
			return "Delete Failure!";
		}
	}

	public String updata(Department department) {
		try{
			departmentDao.save(department);
			System.out.println("���³ɹ�");
			return "Updata Success!";
		}
		catch(HibernateException e){
			System.out.println("����ʧ��");
			return "Updata Failure!";
		}
	}
}
