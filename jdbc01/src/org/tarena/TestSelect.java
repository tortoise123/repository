package org.tarena;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {

	/**
	 * 查询emp中的empno,ename,hiredate,sal信息
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			String url = 
			"jdbc:oracle:thin:@192.168.0.23:1521:tarena10g";
			con = DriverManager.getConnection(
					url,"demo","demo");
			stat = con.createStatement();
			//执行select语句,获取resultset结果对象
			String sql = 
				  "select empno,ename,hiredate,sal " +
					"from emp order by sal";
			rs = stat.executeQuery(sql);//执行select
			//遍历rs获取结果,next用于向下移动一行指针,
			//有记录返回true,没记录返回false
			while(rs.next()){
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				Date hiredate = rs.getDate("hiredate");
				double sal = rs.getDouble("sal");
				System.out.println(empno+" "+ename+" "
						+hiredate+" "+sal);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
