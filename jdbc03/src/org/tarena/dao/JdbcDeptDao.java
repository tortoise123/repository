package org.tarena.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.tarena.util.DbUtil;

public class JdbcDeptDao implements IDeptDao{

	@Override
	public void add(Dept dept) throws SQLException {
		String sql = "insert into " +
				"DEPT(deptno,dname,loc) " +
				"values (dept_seq.nextval,?,?)";
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = DbUtil.openConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, dept.getDname());
			pst.setString(2, dept.getLoc());
			pst.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			DbUtil.closeConnection(con, pst);
		}
		
	}

	@Override
	public void update(Dept dept) throws SQLException {
		String sql = "update DEPT " +
				"set DNAME=?,LOC=? where DEPTNO=?";
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = DbUtil.openConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, dept.getDname());
			pst.setString(2, dept.getLoc());
			pst.setInt(3, dept.getDeptno());
			pst.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			DbUtil.closeConnection(con, pst);
		}
	}

	@Override
	public void delete(Integer deptno) throws SQLException {
		String sql = "delete from DEPT where DEPTNO=?";
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = DbUtil.openConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, deptno);
			pst.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			DbUtil.closeConnection(con, pst);
		}
	}

	@Override
	public Dept findById(Integer deptno) throws SQLException {
		String sql = "select DEPTNO,DNAME,LOC " +
				"from DEPT where DEPTNO=?";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			con = DbUtil.openConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, deptno);
			rs = pst.executeQuery();
			//从rs中获取记录封装成Dept对象
			if(rs.next()){
				Dept dept = toCreateDept(rs);
				return dept;
			}
			return null;//没有记录返回null
		}catch(SQLException ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			DbUtil.closeConnection(con, pst,rs);
		}
		
	}
	
	private Dept toCreateDept(ResultSet rs) throws SQLException{
		Dept dept = new Dept();
		dept.setDeptno(rs.getInt("DEPTNO"));
		dept.setDname(rs.getString("DNAME"));
		dept.setLoc(rs.getString("LOC"));
		return dept;
	}

	@Override
	public List<Dept> findAll() throws SQLException {
		String sql = "select DEPTNO,DNAME,LOC " +
				"from DEPT order by DEPTNO";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			con = DbUtil.openConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			//从rs中获取记录封装成List<Dept>对象
			List<Dept> list = new ArrayList<Dept>();
			while(rs.next()){
				Dept dept = toCreateDept(rs);
				list.add(dept);
			}
			return list;
		}catch(SQLException ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			DbUtil.closeConnection(con, pst,rs);
		}
	}

	@Override
	public List<Dept> findPage(int page, int pageSize) throws SQLException {
		String sql = "select deptno,dname,loc " +
				"from (select deptno,dname,loc,rownum rn from dept) " +
				"where rn>? and rn<=?";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			con = DbUtil.openConnection();
			pst = con.prepareStatement(sql);
			//设置分页参数?
			int begin = (page-1)*pageSize;
			int end = page*pageSize;
			pst.setInt(1, begin);
			pst.setInt(2, end);
			//执行分页查询
			rs = pst.executeQuery();
			List<Dept> list = new ArrayList<Dept>();
			while(rs.next()){
				Dept dept = toCreateDept(rs);
				list.add(dept);
			}
			return list;
		}catch(SQLException ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			DbUtil.closeConnection(con, pst, rs);
		}
		
	}


}
