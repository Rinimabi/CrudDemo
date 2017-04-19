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
	int count = 0; // count值用来确定查询到的总数，初始化分页的时候确定有几页
	
	@RequestMapping(value = "/add_person", method = RequestMethod.POST)
	public String AddData(@ModelAttribute Persons persons, Model model) throws Exception {
		System.out.println("已取到添加人员的信息...");
		persons.display();
		// 查一下这个父ID是否存在，目的是防止将人员添加到人员下面。
		Department check = departmentDAOImp.findById(persons.getFrontid());
		if (check == null) {
			model.addAttribute("result", "Failure!");
			return "RealMain.jsp";
		}
		// 取一个添加结果的信息，返回
		String tdata = personsDAOImp.addPerson(persons);
		model.addAttribute("result", tdata);
		System.out.println("结果：" + tdata);
		return "RealMain.jsp";
	}
	
	@RequestMapping(value = "/delete_person", method = RequestMethod.POST)
	@ResponseBody
	public String DeleteData(@RequestParam(value = "tdata",required=false) int id) throws Exception {
		String tdata = null;
		System.out.println("要删除的人员ID：" + id);
		tdata = personsDAOImp.deletePerson(id);
		System.out.println("结果：" + tdata);
		return tdata;
	}
	
	@RequestMapping(value = "/input_person", method = RequestMethod.POST)
	public String InputData(@RequestParam(value = "personid",required=false) String personid,
			@RequestParam(value = "departmentid",required=false) String departmentid,
			@RequestParam(value = "personname",required=false) String personname, Model model) throws Exception {

		System.out.println("要添加的人员信息：ID:" + personid + "  名字：" + personname + "  部门ID： " + departmentid);
		String tdata = treeDAOImp.InputConnect(personid, personname, departmentid);
		model.addAttribute("result", tdata);
		System.out.println("结果：" + tdata);
		return "RealMain.jsp";
	}
	
	@RequestMapping(value = "/remove_person", method = RequestMethod.POST)
	public String RemoveData(@RequestParam(value = "personid",required=false) String personid,
			@RequestParam(value = "departmentid",required=false) String departmentid, Model model) throws Exception {
		System.out.println("要移除的人员信息：");
		System.out.println("ID:" + personid + "   部门ID:" + departmentid);
		String tdata = treeDAOImp.RemoveConnect(personid, departmentid);
		model.addAttribute("result", tdata);
		System.out.println("结果：" + tdata);
		return "RealMain.jsp";
	}
	
	@RequestMapping(value = "/updata_person", method = RequestMethod.POST)
	public String AddData(Model model,@ModelAttribute Persons persons) throws Exception {
		System.out.println("已取到更新数据...");
		persons.display();
		String tdata = personsDAOImp.updataPerson(persons);
		model.addAttribute("result", tdata);
		System.out.println("结果：" + tdata);

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
		System.out.println("开始取人员信息...");
		List<Persons> list = new ArrayList<Persons>();
		list = personsDAOImp.fineAllPersons(pageid);
		count = list.size();
		list = personsDAOImp.finePagePersons(pagenumber, pagesize, pageid);
		System.out.println("此部门的人员信息如下：");
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
		System.out.println("搜索到的人员：");
		for (int i = 0; i < list.size(); i++) {
			list.get(i).display();
		}
		System.out.println("开始按名称搜索人员...");
		map.put("list", list);
		map.put("count", count);
		return map;
	}
}