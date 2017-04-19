package com.cruddemo.service.serviceImpl;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruddemo.dao.ConnectDao;
import com.cruddemo.dao.PersonsDao;
import com.cruddemo.enity.Connect;
import com.cruddemo.enity.Persons;
import com.cruddemo.service.PersonsService;
@Service
public class PersonsServiceImpl implements PersonsService {
	@Autowired
	private PersonsDao personsDao;
	@Autowired
	private ConnectDao connectDao;
	private int count = 0;

	public List<Persons> fineAllPersons(String pageid) {
		List<Persons> persons = null;
		persons = personsDao.findByFrontid(pageid);
		return persons;
	}

	public List<Persons> finePagePersons(int pagenumber, int pagesize, String pageid) {
		List<Persons> persons = null;
		int number = pagesize * (pagenumber - 1);
		persons = personsDao.findOnePage(number, pagesize, pageid);
		return persons;
	}

	public List<Persons> finePagePersonsByName(int pagenumber, int pagesize, String searchname) {
		List<Persons> persons = null;
		int number = pagesize * (pagenumber - 1);
		persons = personsDao.findByNameCount(searchname);
		setCount(persons.size());
		persons = personsDao.findByNamePage(number, pagesize, searchname);
		return persons;
	}

	public String addPerson(Persons persons) {
		Connect connect = new  Connect(persons.getId(),persons.getCnname(),persons.getFrontid());
		try{
			personsDao.save(persons);
			connectDao.save(connect);
			System.out.println("增加成功");
			return "Addition Success!";
		}catch(Exception e){
			System.out.println("增加失败");
			return "Addition Failure!";
		}
	}

	public String deletePerson(int id) {
		try {
			Persons persons = personsDao.findById(id);
			List<Connect> list = (List<Connect>) connectDao.findAll();
			for(int i=0;i<list.size();i++){
				if(list.get(i).getPersonid()==id){
					connectDao.delete(list.get(i));
				}
			}
			personsDao.delete(persons);
			System.out.println("删除成功");
			return "Delete Success!";
		} catch (Exception e){
			System.out.println("删除失败");
			return "Delete Failure!";
		}
	}

	public String updataPerson(Persons persons) {
		try{
			personsDao.save(persons);
			List<Connect> list = (List<Connect>) connectDao.findAll();
			for(int i=0;i<list.size();i++){
				if(list.get(i).getPersonid()==persons.getId()){
					Connect connect = new Connect(persons.getId(),persons.getCnname(),persons.getFrontid());
					connectDao.save(connect);
				}
			}
			System.out.println("更新成功");
			return "Updata Success!";
		}
		catch(HibernateException e){
			System.out.println("更新失败");
			return "Updata Failure!";
		}
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
