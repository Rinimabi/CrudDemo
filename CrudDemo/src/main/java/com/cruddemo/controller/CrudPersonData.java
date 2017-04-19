package com.cruddemo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cruddemo.enity.Department;
import com.cruddemo.enity.Persons;
import com.cruddemo.service.serviceImpl.DepartmentServiceImpl;
import com.cruddemo.service.serviceImpl.PersonsServiceImpl;
import com.cruddemo.service.serviceImpl.TreeServiceImpl;

@Controller
public class CrudPersonData {
	@Autowired 
	private PersonsServiceImpl personsDAOImp;
	@Autowired      
	private DepartmentServiceImpl departmentDAOImp;
	@Autowired
	private TreeServiceImpl treeDAOImp;
	int count = 0; // countֵ����ȷ����ѯ������������ʼ����ҳ��ʱ��ȷ���м�ҳ
	
	@RequestMapping(value = "/add_person", method = RequestMethod.POST)
	public String AddData(@ModelAttribute Persons persons, Model model) throws Exception {
		System.out.println("��ȡ�������Ա����Ϣ...");
		persons.display();
		// ��һ�������ID�Ƿ���ڣ�Ŀ���Ƿ�ֹ����Ա��ӵ���Ա���档
		Department check = departmentDAOImp.findById(persons.getFrontid());
		if (check == null) {
			model.addAttribute("result", "Failure!");
			return "RealMain.jsp";
		}
		// ȡһ����ӽ������Ϣ������
		String tdata = personsDAOImp.addPerson(persons);
		model.addAttribute("result", tdata);
		System.out.println("�����" + tdata);
		return "RealMain.jsp";
	}
	
	@RequestMapping(value = "/delete_person", method = RequestMethod.POST)
	@ResponseBody
	public String DeleteData(@RequestParam(value = "tdata",required=false) int id) throws Exception {
		String tdata = null;
		System.out.println("Ҫɾ������ԱID��" + id);
		tdata = personsDAOImp.deletePerson(id);
		System.out.println("�����" + tdata);
		return tdata;
	}
	
	@RequestMapping(value = "/input_person", method = RequestMethod.POST)
	public String InputData(@RequestParam(value = "personid",required=false) String personid,
			@RequestParam(value = "departmentid",required=false) String departmentid,
			@RequestParam(value = "personname",required=false) String personname, Model model) throws Exception {

		System.out.println("Ҫ��ӵ���Ա��Ϣ��ID:" + personid + "  ���֣�" + personname + "  ����ID�� " + departmentid);
		String tdata = treeDAOImp.InputConnect(personid, personname, departmentid);
		model.addAttribute("result", tdata);
		System.out.println("�����" + tdata);
		return "RealMain.jsp";
	}
	
	@RequestMapping(value = "/remove_person", method = RequestMethod.POST)
	public String RemoveData(@RequestParam(value = "personid",required=false) String personid,
			@RequestParam(value = "departmentid",required=false) String departmentid, Model model) throws Exception {
		System.out.println("Ҫ�Ƴ�����Ա��Ϣ��");
		System.out.println("ID:" + personid + "   ����ID:" + departmentid);
		String tdata = treeDAOImp.RemoveConnect(personid, departmentid);
		model.addAttribute("result", tdata);
		System.out.println("�����" + tdata);
		return "RealMain.jsp";
	}
	
	@RequestMapping(value = "/updata_person", method = RequestMethod.POST)
	public String AddData(Model model,@ModelAttribute Persons persons) throws Exception {
		System.out.println("��ȡ����������...");
		persons.display();
		String tdata = personsDAOImp.updataPerson(persons);
		model.addAttribute("result", tdata);
		System.out.println("�����" + tdata);

		return "RealMain.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value = "/persons", method = RequestMethod.POST)
	public Map<String, Object> showData(@RequestParam(value = "pagenumber",required=false) int pagenumber,
			@RequestParam(value = "pagesize",required=false) int pagesize,
			@RequestParam(value = "pageid",required=false) String pageid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pagenumber == 0) {
			pagenumber = 1;
		}
		System.out.println("��ʼȡ��Ա��Ϣ...");
		List<Persons> list = new ArrayList<Persons>();
		list = personsDAOImp.fineAllPersons(pageid);
		count = list.size();
		list = personsDAOImp.finePagePersons(pagenumber, pagesize, pageid);
		System.out.println("�˲��ŵ���Ա��Ϣ���£�");
		for (int i = 0; i < list.size(); i++) {
			list.get(i).display();
		}
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/persons_byname", method = RequestMethod.POST)
	public Map<String, Object> showDataByName(@RequestParam(value = "pagenumber",required=false) int pagenumber,
			@RequestParam(value = "pagesize",required=false) int pagesize,
			@RequestParam(value = "searchname",required=false) String searchname) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Persons> list = new ArrayList<Persons>();
		list = personsDAOImp.finePagePersonsByName(pagenumber, pagesize, searchname);
		count = personsDAOImp.getCount();
		System.out.println("����������Ա��");
		for (int i = 0; i < list.size(); i++) {
			list.get(i).display();
		}
		System.out.println("��ʼ������������Ա...");
		map.put("list", list);
		map.put("count", count);
		return map;
	}
}