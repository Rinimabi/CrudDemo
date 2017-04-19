package com.cruddemo.service.serviceImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cruddemo.enity.Tree;
import com.cruddemo.service.TreeService;
@Service
public class TreeServiceImpl implements TreeService {
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	int frontid;

	public void setDatasource(DataSource ds) {
		this.datasource = ds;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	public List<Tree> allTreeBySearch(String searchname) {
		List<Tree> trees = null;
		List<Tree> search = null;
		List<Tree> persons = null;
		persons = SeachPerson(searchname);
		if (persons.size() > 0) {
			for (int i = 0; i < persons.size(); i++) {
				frontid = persons.get(i).getFrontid();
				while (frontid != 0) {
					String sql2 = "select id,frontid,cnname,used from government.departments where " + " id=" + frontid
							+ " and deleted=0  group by id";
					search = jdbcTemplate.query(sql2, rowMapper);
					int a = 0;
					for (int x = 0; x < persons.size(); x++) {
						if (persons.get(x).getId() != search.get(0).getId()) {
							a = a ^ 0;
						} else {
							a = a ^ 1;
						}
					}
					if (a == 0) {
						persons.add(search.get(0));
					}
					frontid = search.get(0).getFrontid();
				}
			}
			return persons;
		}
		persons = null;
		String sql = "select id,frontid,cnname,used from government.departments where " + " cnname like '%" + searchname
				+ "%' and deleted=0  group by id";
		trees = jdbcTemplate.query(sql, rowMapper);
		for (int i = 0; i < trees.size(); i++) {
			frontid = trees.get(i).getFrontid();
			while (frontid != 0) {
				String sql2 = "select id,frontid,cnname,used from government.departments where id=" + frontid
						+ " and deleted=0  group by id";
				search = jdbcTemplate.query(sql2, rowMapper);
				/*
				 * if(persons==null){persons=allPersonTree(frontid+"");}
				 * else{persons.addAll(allPersonTree(frontid+""));}
				 */
				// 检查是否有重复元素（NMB，就该用set）
				int a = 0;
				for (int x = 0; x < trees.size(); x++) {
					if (trees.get(x).getId() != search.get(0).getId()) {
						a = a ^ 0;
					} else {
						a = a ^ 1;
					}
				}
				if (a == 0) {
					trees.add(search.get(0));
				}
				frontid = search.get(0).getFrontid();
			}
		}
		for (int i = 0; i < trees.size(); i++) {
			trees.get(i).setIcons();
			// trees.get(i).setOpen("true");
		}
		// trees.addAll(persons);
		return trees;
	}

	public List<Tree> allDepartmentTree(String treenode) {
		List<Tree> trees = null;
		// String sql = "select id,frontid,cnname from government.departments
		// UNION ALL SELECT id,departmentid as frontid,cnname from
		// government.connect,government.persons where
		// government.connect.personid=government.persons.id group by id";
		// String sql = "select id,frontid,cnname from government.departments
		// where id = "+treenode+" or frontid="+treenode+" group by id";

		String sql = "select id,frontid,cnname,used from government.departments where " + " frontid=" + treenode
				+ " and deleted=0  group by id";
		trees = jdbcTemplate.query(sql, rowMapper);
		for (int i = 0; i < trees.size(); i++) {
			// trees.get(i).setIcons();
			trees.get(i).setParentIconTrue();
			if (trees.get(i).getUsed() == 0) {
				trees.get(i).setParentIconFalse();
			}
		}
		return trees;
	}

	public List<Tree> allPersonTree() {
		List<Tree> trees = null;
		String sql = "SELECT id,id as frontid,cnname,used from government.persons where used=1 and deleted=0 group by id";
		trees = jdbcTemplate.query(sql, rowMapper);
		return trees;
	}

	public List<Tree> allPersonTree(String treenode) {
		List<Tree> trees = null;
		String sql = "SELECT id,departmentid as frontid,cnname,used from government.connect,government.persons where government.connect.personid=government.persons.id and departmentid="
				+ treenode + " and deleted=0 group by id";
		trees = jdbcTemplate.query(sql, rowMapper);
		return trees;
	}

	public List<Tree> SeachPerson(String personname) {
		List<Tree> trees = null;
		String sql = "SELECT id,departmentid as frontid,cnname,used from government.connect,government.persons"
				+ " where government.connect.personid=government.persons.id and cnname like '%" + personname
				+ "%' and deleted=0";
		trees = jdbcTemplate.query(sql, rowMapper);
		return trees;
	}

	public String InputConnect(String personid, String personname, String departmentid) {
		String sql = "insert into government.connect values (" + personid + ",'" + personname + "'," + departmentid
				+ ")";
		int temp = jdbcTemplate.update(sql);
		if (temp > 0) {
			return "Success!";
		} else {
			return "Fairlue";
		}
	}

	public String RemoveConnect(String personid, String departmentid) {
		String sql = "delete from government.connect where personid=" + personid + " and departmentid=" + departmentid;
		int temp = jdbcTemplate.update(sql);
		if (temp > 0) {
			return "Success!";
		} else {
			return "Fairlue";
		}
	}
	protected RowMapper<Tree> rowMapper = new RowMapper<Tree>() {
		public Tree mapRow(ResultSet rs, int rownum) throws SQLException {
			Tree tree = new Tree();
			tree.setId(rs.getInt("id"));
			tree.setFrontid(rs.getInt("frontid"));
			tree.setCnname(rs.getString("cnname"));
			tree.setUsed(rs.getInt("used"));
			return tree;
		}
	};
}
