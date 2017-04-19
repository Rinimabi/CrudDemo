package com.cruddemo.enity;

public class Tree {
	private int id; // �ڵ�ID
	private int frontid; // �ϼ��ڵ�ID
	private String cnname; // �ڵ�����
	private String icon; // �ڵ�ͼ��
	private String iconOpen; // �ڵ�ر�ͼ��
	private String iconClose;// �ڵ��ͼ��
	private String isParent;
	private String open;
	private String zAsync;
	private int used;

	// ͼ�����ݲ��������ݿ�
	public Tree() {

	}

	public Tree(int id, int frontid, String cnname) {
		this.id = id;
		this.frontid = frontid;
		this.cnname = cnname;
	}

	// �ǲ��ž�����icon������ղ��Ż���ʾ���ļ���ͼ��
	public void setIcons() {
		this.icon = "images/Filder.png";
		this.iconClose = "images/Filder.png";
		this.iconOpen = "images/open.png";
		this.setOpen("false");
		// this.zAsync = "false";
	}

	public void setParentIconTrue() {
		this.setIsParent("true");
	}

	public void setParentIconFalse() {
		this.icon = "images/FilderUnuse.png";
		this.iconClose = "images/FilderUnuse.png";
		this.setIsParent("false");
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

	public void Display() {
		System.out.println("Id=" + id + ",FrontID=" + frontid + ",CnName=" + cnname);
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getzAsync() {
		return zAsync;
	}

	public void setzAsync(String zAsync) {
		this.zAsync = zAsync;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}
}
