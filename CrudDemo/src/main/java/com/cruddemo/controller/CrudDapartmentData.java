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
import com.cruddemo.enity.Tree;
import com.cruddemo.service.serviceImpl.DepartmentServiceImpl;
import com.cruddemo.service.serviceImpl.TreeServiceImpl;

@Controller
public class CrudDapartmentData {
	@Autowired      
	private TreeServiceImpl treeServiceImpl;
	@Autowired 
	private DepartmentServiceImpl departmentServiceImpl;
	
	@RequestMapping(value = "/add_data", method = RequestMethod.POST)
	public String AddData( @ModelAttribute Department department, Model model) throws Exception {
		int frontid = department.getFrontid();
		System.out.println("已取到增加部门的信息...");
		department.display();
		// 查一下这个父ID是否存在，目的是防止将部门添加到人员下面。
		if (frontid != 0) {
			Department check = departmentServiceImpl.findById(frontid);
			if (check == null) {
				model.addAttribute("result", "Failure!");
				return "RealMain.jsp";
			}
		}
		// 取一个添加结果的信息，返回
		String tdata = departmentServiceImpl.save(department);
		model.addAttribute("result", tdata);
		return "RealMain.jsp?";
	}
	
	@RequestMapping(value = "/delete_department", method = RequestMethod.POST)
	@ResponseBody
	public String DeleteData(@RequestParam(value = "tdata",required=false) int id) throws Exception {
		String tdata = null;
		System.out.println("要删除的部门ID：" + id);
		tdata = departmentServiceImpl.delete(id);
		return tdata;
	}
	
	@RequestMapping(value = "/updata_data", method = RequestMethod.POST)
	public String AddData(Model model,@ModelAttribute Department department) throws Exception {
		System.out.println("已取到更新数据...");
		department.display();
		String tdata = departmentServiceImpl.updata(department);
		model.addAttribute("result", tdata);
		return "RealMain.jsp";
	}
	
	@RequestMapping(value = "/data", method = RequestMethod.POST)
	@ResponseBody // 重要：此注解将return值转为json
	public Map<String, Object> showData(@RequestParam(value = "pageid") Integer pageid) throws Exception {
		List<Department> list = new ArrayList<Department>();
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("开始取此部门信息...");

		list.add(departmentServiceImpl.findById(pageid));
		map.put("list", list);
		return map;
	}
	
	@RequestMapping(value = "/data_byname", method = RequestMethod.POST)
	@ResponseBody // 重要：此注解将return值转为json
	public Map<String, Object> showDataByName(@RequestParam(value = "searchname",required=false) String searchname) throws Exception {
		// 用一个map存储多组值
		List<Tree> list = new ArrayList<Tree>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println("开始搜索：" + searchname);
		list = treeServiceImpl.allTreeBySearch(searchname);
		System.out.println("搜索到的部门：");
		for (int i = 0; i < list.size(); i++) {
			list.get(i).Display();
		}
		map.put("list", list);
		return map;
	}
}
