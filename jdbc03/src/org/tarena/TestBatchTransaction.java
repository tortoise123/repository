package org.tarena;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.tarena.util.DbUtil;

/**
 * ���������ͬһ��������������
 * @author Administrator
 *
 */
public class TestBatchTransaction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		try{
			con = DbUtil.openConnection();
			String sql = "insert into foo_2 (id,name)" +
					" values(?,?)";
			con.setAutoCommit(false);//�ر��Զ��ύ
			pst = con.prepareStatement(sql);
			pst.setInt(1, 1);
			pst.setString(2, "tiger");
			pst.addBatch();//����һ�����
			pst.setInt(1, 1);
			pst.setString(2, "scott");
			pst.addBatch();//������һ�����,�д���Υ������Լ��
			pst.setInt(1, 2);
			pst.setString(2, "bob");
			pst.addBatch();//������һ�����
			pst.executeBatch();//ִ��������
			con.commit();//�ֶ��ύ
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}finally{
			DbUtil.closeConnection(con, pst);
		}

	}

}
