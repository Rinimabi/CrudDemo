package com.cruddemo.enity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "connect")
@IdClass(ConnectPK.class)
public class Connect {
	public Connect(){
	}
	public Connect(int personid,String personname,int departmentid){
		this.personid = personid;
		this.personname = personname;
		this.departmentid = departmentid;
	}
	public int getPersonid() {
		return personid;
	}
	public void setPersonid(int personid) {
		this.personid = personid;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public int getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}
	@Id
	private int personid;
	@Id
	private String personname;
	@Id
	private int departmentid;
}
