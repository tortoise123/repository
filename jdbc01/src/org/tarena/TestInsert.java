package org.tarena;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {

	/**
	 * ��emp�����һ����¼
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		Statement stat = null;
		try{
//			Class.forName("org.tarena.MyOracleDriver");
			//1.ע������,����OracleDriver�е�static����,����ojdbc6.jar��
			Class.forName("oracle.jdbc.OracleDriver");
			//new oracle.jdbc.OracleDriver();//���Ƽ�
			//2.ָ�����Ӳ���
			String user = "demo";//oracle�û���
			String password = "demo";//oracle����
		//�����ַ���,ָ������ip,�˿ں�,����
		//jdbc:oracle:thin:@ip:1521:����
			String url = 
			"jdbc:oracle:thin:@192.168.0.23:1521:tarena10g";
			//3.��ȡConnection���Ӷ���
			con = DriverManager.getConnection(
					url,user,password);
			//4.дһ��insert���,��statementִ��
			stat = con.createStatement();
			//sql�в�Ҫд����;��
			String sql = "insert into " +
					"emp(empno,ename,sal,deptno) " +
					"values (7503,'bob',8000,10)";
			System.out.println(sql);
			stat.executeUpdate(sql);//����sql��ִ��
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				stat.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				con.close();//�ر�con,�ͷ�����
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
