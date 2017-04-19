package com.cruddemo.controller.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cruddemo.enity.Tree;
import com.cruddemo.service.serviceImpl.TreeServiceImpl;

@Controller
public class findTree {
	@Autowired      
	private TreeServiceImpl treeDAOImp;
	
	@ResponseBody
	@RequestMapping(value = "/tree", method = RequestMethod.POST)
	public Map<String, Object> showData(@RequestParam(value = "isSelectPerson",required=false) String isSelect,
			@RequestParam(value = "treenode",required=false) String treenode) throws Exception {
		System.out.println("开始载入树结构...");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", getList(isSelect, treenode));
		return map;
	}

	public List<Tree> getList(String isSelect, String treenode) {
		List<Tree> list = new ArrayList<Tree>();
		// 判断是不是添加人员树的数据请求，如果是，返回所有人员信息
		if (isSelect != null) {
			list = treeDAOImp.allPersonTree();
			return list;
		}
		// 查询部门节点
		list = treeDAOImp.allDepartmentTree(treenode);
		// 再查询人员节点，并合并成一个list
		// list.addAll(treeDAOImp.allPersonTree(treenode));
		// list.add(new Tree(1,0,"罗湖区"));
		for (int i = 0; i < list.size(); i++) {
			list.get(i).Display();
		}
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/randomid", method = RequestMethod.POST)
	public String getRandom() {
		int max = 999999999;
		int min = 100;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		System.out.println("random:" + s);
		return s + "";
	}
}
