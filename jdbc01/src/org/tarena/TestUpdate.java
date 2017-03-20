package org.tarena;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {

	/**
	 * 更新larry的hiredate和sal
	 * hiredate修改成当前时间
	 * sal修改成9000
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		Statement stat = null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			String url = 
				"jdbc:oracle:thin:@192.168.0.23:1521:tarena10g";
			con = DriverManager.getConnection(
					url,"demo","demo");
			stat = con.createStatement();
//			String sql = 
//				  "update emp " +
//					"set hiredate=sysdate," +
//					"sal=9000 " +
//					"where ename='larry'";
			String sql = "update emp " +
	"set hiredate=to_date('2008-08-08','yyyy-mm-dd') " +
	"where ename='bob'";
			System.out.println(sql);
			//执行sql,返回该语句影响了几行记录
			int size = stat.executeUpdate(sql);
			System.out.println("更新了"+size+"行记录");
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				stat.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
