package org.tarena;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.tarena.util.DbUtil;

/**
 * 用事务控制同一批操作的完整性
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
			con.setAutoCommit(false);//关闭自动提交
			pst = con.prepareStatement(sql);
			pst.setInt(1, 1);
			pst.setString(2, "tiger");
			pst.addBatch();//加入一组参数
			pst.setInt(1, 1);
			pst.setString(2, "scott");
			pst.addBatch();//加入另一组参数,有错误违反主键约束
			pst.setInt(1, 2);
			pst.setString(2, "bob");
			pst.addBatch();//加入另一组参数
			pst.executeBatch();//执行批操作
			con.commit();//手动提交
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
