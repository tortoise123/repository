package org.tarena;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.tarena.util.DbUtil;

/**
 * ����PreparedStatement+������ʵ��
 * @author Administrator
 *
 */
public class TestPrepareStatementBatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = DbUtil.openConnection();
			String sql = "insert into foo_1 (c1,c2) " +
					"values (?,?)";
			pst = con.prepareStatement(sql);
			for(int i=0;i<2000;i++){
				pst.setString(1, i+"");
				pst.setString(2, "larry"+i);
				pst.addBatch();//������һ�������������
				if(i%50==0 && i != 0){
					pst.executeBatch();//��50��ִ��
				}
			}
			pst.executeBatch();//ִ�����һ��
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			DbUtil.closeConnection(con, pst);
		}

	}

}
