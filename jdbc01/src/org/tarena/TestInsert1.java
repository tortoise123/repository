package org.tarena;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestInsert1 {

	/**
	 * ��emp�����һ����¼
	 * @param args
	 */
	public static void main(String[] args) {
//		insertEmp(7600,"����",3600,20);
		System.out.println("�������,���������¸�ʽ���ַ���:");
		System.out.println("���,����,����,���ű��");
		Scanner sc = new Scanner(System.in);
		String line = sc.next();
		//�и�����ֶ�ֵ
		String[] columns = line.split(",");
		//�������ֶ�ֵ����insertEmp����
		int empno = Integer.parseInt(columns[0]);
		String ename = columns[1];
		double sal = Double.parseDouble(columns[2]);
		int deptno = Integer.parseInt(columns[3]);
		insertEmp(empno,ename,sal,deptno);
	}
	
	public static void insertEmp(
			int empno,String ename,double sal,int deptno){
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
					"values ("+empno+",'"+ename+"',"
					+sal+","+deptno+")";
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
