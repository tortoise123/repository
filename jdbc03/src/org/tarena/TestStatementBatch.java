package org.tarena;

import java.sql.Connection;
import java.sql.Statement;

import org.tarena.util.DbUtil;

/*
 * ����Statement������ʽ�����¼
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
				stat.addBatch(sql);//��sql��������
				//��50������ִ��һ��
				if(i%50==0 && i!=0){
					stat.executeBatch();//��������sqlִ��
				}
			}
			stat.executeBatch();//�������һ��sqlִ��
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DbUtil.closeConnection(con, stat);
		}
		
		

	}

}
