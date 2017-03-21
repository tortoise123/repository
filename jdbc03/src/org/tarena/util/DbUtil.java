package org.tarena.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {
	private static String url;
	private static String user;
	private static String password;
	
	static{//�����߼�ִֻ��һ�ξͿ���
		try{
			//��ȡorg.config���µ�db.properties�ļ�
			Properties props = new Properties();
			InputStream inStream = DbUtil.class
				.getClassLoader().getResourceAsStream(
						"org/config/db.properties");
			props.load(inStream);
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			String driver = props.getProperty("driver");
			//ע������,��ȡconnection����
			Class.forName(driver);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * ����һ��connection���Ӷ���
	 * @return
	 * @throws SQLException
	 */
  public static Connection openConnection() 
  		throws SQLException{
  	try{
			Connection con = DriverManager.getConnection(
					url,user,password);
			return con;
  	}catch(Exception ex){
  		throw new SQLException(ex);
  	}
  }
  
  /**
   * �ر�connection����
   * @param con
   */
  public static void closeConnection(Connection con){
  	if(con != null){
  		try{
  			con.close();
  		}catch(Exception ex){}
  	}
  }
  
  public static void closeConnection(
  		Connection con,Statement stat){
  	//�ͷ�stat
  	if(stat != null){
  		try{
  			stat.close();
  		}catch(Exception ex){}
  	}
  	//�ͷ�con
  	closeConnection(con);
  }
  
  public static void closeConnection(
  		Connection con,Statement stat,ResultSet rs){
  	//�ͷ�rs
  	if(rs != null){
  		try{
  			rs.close();
  		}catch(Exception ex){}
  	}
  	//�ͷ�stat,con
  	closeConnection(con,stat);
  }
  
  
  
}
