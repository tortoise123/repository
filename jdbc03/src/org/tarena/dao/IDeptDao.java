package org.tarena.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDeptDao {
	/**
	 * 将dept对象信息写入DEPT表
	 * @param dept
	 * @throws SQLException
	 */
	public void add(Dept dept) throws SQLException;
	/**
	 * 根据deptno更新其他字段值
	 * @param dept
	 * @throws SQLException
	 */
	public void update(Dept dept) throws SQLException;
	/**
	 * 根据deptno删除某一行记录
	 * @param deptno
	 * @throws SQLException
	 */
	public void delete(Integer deptno) throws SQLException;
	/**
	 * 根据deptno查询某一行记录
	 * @param deptno 部门编号
	 * @return 部门对象 没有记录返回null
	 * @throws SQLException
	 */
	public Dept findById(Integer deptno) throws SQLException;
	/**
	 * 查询DEPT所有记录
	 * @return
	 * @throws SQLException
	 */
	public List<Dept> findAll() throws SQLException;
	
	/**
	 * 分页查询,查询指定的page页的记录
	 * @param page 第几页
	 * @param pageSize 一页显示多少条
	 * @return 指定页的记录
	 * @throws SQLException
	 */
	public List<Dept> findPage(int page,int pageSize) throws SQLException;
	
	
	
}
