package com.cruddemo.dao;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cruddemo.enity.Connect;

@Repository
public interface ConnectDao extends CrudRepository<Connect, Serializable>{
	//List<Connect> findAllById(Integer p);
}
