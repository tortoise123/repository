package org.tarena.dao;

/**
 * ����DEPT���д,һ���ֶζ�Ӧдһ�����Ա���;
 * 8���������ͽ���ʹ�÷�װ���Ͷ���
 * @author Administrator
 *
 */
public class Dept {
	private Integer deptno;
	private String dname;
	private String loc;
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
}
