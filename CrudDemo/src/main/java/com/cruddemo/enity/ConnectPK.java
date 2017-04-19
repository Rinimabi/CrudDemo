package com.cruddemo.enity;

import java.io.Serializable;

public class ConnectPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8204520989482369656L;
	
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
	private int personid;
	private String personname;
	private int departmentid;
}
