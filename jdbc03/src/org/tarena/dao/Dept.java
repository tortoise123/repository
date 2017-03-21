package org.tarena.dao;

/**
 * 根据DEPT表编写,一个字段对应写一个属性变量;
 * 8个基本类型建议使用封装类型定义
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
