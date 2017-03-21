package org.tarena;

import java.sql.Connection;
import java.sql.Statement;

import org.tarena.util.DbUtil;

/*
 * 基于Statement批处理方式插入记录
 */
public class TestStatementBatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		Statement stat = null;
		try{
			con = DbUtil.openConnection();
			stat = con.createStatement();
			for(int i=0;i<2000;i++){
				String sql = "insert into foo_1(c1,c2) " +
						"values ('"+i+"','tom"+i+"')";
				stat.addBatch(sql);//将sql缓存下来
				//够50个发送执行一次
				if(i%50==0 && i!=0){
					stat.executeBatch();//发送批中sql执行
				}
			}
			stat.executeBatch();//发送最后一批sql执行
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DbUtil.closeConnection(con, stat);
		}
		
		

	}

}
