package com.cruddemo.enity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
	@Id
	private int id; // ����ID
	private int frontid; // �ϼ�ID
	private String cnname; // ��������
	private int office; // �Ƿ�Ϊί���
	private int used; // �Ƿ�ʹ��
	private int deleted; // �Ƿ�ɾ��
	private String shortname;// ���
	private String exname; // ������

	public Department() {

	}
	public Department(int id, int frontid, String cnname, int office, int used, int deleted, String shortname,
			String exname) {
		this.id = id;
		this.frontid = frontid;
		this.cnname = cnname;
		this.office = office;
		this.used = used;
		this.deleted = deleted;
		this.shortname = shortname;
		this.exname = exname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setFrontid(int frontid) {
		this.frontid = frontid;
	}

	public int getFrontid() {
		return this.frontid;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getCnname() {
		return this.cnname;
	}

	public void setOffice(int office) {
		this.office = office;
	}

	public int getOffice() {
		return this.office;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public int getUsed() {
		return this.used;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getShortname() {
		return this.shortname;
	}

	public void setExname(String exname) {
		this.exname = exname;
	}

	public String getExname() {
		return this.exname;
	}

	public void display() {
		System.out.println("ID=" + id + ", ForntID= " + frontid + " ,CnName=" + cnname + " ,Office= " + office
				+ ", Used=" + used + ", Deleted=" + deleted + ", ShortName=" + shortname + ", ExName=" + exname);
	}
}
