package org.tarena;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {

	/**
	 * ��ѯemp�е�empno,ename,hiredate,sal��Ϣ
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
			//ִ��select���,��ȡresultset�������
			String sql = 
				  "select empno,ename,hiredate,sal " +
					"from emp order by sal";
			rs = stat.executeQuery(sql);//ִ��select
			//����rs��ȡ���,next���������ƶ�һ��ָ��,
			//�м�¼����true,û��¼����false
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
