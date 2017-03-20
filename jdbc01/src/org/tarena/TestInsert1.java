package org.tarena;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestInsert1 {

	/**
	 * 向emp表插入一条记录
	 * @param args
	 */
	public static void main(String[] args) {
//		insertEmp(7600,"张三",3600,20);
		System.out.println("插入操作,请输入以下格式的字符串:");
		System.out.println("编号,名字,工资,部门编号");
		Scanner sc = new Scanner(System.in);
		String line = sc.next();
		//切割各个字段值
		String[] columns = line.split(",");
		//将各个字段值传入insertEmp方法
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
			//1.注册驱动,触发OracleDriver中的static语句块,加载ojdbc6.jar包
			Class.forName("oracle.jdbc.OracleDriver");
			//new oracle.jdbc.OracleDriver();//不推荐
			//2.指定连接参数
			String user = "demo";//oracle用户名
			String password = "demo";//oracle密码
		//连接字符串,指定主机ip,端口号,库名
		//jdbc:oracle:thin:@ip:1521:库名
			String url = 
			"jdbc:oracle:thin:@192.168.0.23:1521:tarena10g";
			//3.获取Connection连接对象
			con = DriverManager.getConnection(
					url,user,password);
			//4.写一个insert语句,给statement执行
			stat = con.createStatement();
			//sql中不要写最后的;号
			String sql = "insert into " +
					"emp(empno,ename,sal,deptno) " +
					"values ("+empno+",'"+ename+"',"
					+sal+","+deptno+")";
			System.out.println(sql);
			stat.executeUpdate(sql);//发送sql并执行
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				stat.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				con.close();//关闭con,释放连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	

}
