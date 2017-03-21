package org.tarena;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.tarena.util.DbUtil;

public class TestMetaData {
	public static void main(String[] args){
		String sql = "select * from dept";
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			con = DbUtil.openConnection();
			//获取数据库的结构信息
			DatabaseMetaData dbMeta = con.getMetaData();
			System.out.println(dbMeta.getDatabaseProductName());
			System.out.println(dbMeta.getDatabaseProductVersion());
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			//获取结果集结构信息
			ResultSetMetaData meta = rs.getMetaData();
			int size = meta.getColumnCount();
			System.out.println("DEPT表一共有"+size+"列");
			for(int i=1;i<=size;i++){
				String columnName = meta.getColumnName(i);
				System.out.println(columnName);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DbUtil.closeConnection(con, pst, rs);
		}
	}
}
