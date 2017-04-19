package com.cruddemo.dao;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cruddemo.enity.Department;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Serializable>{
	Department findById(Integer id);
}
