package com.cruddemo.enity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "persons")
public class Persons {
	@Id
	private Integer id; // ID
	@Transient
	private Integer frontid; // �ϼ�ID�����������棩
	private String loginname; // ��¼����
	private String cnname; // ��������
	private String sex; // �Ա�
	private String duty; // ְλ
	private String dutyname; // ְ��
	private String education; // ѧ��
	private int age; // ����
	private int used; // �Ƿ�ʹ��
	private int deleted; // �Ƿ�ɾ��
	private String shortname; // ��ƣ��ȵȣ�Ϊʲô�����������
	private String exname; // ������

	public Persons() {

	}

	public Persons(int id, int frontid, String loginname, String cnname, String sex, String duty, String dutyname,
			String education, int age, int used, int deleted, String shortname, String exname) {
		this.id = id;
		this.frontid = frontid;
		this.loginname = loginname;
		this.cnname = cnname;
		this.sex = sex;
		this.duty = duty;
		this.dutyname = dutyname;
		this.education = education;
		this.age = age;
		this.used = used;
		this.deleted = deleted;
		this.shortname = shortname;
		this.exname = exname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getDutyname() {
		return dutyname;
	}

	public void setDutyname(String dutyname) {
		this.dutyname = dutyname;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getExname() {
		return exname;
	}

	public void setExname(String exname) {
		this.exname = exname;
	}

	public void display() {
		System.out.println("Id=" + id + ",Loginname=" + loginname + ",CnName=" + cnname + ",Sex=" + sex + ",Duty="
				+ duty + ",DutyName=" + dutyname + ",Education=" + education + ",Age=" + age + ",Used=" + used
				+ ",Deleted=" + deleted + ",Exname=" + exname + ",ShortName=" + shortname);
	}

	public Integer getFrontid() {
		return frontid;
	}

	public void setFrontid(Integer frontid) {
		this.frontid = frontid;
	}
}
