package com.cruddemo.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cruddemo.enity.Persons;

@Repository
public interface PersonsDao extends CrudRepository<Persons, Serializable>{
	Persons findById(Integer id);
	
	@Query(value="select id,departmentid as frontid,loginname,cnname,sex,duty,dutyname,education,age,used,deleted,shortname,exname from connect,persons where connect.personid=persons.id and ( personid = :pageid or departmentid = :pageid ) group by id",nativeQuery = true)
	List<Persons> findByFrontid( @Param("pageid") String pageid );
	
	@Query(value="select id,departmentid as frontid,loginname,cnname,sex,duty,dutyname,education,age,used,deleted,shortname,exname from government.connect,government.persons where government.connect.personid=government.persons.id and ( personid = :pageid or departmentid = :pageid ) group by id limit  :number , :pagesize",nativeQuery = true)
	List<Persons> findOnePage(@Param("number") int number, @Param("pagesize") int pagesize, @Param("pageid") String pageid);
	
	@Query(value="select id,departmentid as frontid,loginname,cnname,sex,duty,dutyname,education,age,used,deleted,shortname,exname from government.connect,government.persons where government.connect.personid=government.persons.id  and ( personname like %:searchname% ) group by id limit :number , :pagesize",nativeQuery = true)
	List<Persons> findByNamePage(@Param("number") int number, @Param("pagesize") int pagesize, @Param("searchname") String searchname);
	
	@Query(value="select id,departmentid as frontid,loginname,cnname,sex,duty,dutyname,education,age,used,deleted,shortname,exname from government.connect,government.persons where government.connect.personid=government.persons.id  and ( personname like %:searchname% ) group by id",nativeQuery = true)
	List<Persons> findByNameCount(@Param("searchname") String searchname);
	
	@Query(value="insert into government.connect(personid,personname,departmentid) values(:personid,:personname,:departmentid)",nativeQuery = true)
	void saveConnect(@Param("personid") Integer personid, @Param("personname") String personname, @Param("departmentid") Integer departmentid);
}
